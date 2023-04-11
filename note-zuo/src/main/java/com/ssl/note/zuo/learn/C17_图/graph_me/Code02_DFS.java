package com.ssl.note.zuo.learn.C17_图.graph_me;

import java.util.HashSet;
import java.util.Stack;

public class Code02_DFS {

    /**
     * 图的深度优先遍历：
     * 一条路没走完就走到死，如果走完了，就去路上找还没走完的
     */
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        // 入栈就打印
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    // 重新压入cur
                    stack.push(cur);
                    // 压入next
                    stack.push(next);
                    // 注册是否加入过
                    set.add(next);
                    // next入栈就打印
                    System.out.println(next.value);
                    // 只取nexts中的一个
                    break;
                }
            }
        }
    }


}
