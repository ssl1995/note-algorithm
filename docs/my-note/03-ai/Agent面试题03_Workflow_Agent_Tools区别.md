# Agent 面试题 03：Workflow、Agent、Tools 这三个的概念和区别介绍一下？
> 来源：https://xiaolinnote.com/ai/agent/3_workflow_tools.html
## 🎯 核心考点一句话
三者是**粒度从小到大、可相互嵌套的三层结构**（不是三选一），最核心的区分角度只有一个：**谁来做「下一步该干什么」这个决策**。
## 💡 简要回答（面试可直接背）
- **Tools** 是最小的能力单元：封装好的可调用函数（搜索、执行代码、发邮件），只负责「执行」，本身没有任何决策能力；
- **Agent** 是完整的决策系统：内部用 LLM 做大脑，自己判断什么时候调哪个 Tool、要不要继续、什么时候结束，是主动的；
- **Workflow** 是更上层的编排框架：把 Agent、LLM、Tools 组织成确定性流程，每个节点做什么、按什么顺序流转都是开发者事先写死的。
一句话总结：**Tools 不做决策只执行，Agent 自己做决策，Workflow 是开发者替所有节点把决策提前写好。**
## 📝 详细解析
### 第一层：Tools——最小的能力积木
- 本质：**按特定格式暴露给 LLM 的函数**。普通函数给程序员调用，Tool 给 LLM 调用，所以必须配一份 LLM 看得懂的 schema（名字、描述、参数类型），除此之外和普通函数无本质区别；
- ⚠️ 关键设计：**工具本身没有任何决策能力，甚至不知道自己「应该」什么时候被用**——这不是缺陷，是故意为之。Tools 的使命是把能力封装好随时待命，「什么时候用」是别人的事；
- 类比：瑞士军刀的刀片，每个刀片各有擅长，但刀片不会自己说「现在该把我翻出来」，**决定拿哪个刀片的是握着刀的手（Agent）**。
```python
# 工具定义只有「说明书」，没有任何决策逻辑
tools = [
    {
        "name": "web_search",
        "description": "在互联网上搜索信息，适合查询实时数据或不确定的知识",
        "parameters": {
            "type": "object",
            "properties": {
                "query": {"type": "string", "description": "搜索关键词，越具体越好"}
            },
            "required": ["query"]
        }
    }
]
# 工具的实际执行逻辑单独写，和「说明书」是分开的
def execute_web_search(query: str) -> str:
    ...  # 真正发 HTTP 请求搜索的代码
```
**🔥 工具设计四原则（很多 Agent 表现不好根源在工具设计而非 LLM）**：
1. **职责单一**：一个工具只做一件事，太杂会让模型难以判断何时该用；
2. **描述精确**：模型完全靠 description 理解工具用途，含糊会导致误调/漏调（「查询数据」❌ →「查询公司内部销售数据库，支持按日期和产品类别筛选」✅）；
3. **错误信息清晰**：「参数 city 不能为空」比「Error code 400」好得多，前者能帮 LLM 自行修正参数重试；
4. **参数设计简洁**：能少传就少传、能有默认值就给默认值，LLM 填的参数越多出错概率越大。
行业趋势：工具增多后管理和发现成为工程问题 → MCP 协议（工具世界的「USB 接口」）标准化注册、描述、调用。
### 第二层：Agent——拿着工具自己做决定的人
- Agent 是那个「拿着工具、自己决定用哪个」的角色；「要不要、用哪个、够不够、停不停」全部由内部 LLM 决策；
- 与 Tools 最本质的区别：**Tools 被动等待调用，Agent 主动做决策**；
- 运行方式：**Thought → Action → Observation → 再 Thought……** 循环直到 LLM 判断任务完成。
```python
def run_agent(user_goal: str):
    messages = [{"role": "user", "content": user_goal}]
    # 核心：不断循环的决策过程，开发者不知道会跑几次，完全由 LLM 决定
    while True:
        response = client.messages.create(
            model="claude-opus-4-6", max_tokens=1024,
            tools=tools,        # 传「工具说明书」让 LLM 知道有哪些能力
            messages=messages
        )
        if response.stop_reason == "end_turn":   # LLM 判断任务完成
            return response.content[0].text
        # LLM 只「告诉调哪个工具、传什么参数」，真正执行的是代码
        tool_use = next(b for b in response.content if b.type == "tool_use")
        tool_result = execute_tool(tool_use.name, tool_use.input)
        # 结果塞回对话历史，LLM 下一轮看到后再决策
        messages.append({"role": "assistant", "content": response.content})
        messages.append({"role": "user", "content": [
            {"type": "tool_result", "tool_use_id": tool_use.id, "content": tool_result}]})
```
- ⚠️ 高频追问点：**`while True` 死循环怎么办？——停止条件（Stop Condition）**，生产环境必备，通常多种机制并存、哪个先触发用哪个：
  1. LLM 主动判断任务完成（最理想）；
  2. 最大循环次数（如最多 15 轮，强制停止并返回当前结果）；
  3. 总 token 预算上限（防止成本失控）；
  4. 超时机制（如超过 60 秒终止）。
- Agent 的副作用：**行为不确定**。同样任务两次运行可能走不同路径、结果微妙不同（LLM 是概率模型）。**灵活性和不确定性是孪生兄弟**；结果是出错时难以复现路径 → 生产环境需加详细执行日志记录每步思考和工具调用，便于事后追溯。
### 第三层：Workflow——把所有人组织起来的总指挥
- **把整个执行流程的「骨架」写在代码里，LLM、Agent、Tools 都只是流程里的「节点」**；走哪条路、下一步去哪全由开发者代码决定；
- ⚠️ 纠正常见误解：Workflow 的节点**不必须是 Agent**，可以是 LLM 调用、Tools 或 Agent——关键不是节点类型，而是**控制流由谁掌握**。
```python
def run_customer_service_workflow(user_query: str) -> str:
    # 第一步：意图识别——LLM 只当分类器用，「下一步去哪」由 if/elif 决定
    intent = classify_intent_with_llm(user_query)  # "product" / "refund" / "other"
    # 第二步：分支判断是开发者写的 Python 代码，不是 LLM 的决策
    if intent == "product":
        docs = search_knowledge_base(user_query)        # 调 Tool 检索
        return generate_answer_with_llm(user_query, docs)  # LLM 作为节点生成回答
    elif intent == "refund":
        order_info = query_order_system(user_query)
        if order_info["eligible"]:
            process_refund(order_info["order_id"])
            return "退款已受理，预计 3 个工作日到账"
        else:
            return "很抱歉，该订单不满足退款条件"
    else:
        escalate_to_human_agent(user_query)
        return "已为您转接人工客服，请稍候"
```
- LLM 在其中出现两次（意图分类、生成回答），但**只是流程里的两个工位**，「接下来去哪」完全由 if/elif 控制；
- Workflow 最大优点：**可预测、可控、好调试**——代码里看到什么就做什么，出问题可精确定位节点，这种确定性在线上系统非常珍贵。
### 三者对比表
| 维度 | Tools | Agent | Workflow |
|:---|:---|:---|:---|
| 决策能力 | 无（只执行不决策） | 有（LLM 自主动态决策） | 无（开发者代码写死） |
| 执行方式 | 被动等待被调用 | 主动自主循环直到完成 | 按开发者定义顺序执行 |
| 确定性 | 高（输入固定输出固定） | 低（同输入可能走不同路径） | 高（行为完全可预测） |
| 灵活性 | 只做一件事 | 高（能应对预料外情况） | 低（流程写死难动态调整） |
| 调试难度 | 容易（单一函数） | 难（执行路径不确定） | 容易（链路清晰可追踪） |
| 适用场景 | 封装单一具体能力 | 路径未知的复杂任务 | 流程相对固定的业务系统 |
### 🔥 三者怎么组合？Agentic Workflow 才是生产主流
- 纯 Agent：行为难控制、出问题难排查、成本易失控 → 生产环境很少用；
- 纯 Workflow：太脆，无法穷举所有情况，遇到预料外输入易失败；
- ✅ **Agentic Workflow（生产最主流）**：**用 Workflow 固定主流程骨架，在需要灵活判断的节点嵌入 Agent，其余固定节点直接用 LLM 或 Tools**——骨架确定（可控可调试）+ 关键节点灵活（应对复杂情况），两者优点兼得、缺点互削；
- 成本角度：纯 Agent 一个复杂任务可能跑十几~几十轮决策循环，每轮都发完整上下文，token 线性增长、延迟累积；Workflow 可精确控制每节点 token 预算、该并行的并行，延迟和成本更可控。很多团队从原型（纯 Agent 快速验证）过渡到生产时会重构成 Agentic Workflow。
**Anthropic 总结的常见 Workflow 编排模式（面试加分项）**：
1. **Prompt Chaining（提示链）**：大任务拆成多小步，前一步输出作为后一步输入，流水线串联；
2. **Routing（路由）**：LLM 先做分类判断，再分发到不同处理分支（客服系统即典型路由）；
3. **Parallelization（并行化）**：可同时进行的子任务并行执行后汇总（如同时从多数据源检索）；
4. **Orchestrator-Workers（编排者-工人）**：中央编排者分配任务，多个 Worker 各自完成独立子任务；
5. **Evaluator-Optimizer（评估者-优化者，实用但常被忽略）**：一个 LLM 生成、另一个评估质量，不通过则反馈改进、循环重试直到通过或达到上限。适合营销文案、法律条款、代码等高质量场景，本质是自动化「审稿-修改」过程。⚠️ 评估标准必须在代码里定义清楚（如打分函数），不能让评估者自由发挥。
以上模式不互斥，实际项目常混合使用。
## ⚠️ 面试常见踩雷点
1. **把 Workflow 理解成「多个 Agent 串联」**：错误。节点可以是任意 LLM 调用、Tools 或 Agent，关键是控制流由谁掌握；
2. **把三者当成三选一**：实际是粒度不同、可相互嵌套的三层结构，真实项目中三者通常同时存在；
3. **忽略「谁做决策」这个核心维度**：Tools 无决策、Agent 由 LLM 运行时动态决策、Workflow 决策提前写死在代码里。
## ✅ 答题 checklist
- [ ] 点明三者是粒度从小到大的嵌套结构（Tools → Agent → Workflow），不是并列三选一；
- [ ] 抓住核心区分角度「谁做决策」，并能展开三者的确定性/灵活性/调试难度差异；
- [ ] 必补一句：生产主流是 **Agentic Workflow**（Workflow 定骨架 + 关键节点嵌 Agent）；
- [ ] 加分项：Agent 停止条件（最大轮数/token 预算/超时）、工具设计四原则、Anthropic 五种编排模式。
