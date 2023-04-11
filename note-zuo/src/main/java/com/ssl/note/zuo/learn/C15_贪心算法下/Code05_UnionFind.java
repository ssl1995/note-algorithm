package com.ssl.note.zuo.learn.C15_贪心算法下;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code05_UnionFind {

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    /**
     * 实现一个并查集
     * 解决：图中连通性的问题
     */
    public static class UnionFind<V> {
        /**
         * 样本对应包装的Node
         */
        public HashMap<V, Node<V>> nodes;

        /**
         * 每个节点对应的父亲
         */
        public HashMap<Node<V>, Node<V>> parents;

        /**
         * 决定谁挂谁,保存每个节点的含有的节点数
         */
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        /**
         * 查找一个结点的父节点
         * 给你一个节点，请你往上到不能再往上，把代表返回
         */
        public Node<V> findFather(Node<V> cur) {
            // 记录沿途经过的节点
            Stack<Node<V>> path = new Stack<>();
            // 不能再往上的节点：自己的父亲就是自己
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            // 优化：变扁平：沿途经过的节点，平均重新挂在到父节点上，目的是减少下次查找的时间复杂度
            // 算法导论证明：如果样本量find次数达到N,再次查找findF的时间复杂度就是O(1)
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        /**
         * 查找两个节点是否需要同一个集合
         */
        public boolean isSameSet(V a, V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        /**
         * 合并两个节点
         * 小挂大
         */
        public void union(V a, V b) {
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                // 优化：小挂大，减少操作
                parents.put(small, big);
                // 更新大
                sizeMap.put(big, aSetSize + bSetSize);
                // sizeMap只保留代表结点的记录
                sizeMap.remove(small);
            }
        }

        public int size() {
            return sizeMap.size();
        }

    }
}
