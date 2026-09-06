# Agent 面试题 04：了解哪些其他的 Agent 设计范式？Agent 和 Workflow 的区别是什么？
> 来源：https://xiaolinnote.com/ai/agent/4_patterns.html
## 🎯 核心考点一句话
Agent 与 Workflow 最本质的区别是**「谁来决定下一步」**；主流设计范式有 **ReAct、Plan-and-Execute、Reflection** 三种，生产环境主流是二者混用的 **Agentic Workflow**。
## 💡 简要回答（面试可直接背）
- **Workflow** 是我提前把流程写死的确定性流程图，每一步怎么走都是固定的，确定性高、好控制；**Agent** 是把「下一步做什么」的决策权交给 LLM，灵活但不可控；
- 常见设计范式：**ReAct**（思考→行动→观察循环）、**Plan-and-Execute**（规划与执行解耦，支持动态重规划）、**Reflection**（执行后加自我评估的质量保障层）；
- 实际工程里用得最多的是**混用**：固定流程部分用 Workflow，需要灵活决策的节点嵌入 Agent 能力，既保住整体可控又有局部灵活性。
一句话总结：**生产环境可控性优先于灵活性，能用 Workflow 解决的就不用 Agent，纯 Agent 模式很少直接用。**
## 📝 详细解析
### Workflow 和 Agent 的本质区别
- **Workflow**：确定性流程图，「第一步做 A，A 完了做 B，B 失败走分支 C」全部硬编码，LLM 只是某个节点的执行工具、不负责决策流程本身。优点：行为完全可预测、易测试、出问题好排查；缺点：灵活性低，遇到未预料的情况走进死胡同；
- **Agent**：把决策权交给 LLM，只告诉它目标，它自己判断调哪个工具、该不该继续、何时算完成。优点：能处理事先没设计的情况；缺点：行为不确定，同样输入可能走不同路径，线上问题难复现。
```python
# Workflow 风格：流程固定，每步都是确定的，LLM 只是工具
def workflow_answer_question(user_query: str):
    docs = vector_db.search(user_query, top_k=5)       # 第一步：固定做向量检索
    reranked = reranker.rank(user_query, docs)         # 第二步：固定做 rerank 重排序
    answer = llm.generate(user_query, context=reranked) # 第三步：固定喂给 LLM 生成答案
    return answer
# Agent 风格：流程不固定，LLM 自己在运行时动态决定每一步
def agent_answer_question(user_query: str):
    while True:
        # LLM 自己决定：要搜索？要计算？还是直接回答？
        action = llm.decide(user_query, history=memory)
        if action.type == "search":
            result = vector_db.search(action.query)
            memory.append(result)
        elif action.type == "calculate":
            result = calculator.run(action.expr)
            memory.append(result)
        elif action.type == "final_answer":
            return action.content
```
- 代码结构上即一目了然：Workflow 每一行都是明确指令，控制流完全由代码决定；Agent 的 loop 里只有 `llm.decide()`，所有路径由 LLM 运行时动态选择。形象地说：**Workflow 是「开发者在驾驶」，Agent 是「LLM 在驾驶，开发者在副驾驶设安全限制」**。
### 范式一：ReAct（Reasoning + Acting）
- 最常用范式，核心机制是**推理与行动交替进行**，每轮循环三步：**Thought → Action → Observation**；
  - **Thought**：LLM 把当前情况分析一遍、推理过程写出来；
  - **Action**：根据思考结论决定调哪个工具、传什么参数；
  - **Observation**：工具结果反馈给 LLM，进入下一轮 Thought 重新分析；
- 🔥 为什么显式拆开三步？直接输出行动模型会「冲动决策」（没搞清楚需求就急着调工具）；先写出推理过程，决策质量更稳定，且 **Thought 可见、方便调试定位哪一步想歪了**；
- 循环终止：直到某轮 Thought 判断「信息够了」给出最终答案；
- ⚠️ 短板：**「走一步看一步」的局部最优决策**，处理需全局规划的复杂任务易迷失方向——十几步的研究任务可能做到第五步就忘了最初目标，或在几个工具间打转。
### 范式二：Plan-and-Execute
- 针对 ReAct 短板而来：**把规划和执行彻底解耦**，先让一个 LLM 专门规划、输出完整步骤列表，再由另一个 LLM（或同模型不同角色）逐步执行；
- 好处：复杂任务整体结构清晰，执行前甚至可以**人工审核计划**；
- 🔥 关键机制——**动态重规划（Replan）**：成熟的实现不是「做好计划死板执行」，而是每执行完一步把结果反馈给规划器，判断「结果与预期一致吗？后续计划还适用吗？」，偏离严重则修改后续步骤甚至插入新步骤。例：计划「搜竞品 A → 搜竞品 B → 对比分析」，执行第一步发现竞品 A 刚发布重大更新，规划器动态插入「搜竞品 A 最新更新详情」一步；
- **「计划是活的」让 Plan-and-Execute 既保持全局视野又不死板**；缺点：多了规划/重规划的 LLM 调用，延迟和成本增加，且初始规划方向错了后续很难挽回。
### 范式三：Reflection（反思）
- 在前两种范式基础上加一层**质量保障**：完成一步或整个任务后，再让一个 LLM（同模型或专门评估模型）评估做得好不好、是否符合预期，不通过则重试或换策略；
- 在代码生成、文案写作这类「质量要求高但一次做对很难」的场景效果特别明显；
- 🔥 变体 **Reflexion**：不只是「结果不好重做一遍」，而是生成具体的**「反思总结」记录失败原因和改进建议，作为额外上下文传给下一次尝试**——类比「写错题本」；
- 效果有数据支撑：HumanEval 代码生成基准上，GPT-4 直接做准确率约 80%，加 Reflexion 后提升到 **91%**；
- ⚠️ 代价：每加一轮反思就多一次甚至多次 LLM 调用，**token 消耗和延迟都增加**，需在质量与成本间取舍。
### 三种范式怎么选？
核心看两个维度：**任务复杂度和质量要求**。
- 任务步骤少、每步相对独立 → **ReAct** 够用，简单直接；
- 任务复杂、步骤间有依赖、需全局统筹 → **Plan-and-Execute**；
- 输出质量要求特别高、允许多花时间和成本 → 在前两者基础上叠加 **Reflection**；
- 三者不互斥，常混合使用：**Plan-and-Execute 做整体规划，每个步骤内部用 ReAct 执行，关键步骤加 Reflection 把关**。
### 🔥 生产主流：Agentic Workflow
- 纯 Agent 模式实际用得不多，因为太难控制；更常见做法是 **Agentic Workflow**：整体用 Workflow 框住主流程，在需要灵活处理的节点嵌入 Agent 能力；
- 例：客服系统「意图识别 → 知识检索 → 回答生成」主链路是固定 Workflow，但「知识检索」节点内部用 Agent 动态决定检索几轮、用哪些工具；
- 🔥 Anthropic 工程博客的实用原则（面试加分项）：**「能用 Workflow 解决的问题，就不要用 Agent」**。理由：生产环境可控性比灵活性更重要，Workflow 行为确定、可精确定位出错节点；Agent 行为是概率性的，测试覆盖率天然低。建议从最简单的 Workflow 开始，发现某节点确实需要灵活决策时才升级为 Agent——**「从简单到复杂、按需升级」**。
### Workflow vs Agent 对比表
| 维度 | Workflow | Agent |
|:---|:---|:---|
| 决策者 | 开发者（硬编码流程） | LLM（动态决策） |
| 确定性 | 高，行为完全可预测 | 低，同输入可能走不同路径 |
| 灵活性 | 低，流程固定 | 高，能处理预料之外的情况 |
| 调试难度 | 容易，链路清晰 | 困难，行为不确定 |
| 适用场景 | 流程相对固定的业务 | 需要灵活判断的复杂任务 |
## ⚠️ 面试常见踩雷点
1. **设计范式说不全**：只知道 ReAct，说不出 Plan-and-Execute（规划执行解耦）和 Reflection（执行后自我评估）；多 Agent 协作是架构模式，不是设计范式，别混淆；
2. **把 Reflection 当调试手段**：它是正式的运行时机制，内嵌在 Agent 执行流程里，代价是增加 token 消耗和延迟，这个取舍经常被追问；
3. **以为纯 Agent 是生产首选**：实际纯 Agent 在生产里用得很少（行为不确定、难调试、成本易失控），真正的工程答案是 Agentic Workflow。
## ✅ 答题 checklist
- [ ] 点明核心区别是「谁来决定下一步」：Workflow 开发者写死，Agent 由 LLM 运行时动态决策；
- [ ] 说出三种设计范式及各自适用场景：ReAct / Plan-and-Execute（含动态重规划）/ Reflection（含 Reflexion 错题本机制 + HumanEval 80%→91% 数据）；
- [ ] 必补一句：生产主流是 **Agentic Workflow**，能主动说出「为什么纯 Agent 在生产有局限」是拿高分关键；
- [ ] 加分项：Anthropic 原则「能用 Workflow 就不用 Agent」「从简单到复杂、按需升级」；三范式混合用法（规划 + 步骤内 ReAct + 关键步骤 Reflection）。
