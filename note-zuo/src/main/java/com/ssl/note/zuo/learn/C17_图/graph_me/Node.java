package com.ssl.note.zuo.learn.C17_图.graph_me;

import java.util.ArrayList;

/**
 * 点结构的描述
 */
public class Node {
    public int value;
    public int in;
    public int out;
    /**
     * 从它直接出发的到达的点是那些
     */
    public ArrayList<Node> nexts;
    /**
     * 从它直接出发经过的边有哪些
     */
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
