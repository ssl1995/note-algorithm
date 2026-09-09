# 简历项目上下文（跨会话交接文档）

> 用途：新会话窗口读取本文档即可了解全部历史背景，继续修改操作。
> 最后更新：2026-09-09（V1 简历定稿 + 复习手册输出完成）

---

## 1. 个人情况

- 95 年男，211 计算机硕士（北京林业大学 软件工程 2020.09-2023.06，**非全日制**，简历上不标注，面试口径：边工作边读）
- 本科：贵州师范学院 特殊教育（2013-2017），跨专业
- 工作经历：2021.09-2022.03 脉脉（实习）→ 2022.03-2023.03 滴滴（实习）→ 2023.03-2026.04 瓴岳科技/洋钱罐金融（正式，Java 后端）
- 简历写"工作年限 5 年"（用户决定：2021 年起边工作边读非全，实习与学业重叠，不单独标注实习）
- 求职意向：Java 后端开发工程师（北京）；2024、2025 连续两年 A 档绩效
- 已离职（2026.04），空窗期口径需准备

## 2. 关键飞书文档

| 文档 | 链接 | 说明 |
|---|---|---|
| 简历 v1（已定稿 ✅） | https://my.feishu.cn/wiki/CoLcwykKQiDjwrkhH3MckIIrnhd | document_id: CoLcwykKQiDjwrkhH3MckIIrnhd，可直接投递 |
| 个人技能（2023版/2026版两个 h1） | https://my.feishu.cn/wiki/FN2OwF16yi7t2EkvFZ9cPlAPnZf | 2026版 h1 block_id: EfRNdbuH2oIlAMxJYJhcDDORngg |
| 项目经验（老版本/新版本两个 h1） | https://my.feishu.cn/wiki/FlsnwTFqIilyrZkBuPrcHvu3nBb | 新版本 h1 block_id: AxX0dnGImo49gvxrG9Qc4tjNnjb；含修改建议+定稿版+弹药库 |
| 复习背诵手册（简历八股） | https://my.feishu.cn/wiki/Bo8ewwGcIis0SqkM0BccCR6Dnqd | 五部分：Crane/OA 问答、技能速测、必背口径、自查表 |
| AI Agent 概念笔记 | https://my.feishu.cn/wiki/Y9hcw9UI6iiM8FkjbYNcQPTunGc | V2 素材来源 |
| 八股文整理背景 | 本地 04-resume/八股文整理背景.txt | 内含 9 个八股文档链接（Java基础/集合/并发/JVM/MySQL/SSM/SpringBoot/网络×2；Redis、Kafka 暂无 ⚠️） |

## 3. 本地代码库（简历事实来源）

| 项目 | 路径 | 说明 |
|---|---|---|
| Crane 权限中台 | D:\project\crane-backend | 用户独立负责；文档在 docs/crane-doc（01-15） |
| OA 系统 | D:\project\yqg_oa | 团队协作，用户深度参与（git 账号 songshenglin，87 次提交排第二）；文档在 doc/oa-knowledge |

### Crane 代码证据（写简历/面试追问的事实依据）
- 权限申请入口：`crane-admin/.../controller/internal/permission/PermissionApplyController.java`
- 核心服务：`crane-core/.../service/impl/RoleApplyServiceImpl.java`（L269 submitRoleFlow / L473 flowCallBack / L597 openEmployeeRelationRole）
- 三层架构：`PermissionApplyFlowFactory` + `AbstractPermissionApplyFlow`（@PostConstruct 自注册，4 种申请类型路由）
- 分布式锁：`crane-core/.../lock/CommonLock.java` + `CommonLockAspect.java`（Redisson，粒度=工号+业务ID）
- 查询优化：`EmployeeAuthorityServiceImpl`（V1 串行→V2 CompletableFuture+TTL→V3 批量合并）、`EmployeeRedisCache`（256 桶分桶+Snappy）、`FetchAuthorityThreadContext`
- 技术栈事实：SpringBoot 1.5 + JDK8 + MyBatis + MySQL8 + Redis/Redisson + Kafka + MongoDB + Feign(自研注解) + YJob 120+ 定时任务；**无 SpringCloud 组件**
- 业务量口径：日均 RPC **十万级**（用户已将百万级改为十万级，全篇统一）

### OA 代码证据
- 员工异动：`oa-core/.../employee_changes/other/EmployeeChangesOtherServiceImpl.java`、`EmployeeChangesOtherCallback.java`（动态审批人 fetchEmployeeChangesOtherDynLeaders、快照回滚、企业微信同步）
- Moka 集成：`oa-core/.../service/moka/MokaServiceImpl.java`（L228 submitOrResubmitFlow 幂等、L1375 autoEntryV2）、`MokaFlowCallBack.java`、`MokaFlowType`（MRF/MOF/MJF）、13 个 public-api 回调端点、`MokaAutoEntrySubmitV2Job.java`、429 限流修复（threadSleep=1000ms）
- OA 技术栈：SpringBoot + SpringCloud **Eureka** + JOOQ + MySQL + Redis/Redisson + Kafka + ES + YJob
- ⚠️ 勿写：年度晋升批量异动、下级联动异动（employee_changes_sub）、会议室时间段运算（无用户提交记录）

## 4. V1 简历定稿内容摘要

- **个人技能 15 条**：精通并发、精通 JVM（含 Arthas/jstack/jmap）、熟练 Java 基础/算法设计模式/MySQL/Redis、熟练 Spring 系、**了解** SpringCloud Alibaba（已降级，项目无使用）、熟悉分布式方案（YJob/XXL-Job）/Kafka/权限系统/网络、工具链、了解 AI Agent、熟练 Cursor/Claude Code
- **项目经验**：Crane（独立负责，4 条 bullet：审批流三层架构/并发与一致性/查询三代演进/生命周期自动化）+ OA（深度参与，4 条 bullet：异动审批流 0 到 1/生效同步与字段改造/Moka 双向对接/自动入职与数据同步）
- **自我评价**：代码规范 + 连续两年 A 档绩效（健身那条已删）

## 5. 待办与风险清单

| 优先级 | 事项 | 状态 |
|---|---|---|
| ⚠️ 高 | Redis 八股补课（持久化/主从哨兵/大key热key），技能栏写"熟练掌握"但无整理文档 | 未开始 |
| 中 | 查询接口 V1→V3 的 TP99/耗时数字，回忆后补入简历 bullet 3 | 未补 |
| 中 | Kafka 八股整理（目前参考模板描述） | 未开始 |
| 面试前 | 必背口径 5 个（离职空窗/非全学历/SpringCloud 追问/最难问题/优势）——复习手册第四部分已有参考答法 | 待背 |

## 6. V2 版本目标（AI 方向）

- 现状：V1 仅技能栏 2 条 AI（了解 Agent 概念 + 熟练 AI 工具），项目经验无 AI
- V2 思路：把真实 AI 实践写成项目条目，可用素材：
  - Crane 的 CLAUDE.md 沉淀 20 条代码规范、CR 阶段 AI 自动校验（真实存在）
  - SKILL.md 编写实践、Cursor/Claude Code 提效量化（代码占比/案例）
  - AI Agent 概念体系（飞书笔记 Y9hcw9UI...）：LLM/Agent 区别、Workflow vs Agent、ReAct/Plan-and-Execute、Function Call/MCP/Skills/A2A
- 原则：不硬编 AI 项目，基于真实使用沉淀可量化素材

## 7. 工具环境备忘

- lark-cli 不在 PATH：`export PATH="$PATH:/c/Users/87291/AppData/Roaming/npm"`（版本 1.0.92）
- 读飞书文档：`lark-cli docs +fetch --doc <url> --as user`；改文档：`docs +update --command str_replace|block_replace|block_insert_after|append --as user`
- Windows bash 输出中文需 `PYTHONIOENCODING=utf-8 python -c ...` 处理 JSON
- 本目录其他文件：resume.txt（任务入口）、个人情况.txt、八股文整理背景.txt、项目经验.txt
