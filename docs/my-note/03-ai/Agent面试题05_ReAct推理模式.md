# Agent 面试题 05：Agent 推理模式有哪些？ReAct 是啥？具体是怎么实现的？
> 来源：https://xiaolinnote.com/ai/agent/5_react.html
## 🎯 核心考点一句话
ReAct 的本质是 **「思考 → 行动 → 观察」的循环**，而面试官最想听到的关键点是：**这个循环不是模型自己在转，是由你的代码框架驱动的**——模型每次只输出 Thought + Action，代码负责解析、执行工具、把 Observation 填回历史再调模型。
## 💡 简要回答（面试可直接背）
- 推理模式从最基础到进阶：**直接输出答案**（无中间推理）→ **CoT**（先把推理过程写出来再给答案，准确率更高）→ **ReAct**（在 CoT 基础上加「行动」，交替输出思考和工具调用，每次行动后根据结果继续思考，形成循环）；
- ReAct 是目前 Agent 用得最广的模式：**推理过程可见 + 能动态利用外部工具**，两个优点兼得；
- 🔥 关键分工：**模型负责推理，代码负责驱动**——模型不会自己「循环」，它每次只根据历史输出下一步的 Thought + Action，代码检测输出、执行工具、把 Observation 填回历史、再次调用模型。
## 📝 详细解析
### 为什么需要「推理模式」？
- LLM 一个 token 一个 token 地往后预测，遇到需要多步推导的问题时，「一口气」预测答案，中间推导是隐式的，**误差在暗处悄悄累积**，最终暴露在答案里；
- 类比心算 vs 笔算：把每一步写在纸上，出错概率大大降低——不是变聪明了，而是「写下来的过程」帮助避免中间某步跳跃出错；
- **推理模式存在的根本原因：让 LLM 把隐式的思考过程显式化，减少多步推理中的累积误差**。CoT、ReAct 是这个方向上的两种解法，各自解决前者的局限。
### CoT（Chain of Thought，思维链）
- Wei 等人 2022 年提出，最早也最简单的解法：prompt 里加一句**「让我们一步步思考」**，LLM 就会先把推理步骤写出来再给答案；
- 有效的本质：LLM 输出是顺序生成的，**先写出的推理内容进入上下文，成为后续生成的依据**，下一步预测建立在已写出的正确基础上，而不是靠「暗中维持」中间状态；
- 两种触发方式：
| 方式 | 做法 | 优点 | 缺点 |
|:---|:---|:---|:---|
| Zero-shot CoT | prompt 末尾直接加「让我们一步步思考」 | 零成本、即插即用 | 推理格式和深度靠模型自己发挥，不稳定、偶尔跳步 |
| Few-shot CoT | 在 prompt 里给几个带完整推理过程的示例，让模型照格式模仿 | 效果更稳定，适合输出格式固定的场景 | 需提前准备高质量示例，示例本身占 token |
- ⚠️ CoT 的根本局限：**纯文字推理，无法和外部世界交互**——拿不到实时数据、不能执行计算、不能访问数据库（训练知识可能过时几个月）；CoT 的设计本身没有把推理和工具调用交织在一起，接工具需要自己在外面加胶水；
- 于是需要**一个能在推理过程中「出去拿数据」「执行工具」再「回来继续推理」、且这件事是原生设计的系统 → ReAct**。
### ReAct 是什么？
- **Reasoning and Acting** 缩写，Yao 等人 2022 年提出，核心思路：在 CoT 的推理链里插入真实的「行动」，按**「思考 → 行动 → 观察」循环**推进任务，直到 LLM 判断任务完成；
- 🔥 为什么不用纯 CoT 或纯 Act-only？这个对比非常关键：
  - **纯 CoT**：只能脑内推理，拿不到真实数据，且没有外部事实校准**容易产生幻觉**；
  - **纯 Act-only**（直接输出工具调用序列、不写思考）：效率看似高，但步骤间没有推理链连接，动作序列很脆弱——某一步搜到不相关内容，后面动作全跟着跑偏，没有「思考」环节纠正方向。HotpotQA 多跳问答基准上 Act-only 准确率明显低于 CoT 和 ReAct；
  - **ReAct 的精妙在于推理与行动交织**：Thought 提供方向、Action 落地为真实操作、Observation 带回外部反馈，三者互补形成闭环。**推理为行动提供方向，行动为推理提供事实**；
- 一个完整循环示例（问「2024 年苹果和谷歌市值谁更高？差多少？」）：
```text
Thought: 这道题需要两家公司的实时市值数据，我得先查苹果的市值
Action: search
Action Input: 苹果公司 2024 年市值
Observation: 苹果公司 2024 年市值约为 3.5 万亿美元
Thought: 好，苹果的数字有了，再查谷歌的
Action: search
Action Input: 谷歌 2024 年市值
Observation: 谷歌 2024 年市值约为 2.1 万亿美元
Thought: 两个数字都有了，苹果 3.5 万亿，谷歌 2.1 万亿，苹果更高，差距是 1.4 万亿
Final Answer: 苹果公司 2024 年市值约 3.5 万亿美元，谷歌约 2.1 万亿美元，苹果更高，差距约 1.4 万亿美元
```
- 每个 Thought 是 LLM 的推理，每个 Action 是它决定调什么工具，每个 Observation 是系统填进去的真实结果，**Final Answer 是任务完成的终止信号**。
### ReAct 的实现原理（高频追问点）
- 🔥 **循环不是 LLM 自己在转，是代码驱动的**：LLM 每次只做一件事——根据当前历史输出下一步 Thought + Action；代码负责检测输出：有 Final Answer 就返回，没有就解析 Action、执行工具、把结果作为 Observation 填回历史、再次调 LLM；
- 典型 ReAct prompt 格式：
```text
你是一个 AI 助手，可以使用以下工具：
- search(query): 搜索互联网获取最新信息
- calculator(expr): 计算数学表达式
回答时请严格按照以下格式：
Thought: 你的思考过程（分析当前情况，决定下一步）
Action: 工具名称
Action Input: 工具的输入参数
Observation: （此行由系统填入工具返回的结果，你不用写）
... 以上可以重复多轮 ...
Final Answer: 当你确定可以回答时，在这里给出最终答案
问题：2024 年苹果公司的市值是多少？和谷歌相比谁更高？
```
- 驱动循环的代码：
```python
def react_agent(question: str, tools: dict, max_steps: int = 10):
    # 把 ReAct 格式约束和问题拼在一起，作为初始 prompt
    prompt = build_react_prompt(question, tools)
    history = []  # 存每一轮对话历史，每次调 LLM 都把完整历史带上
    for _ in range(max_steps):
        # 调 LLM 输出下一步 Thought + Action（每次带上完整历史）
        response = llm.generate(prompt + "\n".join(history))
        if "Final Answer:" in response:   # LLM 判断任务完成
            return response.split("Final Answer:")[-1].strip()
        # 解析 Action 名称和 Action Input，执行对应工具
        action, action_input = parse_action(response)
        if action in tools:
            observation = tools[action](action_input)
        else:
            # LLM 填了不存在的工具名，给错误反馈让它自行修正
            observation = f"工具 {action} 不存在，请选择可用工具"
        # 本轮输出（含 Thought+Action）和 Observation 追加进历史，作为下轮「记忆」
        history.append(response)
        history.append(f"Observation: {observation}")
    return "超过最大步数，任务未完成"
```
- 分工总结：**真正的「智能」全在 LLM 每次输出的 Thought 里；代码框架做的事是管理对话历史、执行工具、检测循环终止条件**；
- 补充：上面是**经典实现**（prompt 格式约束 + 文本解析）；现代 LLM（GPT-4、Claude 3 之后）原生支持 **Function Calling / Tool Use**，模型直接输出结构化 JSON 工具调用，不再靠解析 `Action: xxx` 文本，实现更干净可靠。**「思考 → 行动 → 观察」的循环没变，只是「行动」从解析文本变成解析结构化 JSON**。
### 🔥 ReAct 的两个实战大坑（面试主动说出非常加分）
根源相同：**ReAct 是纯粹的「前向推理」，走一步看一步，没有全局规划约束方向，也没有反思机制纠正错误**。
1. **循环漂移**：像没有导航的旅行者，每个路口临时决定方向，途中「风景」会吸引注意力拐进岔路。例：让它「查苹果最近三年营收趋势」，第二步搜索结果提到三星，第三步就跑去搜三星了，越走越远忘了原目标。**步骤越多、历史越长，漂移概率越大**——冗长历史里充满「诱惑」；
2. **错误传播**：每步决策建立在前面所有步骤结果之上，中间某步拿到错误信息，后面所有推理都被带跑；且 ReAct **没有内置「回头检查」机制**，默认前面的 Observation 都对，一路往前冲，早期出错整条链白费。
解决思路：既然问题出在「没有全局规划」，就先让 LLM 站在全局视角想清楚、列出完整执行计划，再逐步执行 → **Plan-and-Execute**。
### Plan-and-Execute：先规划再执行
- 类比：ReAct 是「边走边问路」，Plan-and-Execute 是「先看地图再出发」——即使中途某条路封了，也知道大方向在哪，可以绕路但不会迷路；
- 两个阶段：
  - **规划（Planner）**：LLM 只做规划不执行任何工具，站在全局角度输出结构化的分步执行计划；
  - **执行（Executor）**：按计划逐步执行，每步内部可用 ReAct 循环，但执行器**始终知道自己在整体计划中的位置**，不会像纯 ReAct 那样漫无目的漂移；
```python
def plan_and_execute(question: str, tools: dict):
    # 第一阶段：只做规划，不执行任何工具
    plan = llm.generate(f"请为以下任务制定分步执行计划：{question}（输出编号列表）")
    steps = parse_plan(plan)
    results = []
    # 第二阶段：按计划逐步执行
    for i, step in enumerate(steps):
        # 每步用 ReAct 执行，但告知其在整体计划中的位置，并传入前面步骤结果
        step_result = react_executor(task=step, tools=tools,
            context=f"整体计划共{len(steps)}步，当前是第{i+1}步",
            previous_results=results)
        results.append(step_result)
        # 关键：动态重规划——执行完一步检查是否需要调整后续计划
        if need_replan(step, step_result, steps[i+1:]):
            remaining = llm.generate(f"原计划：{steps}，已完成到第{i+1}步，结果：{results}，请输出更新后的剩余步骤")
            steps = steps[:i+1] + parse_plan(remaining)
    return llm.generate(f"根据以下执行结果回答问题：{results}")
```
- 🔥 **动态重规划**：计划不是定死的，每步执行完检查结果与预期是否一致，意外时把已有结果和剩余步骤交回 Planner 重新规划——像导航遇到封路自动重规划路线；
- 场景对比：**ReAct** 适合任务边界不明确、需探索性获取信息的场景（开放问答、信息搜索），代价是易漂移、每步带完整历史 token 线性增长；**Plan-and-Execute** 适合目标明确、多步骤协作的复杂任务（深度研究、长文写作、多工具数据分析），代价是任务太简单时规划步骤是多余开销；
- 🔥 工程混合用法：**Plan-and-Execute 做全局规划 + 每步用 ReAct 执行；规划用强模型（GPT-4/Claude Opus）、执行用便宜小模型**——规划只调一次花费有限，执行多次用便宜模型，实际项目可**降低 70%~90% LLM 调用成本**。
## ⚠️ 面试常见踩雷点
1. **以为模型自己在「循环」**：最大的误区。模型每次只输出一段文本，循环是代码框架驱动的（解析输出 → 执行工具 → 填回 Observation → 再调模型）；
2. **说不出 CoT 的根本局限**：纯文字推理、无法与外部世界交互、无外部事实校准易产生幻觉；
3. **只背定义不知局限**：ReAct 的循环漂移和错误传播两个实战坑要主动说，并能引出 Plan-and-Execute 的解决思路。
## ✅ 答题 checklist
- [ ] 说清推理模式演进：直接回答 → CoT（显式化推理减少累积误差）→ ReAct（推理与行动交织）；
- [ ] 点明 ReAct 核心循环 Thought → Action → Observation，且「推理为行动提供方向，行动为推理提供事实」；
- [ ] 🔥 必答：循环由代码驱动，模型只输出 Thought + Action，代码负责解析/执行/回填；
- [ ] 加分项：经典文本解析 vs 现代 Function Calling 的实现差异；两个坑（循环漂移、错误传播）；Plan-and-Execute 动态重规划；强模型规划 + 弱模型执行降本 70%~90%。
