package com.ssl.note.zuo.learn.C17_图.graph_me;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图数据结构
 */
public class Graph {
    /**
     * 针对输入int a = 1.
     * map< a,对应的node结构 >
     */
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
