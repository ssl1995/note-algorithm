package com.ssl.note.leetcode.编号刷题.LC146_LRU缓存.练习;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    static class Node {
        int key;
        int value;

        Node pre;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node tail;
    private Node head;
    private int size;
    private Map<Integer, Node> map;

    public LRUCache(int capacity) {
        tail = new Node(-1, -1);
        head = new Node(-1, -1);

        size = capacity;
        map = new HashMap<>();

        head.next = tail;

        tail.pre = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            moveToTail(node);
            return node.value;
        }

        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            moveToTail(node);
            node.value = value;
            return;
        }

        if (size == map.size()) {
            moveHead();
        }

        Node node = new Node(key, value);
        map.put(key, node);
        insertToTail(node);
    }

    private void moveHead() {
        Node node = head.next;
        deleteListNode(node);
        map.remove(node.key);
    }

    private void deleteListNode(Node node) {
        if (node == null) {
            return;
        }
        node.pre.next = node.next;
        node.next.pre = node.pre;
        // 不用动map
    }

    private void moveToTail(Node node) {
        if (node == null) {
            return;
        }
        deleteListNode(node);
        insertToTail(node);
    }

    private void insertToTail(Node node) {
        if (node == null) {
            return;
        }
        tail.pre.next = node;
        node.pre = tail.pre;
        node.next = tail;
        tail.pre = node;
    }
}