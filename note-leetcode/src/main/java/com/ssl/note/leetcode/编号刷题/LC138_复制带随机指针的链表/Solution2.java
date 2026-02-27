package com.ssl.note.leetcode.编号刷题.LC138_复制带随机指针的链表;

/**
 * @author SongShengLin
 * @date 2022/1/11 9:12 AM
 * @description
 */
public class Solution2 {

  /**
   * 复制一个带有随机指针的链表
   */
  public Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }
    // 1、每个节点后面复制节点
    Node cur = head;
    while (cur != null) {
      Node next = cur.next;
      Node copy = new Node(cur.val);
      cur.next = copy;
      copy.next = next;

      cur = next;
    }

    // 2、修改复制节点的random
    cur = head;
    while (cur != null) {
      Node copy = cur.next;
      copy.random = cur.random == null ? null : cur.random.next;

      cur = copy.next;
    }

    // 3、分离=修改复制节点的next
    cur = head;
    Node newHead = cur.next;
    while (cur != null) {
      Node copy = cur.next;
      Node next = copy.next;

      cur.next = next;
      copy.next = next == null ? null : next.next;

      cur = next;
    }

    return newHead;
  }
}
