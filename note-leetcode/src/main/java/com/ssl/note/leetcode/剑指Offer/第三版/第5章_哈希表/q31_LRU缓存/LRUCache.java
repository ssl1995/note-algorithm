package com.ssl.note.leetcode.剑指Offer.第三版.第5章_哈希表.q31_LRU缓存;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2022/1/5 8:24 AM
 * @description 数据结构与算法之美书上的LRU
 */
public class LRUCache {

    /**
     * 有序链表结点=双端链表结点
     * 输入
     * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
     * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
     * 输出
     * [null, null, null, 1, null, -1, null, -1, 3, 4]
     * 解释
     * LRUCache lRUCache = new LRUCache(2);
     * lRUCache.put(1, 1); // 缓存是 {1=1}
     * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
     * lRUCache.get(1);    // 返回 1
     * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
     * lRUCache.get(2);    // 返回 -1 (未找到)
     * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
     * lRUCache.get(1);    // 返回 -1 (未找到)
     * lRUCache.get(3);    // 返回 3
     * lRUCache.get(4);    // 返回 4
     */
    private class Node {
        public int key;
        public int value;
        public Node prev;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 哈希表：缓存值和指向有序链表的指针
     */
    private Map<Integer, Node> map;
    /**
     * 初试化长度：size
     * 实际长度：map.size
     */
    private int size;
    /**
     * 哨兵头
     */
    private Node head;
    /**
     * 哨兵尾
     */
    private Node tail;


    public LRUCache(int capacity) {
        size = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 查找
     * 每次查找或者添加修改都将使用过的元素放在链表末尾
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        moveToTail(node);
        return node.value;
    }

    /**
     * 添加和修改
     * 每次查找或者添加修改都将使用过的元素放在链表末尾
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToTail(node);
            return;
        }
        if (map.size() == size) {
            remove(head.next.key);
        }
        Node node = new Node(key, value);
        insertToTail(node);
        map.put(key,node);
    }

    /**
     * 删除
     * 头部始终保持最近最少使用的元素
     */
    public void remove(int key) {
        if (!map.containsKey(key)) {
            return;
        }
        Node node = map.get(key);
        deleteNode(node);
        map.remove(node.key);
    }

    private void moveToTail(Node node) {
        if (node == null) {
            return;
        }
        deleteNode(node);
        insertToTail(node);
    }


    private void deleteNode(Node node) {
        if (node == null) {
            return;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertToTail(Node node) {
        if (node == null) {
            return;
        }
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);// 缓存是 {1=1}
        lruCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lruCache.get(1));    // 返回 1
        lruCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lruCache.get(2));    // 返回 -1 (未找到)
        lruCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lruCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lruCache.get(3));    // 返回 3
        System.out.println(lruCache.get(4));    // 返回 4
    }
}
