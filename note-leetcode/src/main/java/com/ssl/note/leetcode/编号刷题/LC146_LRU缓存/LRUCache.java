package com.ssl.note.leetcode.编号刷题.LC146_LRU缓存;

import java.util.HashMap;

/**
 * @Author: SongShengLin
 * @Date: 2022/06/16 10:17 AM
 * @Describe:
 */
public class LRUCache {

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

    /**
     * size：链表初试化长度
     * 实际长度：mao.size
     */
    private int size;
    private Node head;
    private Node tail;
    private HashMap<Integer, Node> map;

    /**
     * 最近最少使用：
     * put/get都将使用过的元素放在链表末尾
     * 于是链表头部永远都是待删除的元素
     */
    public LRUCache(int capacity) {
        map = new HashMap<>();
        size = capacity;
        // 题目固定key和value都是正整数
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.pre = head;
    }

    /**
     * 获取元素
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
     * 设置元素
     */
    public void put(int key, int value) {
        // 如果map中有这个元素，就更新value
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToTail(node);
            return;
        }
        // map中没有这个元素
        // 如果达到初始化长度，删除头部最近的数据
        if (map.size() == size) {
            moveHead();
        }
        // 链表尾部插入数据
        Node node = new Node(key, value);
        insertToTail(node);
        map.put(key, node);
    }


    /**
     * 删除头部元素
     */
    private void moveHead(){
        Node node = head.next;
        deleteListNode(node);
        // 删除元素，也要移除map
        // 注意api的参数是key不是node
        map.remove(node.key);
    }

    private void deleteListNode(Node node) {
        if (node == null) {
            return;
        }
        node.pre.next = node.next;
        node.next.pre = node.pre;
        // 这个删除没有动map = 逻辑删除而已
    }

    /**
     * get、put使用过的元素都放到末尾
     */
    private void moveToTail(Node node) {
        if (node == null) {
            return;
        }
        // 删除原有位置的元素
        deleteListNode(node);
        // 插入到末尾
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

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1);// 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
    }


}
