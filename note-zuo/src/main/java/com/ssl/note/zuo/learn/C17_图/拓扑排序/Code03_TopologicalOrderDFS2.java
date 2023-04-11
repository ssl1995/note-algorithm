package com.ssl.note.zuo.learn.C17_图.拓扑排序;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

// OJ链接：https://www.lintcode.com/problem/topological-sorting
public class Code03_TopologicalOrderDFS2 {

    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    // 提交下面的
    public static class Record {
        // 当前节点
        public DirectedGraphNode node;
        // 当期节点后面还有多少个拓扑排序结点的点次
        public long nodes;

        public Record(DirectedGraphNode n, long o) {
            node = n;
            nodes = o;
        }
    }

    public static class MyComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            return Long.compare(o2.nodes, o1.nodes);
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // 相当于cache缓存
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            f(cur, order);
        }
        ArrayList<Record> recordArr = new ArrayList<>();
        for (Record r : order.values()) {
            recordArr.add(r);
        }
//        recordArr.sort(new MyComparator());
        recordArr.sort((r1, r2) -> Long.compare(r2.nodes, r1.nodes));
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record r : recordArr) {
            ans.add(r.node);
        }
        return ans;
    }

    // 当前来到cur点，请返回cur点所到之处，所有的点次！
    // 返回（cur，点次）
    // 缓存！！！！！order
    //  key : 某一个点的点次，之前算过了！
    //  value : 点次是多少
    public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        // cur的点次之前没算过！
        long nodes = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            nodes += f(next, order).nodes;
        }
        // 再加上自己的点次
        Record ans = new Record(cur, nodes + 1);
        order.put(cur, ans);
        return ans;
    }

}
