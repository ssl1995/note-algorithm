# Agent 面试题 09：Agent 的长短期记忆系统怎么做的？记忆是怎么存的？粒度是多少？怎么用的？
> 来源：https://xiaolinnote.com/ai/agent/9_memory_storage.html
## 🎯 核心考点一句话
短期记忆是 context window 里的 messages 列表（任务内的「工作台」），长期记忆靠 **Embedding + 向量数据库做语义检索**（跨任务的「档案」），存储粒度按「一次完整交互」或「一个独立知识点」，两层分工配合。
## 💡 简要回答（面试可直接背）
- **短期记忆**就是 context window 里的对话历史，存当前任务的中间状态，任务结束就清掉；
- **长期记忆**用向量数据库存：信息 embedding 后写入，用的时候做**语义检索**（不是关键词匹配）拿回来注入 prompt；
- **粒度**按「一次完整交互」或「一个关键事件」为单位存——太细碎检索噪音大，太粗糙丢失细节，需根据业务实际调整。
## 📝 详细解析
### 短期记忆：LLM 的「工作台」
- 本质就是每次调 LLM 传的 `messages` 列表：用户指令、LLM 思考、工具返回、中间状态全在桌上，LLM 靠它知道「我在做什么、做到哪了」；
- ⚠️ 关键点：**每次调 LLM 传的是完整历史，不只是最新一条**——这就是短期记忆的本质，把整个任务状态带在身上；代价是 messages 越来越长，context window 装满后早期内容被截断，Agent 开始「遗忘」；
- 任务结束后清空，桌面恢复干净。
```python
class ShortTermMemory:
    def __init__(self):
        # messages 列表就是 LLM 的工作台
        # 每条消息有 role（谁说的）和 content（说了什么）
        self.messages = []
    def add(self, role: str, content: str):
        # role 有三种：user（用户输入）、assistant（LLM 输出）、tool（工具返回结果）
        # 每一步的内容都要追加进来，保持完整的任务状态
        self.messages.append({"role": role, "content": content})
    def get_context(self):
        # 调 LLM 时把完整的 messages 传进去，LLM 读取完整历史来理解当前状态
        return self.messages
    def clear(self):
        # 任务结束后清空，这次任务的所有中间状态都消失了
        self.messages = []
# 一次任务执行的示例
memory = ShortTermMemory()
memory.add("user", "帮我分析这几家竞品的核心功能差异")
memory.add("assistant", "好的，我先搜索一下竞品 A 的信息")
memory.add("tool", "搜索结果：竞品 A 的核心功能是实时协作编辑……")
# 每次调 LLM 都传完整历史，它才能知道自己做到哪一步了
response = llm.chat(messages=memory.get_context())
```
**进阶：结构化工作记忆（Structured Working Memory）**：给工作台划出固定区域（当前任务目标 / 已确认的中间结论 / 待验证的假设），每步执行后主动更新对应区域、替换过时结论——即使对话很长，LLM 每次读到的都是当前最准确、结构清晰的任务状态，不会被杂乱历史淹没。
### 长期记忆：Embedding + 向量数据库
- **Embedding**：把文字转成几百~几千维的数字向量，捕捉语义；语义相近的文字向量距离近（类比 RGB 颜色编码，高维下通常用**余弦相似度**算距离）；
- **向量数据库**：专存向量，核心能力是**相似度检索**——给查询向量，找距离最近的几条记录；配合 **HNSW / IVF 等 ANN（近似最近邻）索引**加速，不用和库里每条都比，只在少量候选里精查，效率极高；
- 存的时候：信息转成向量和原文一起存；取的时候：问题也转成向量，找语义最相关的记忆拿出来。
**长期记忆按类型细分三种，分库存储召回质量更高**：
| 子类型 | 存什么 | 回答的问题 |
|:---|:---|:---|
| 语义记忆（Semantic） | 事实性知识（「用户是 Python 开发者」「预算上限 5 万」） | 「是什么」 |
| 情节记忆（Episodic） | 具体事件经历，带时间线和因果（「上周写爬虫因反爬改了三次方案」） | 「之前怎么处理过类似情况」 |
| 程序记忆（Procedural） | 「怎么做某事」的方法论/行为模式（「先确认风格偏好，再写主逻辑，最后加注释」） | 「该按什么流程来」 |
```python
from openai import OpenAI
import chromadb
client = OpenAI()
# ChromaDB 是轻量向量数据库，适合本地开发
db = chromadb.Client()
# 「集合」类似关系数据库的表，用来存 Agent 的长期记忆
collection = db.get_or_create_collection("agent_memory")
def save_to_long_term(content: str, metadata: dict):
    # 第一步：把文字转成 embedding 向量（几百维浮点数列表）
    embedding = client.embeddings.create(
        input=content, model="text-embedding-3-small"
    ).data[0].embedding
    # 第二步：向量、原文、元信息一起存进向量数据库
    # metadata 非常关键，是记忆的「标签」，检索时可按标签过滤
    # 比如只查「coding 类型」或「最近 7 天」的记忆
    collection.add(
        embeddings=[embedding],   # 索引，用于相似度检索
        documents=[content],      # 原文，命中后直接给 LLM 用
        metadatas=[metadata],     # 存入时间、任务类型、重要程度、记忆类型
        ids=[f"mem_{hash(content)}"]
    )
def retrieve_memory(query: str, top_k: int = 3) -> list[str]:
    # 查询也转成向量，必须和存储用同一个 embedding 模型，「语义距离」才有可比性
    query_embedding = client.embeddings.create(
        input=query, model="text-embedding-3-small"
    ).data[0].embedding
    # 找向量距离最近的几条：距离近 = 语义相近 = 内容最相关
    results = collection.query(
        query_embeddings=[query_embedding],
        n_results=top_k  # 只取前 top_k 条，避免检索出太多噪音
    )
    return results["documents"][0]
```
⚠️ **容易忽略的点：记忆衰减**。半年前用户说「我在学 Go」，最近三个月都在聊 Python，不做衰减就会按旧记忆处理。常见做法：
1. 给每条记忆加「**新鲜度权重**」，检索排序同时考虑语义相似度和时间，越久远权重越低；
2. 定期让 LLM 审查记忆库，把过时、矛盾的记忆标记失效或合并更新，保持记忆库健康。
### 粒度问题：一次存多少？
- **粒度太细**（每句话存一条）：完整偏好被拆成四五条，检索只命中部分 → 记忆碎片化，LLM 拿到不完整信息；
- **粒度太粗**（整次任务存一条）：2000 token 里真正相关的可能只有 100，LLM 在无关内容里找信息易被干扰；
- ✅ **合理粒度**：按「**一次完整交互**」（用户请求 + 处理结果，信息完整性好）或「**一个独立知识点/事件**」（如「用户偏好：Python、简洁风格、英文注释」打包成一条结构化记录，一次拿全不碎片化）；过程中的细小中间结果不用存长期记忆，短期记忆够用。
### 两层记忆怎么配合
- 短期记忆在**任务执行过程中**起作用，是时刻变化的「工作台」，保证当前任务连贯性；
- 长期记忆在**任务开始前检索注入、任务结束后写入沉淀**，是「档案」，保证跨任务积累。
```python
def run_agent_with_memory(user_request, long_term_memory, short_term_memory):
    # 第一步：任务开始前检索长期记忆，「想起」相关历史经验和用户偏好
    relevant_memories = long_term_memory.retrieve(user_request, top_k=3)
    # 第二步：检索结果注入 system prompt，作为背景知识影响本次任务
    system_prompt = f"""你是一个智能助手。
以下是用户的相关历史信息，请在处理任务时参考：
{chr(10).join(relevant_memories)}"""
    short_term_memory.add("system", system_prompt)
    short_term_memory.add("user", user_request)
    # 第三步：任务执行全程靠短期记忆维持状态，中间结果追加进 messages
    result = execute_task_with_short_term_memory(short_term_memory)
    # 第四步：任务完成后把重要结论写入长期记忆，沉淀下来供下次用
    if result.is_important:
        long_term_memory.save(
            content=result.summary,
            metadata={"task_type": "coding", "timestamp": now()}
        )
    return result
```
典型场景：第一天「优化 Python 代码」→ 任务后把「偏好 Python、风格简洁、英文命名」存长期记忆；两天后「写个网页爬虫」→ 任务前检索拿回偏好注入 system prompt → 输出自然符合偏好，用户感觉「这个 AI 真了解我」。
## ⚠️ 面试常见踩雷点
1. **长期记忆说成「存数据库靠关键词搜索」**：暴露不懂向量检索——核心是 Embedding + 向量数据库，靠语义相似度而非字符串匹配，「代码习惯」和「Python 风格偏好」关键词不重叠也能命中；
2. **以为粒度越细越好**：粒度太细导致记忆碎片化、检索拿到不完整信息；合理粒度是「一次完整交互」或「一个独立知识点」；
3. **搞不清两层记忆的作用时机**：短期记忆是任务执行中的工作台、任务结束即清空；长期记忆是任务前检索注入、任务后写入沉淀，分工不同、配合使用。
## ✅ 答题 checklist
- [ ] 说清短期记忆 = messages 列表，每次调 LLM 传完整历史，任务结束清空；
- [ ] 说清长期记忆 = Embedding + 向量数据库 + 语义检索（能提到 ANN 索引、metadata 过滤标签更佳）；
- [ ] 主动答粒度权衡：太细碎片化、太粗信息干扰，按「完整交互/独立知识点」存；
- [ ] 用「任务前检索注入 → 执行中短期记忆维持 → 任务后写入沉淀」的完整流程收尾；
- [ ] 加分项：长期记忆三子类型分库存储、记忆衰减（新鲜度权重/定期审查）、结构化工作记忆。
