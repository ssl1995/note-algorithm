# CLAUDE.md

本文件为 Claude Code (claude.ai/code) 提供项目指导，帮助其更好地理解和处理本仓库的代码。

## 项目概述

这是一个 Java 算法练习仓库，包含两个 Maven 模块：
- **note-leetcode**: LeetCode 题目，按题号组织，包含"剑指Offer"系列
- **note-zuo**: 算法课程材料，按章节组织（C01-C40+）

两个模块都使用 Spring Boot 2.6.7 和 Java 8。代码和注释均为中文。

## 构建与测试

### 构建命令
```bash
# 构建所有模块
mvn clean install

# 构建指定模块
cd note-leetcode && mvn clean install
cd note-zuo && mvn clean install

# 跳过测试构建
mvn clean install -DskipTests
```

### 运行题解
每个算法题解都有自己的 `main` 方法用于测试。可以直接在 IDE 中运行，或使用：
```bash
# 运行指定的题解类
mvn exec:java -Dexec.mainClass="com.ssl.note.leetcode.编号刷题.LC169_多数元素.Solution"
```

### 测试模式
本项目使用"对数器"模式而非 JUnit 测试：
- 题解包含带测试用例的 `main` 方法
- 随机测试数据生成器（如 `generateRandomArray` 等）
- 与简单/正确实现的对比（`comparator` 方法）
- 大规模验证（例如 50 万次测试迭代）

## 代码组织

### note-leetcode 模块
```
com.ssl.note.leetcode/
├── 编号刷题/               # 按 LC 题号组织（LC1, LC169 等）
│   └── LC{number}_{题目名}/
│       └── Solution.java
├── 剑指Offer/              # "剑指Offer"系列
│   └── 第三版/
│       └── 第{n}章_{主题}/
└── utils/                 # 公共数据结构（TreeNode, ListNode 等）
```

### note-zuo 模块
```
com.ssl.note.zuo/
├── learn/                 # 课程章节 C01-C40+
│   └── C{number}_{主题}/
│       └── Code{number}_{题目}.java
├── bilibili/              # 附加内容
└── utils/                 # 公共数据结构（TreeNode, ListNode, Node）
```

## 编码规范

### 文件创建
- 每个算法题目都有自己的包，命名为：`LC{number}_{中文题目名}` 或 `C{number}_{中文主题}`
- 每个题解都是独立的类，包含 `main` 方法
- 可以在题目包中包含图片/笔记（img.png, note.md）

### 题解结构模式
```java
public class Solution {
    /**
     * 问题描述（中文）
     */
    public int solutionMethod(int[] nums) {
        // 算法实现
    }

    public static void main(String[] args) {
        // 测试用例
        Solution solution = new Solution();
        // 运行测试
    }
}
```

### 公共工具类
- `TreeNode`: 二叉树节点（val, left, right）
- `ListNode`: 链表节点（val, next）
- `Node`: 通用节点类
- 两个模块都有各自的 utils 包包含这些结构

### 代码风格
- 注释和问题描述使用中文
- 不使用 Javadoc 注释（@param, @return 等）
- 方法名和变量名使用英文/驼峰命名
- 保持题解独立且可运行

## 重要说明

- 题目按提交历史时间顺序组织（参考 git log）
- 每个题目通常在注释中包含问题描述
- 题解可能包含多种方法或优化
- "对数器"模式是标准测试方法 - 除非明确要求，否则不要添加 JUnit 测试
