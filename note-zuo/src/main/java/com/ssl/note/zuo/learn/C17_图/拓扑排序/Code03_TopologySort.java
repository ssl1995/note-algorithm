package com.ssl.note.zuo.learn.C17_图.拓扑排序;

import com.ssl.note.algorithm.learn.C17_图.graph_me.Graph;
import com.ssl.note.algorithm.learn.C17_图.graph_me.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code03_TopologySort {

    /**
     * 拓扑排序1
     * 要求：有向无环图
     * 实现：更新节点入度信息
     */
    public static List<Node> sortedTopology(Graph graph) {
        // key 某个节点   value 剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 只有剩余入度为0的点，才进入这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            // 保存每个结点的入度信息
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                // 相邻结点的入度信息-1
                inMap.put(next, inMap.get(next) - 1);
                // 如果遇到入度信息为0，加入队列
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}
