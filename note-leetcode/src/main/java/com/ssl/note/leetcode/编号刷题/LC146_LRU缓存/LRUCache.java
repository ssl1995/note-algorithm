package com.ssl.note.leetcode.编号刷题.LC146_LRU缓存;

import java.util.HashMap;
import java.util.Map;


class LRUCache {

  /**
   * 手写一个LRU最近最少使用缓存策略
   * 提供：get(key)，put(key, value)
   */
  public LRUCache(int capacity) {
    head = new Node(-1, -1);
    tail = new Node(-1, -1);
    head.next = tail;
    tail.pre = head;

    map = new HashMap<>();

    size = capacity;
  }

  public int get(int key) {
    if (!map.containsKey(key)) {
      return -1;
    }
    Node node = map.get(key);
    moveToTail(node);
    return node.value;
  }

  public void put(int key, int value) {
    // 存在元素就更新
    if (map.containsKey(key)) {
      Node node = map.get(key);
      node.value = value;
      moveToTail(node);
      return;
    }
    // 队列满了，移除队首
    if (size == map.size()) {
      removeHead();
    }
    // 新增节点
    Node node = new Node(key, value);
    insertToTail(node);
    map.put(key, node);// 不能忘记插入map
  }

  // 双向链表数据结构
  static class Node {
    Node pre;
    Node next;

    int key;
    int value;

    public Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  // 整体缓存结构
  private final int size;
  private final Map<Integer, Node> map;
  private final Node head;
  private final Node tail;

  // 原子操作：删除节点
  private void deleteNode(Node node) {
    if (node == null) {
      return;
    }
    node.pre.next = node.next;
    node.next.pre = node.pre;
  }

  // 原子操作：队尾插入节点
  private void insertToTail(Node node) {
    if (node == null) {
      return;
    }
    tail.pre.next = node;
    node.pre = tail.pre;
    node.next = tail;
    tail.pre = node;
  }

  // 通用操作：移动到队尾
  private void moveToTail(Node node) {
    if (node == null) {
      return;
    }
    deleteNode(node);
    insertToTail(node);
  }

  // 通用操作：移除队首
  private void removeHead() {
    Node removeHead = head.next;
    deleteNode(removeHead);
    map.remove(removeHead.key);
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