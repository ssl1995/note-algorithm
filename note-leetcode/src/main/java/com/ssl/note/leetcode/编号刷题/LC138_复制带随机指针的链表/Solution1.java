package com.ssl.note.leetcode.编号刷题.LC138_复制带随机指针的链表;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2022/1/11 9:12 AM
 * @description
 */
public class Solution1 {

  /**
   * 复制一个带有随机指针的链表
   * 哈希解法
   */
  public Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }
    Map<Node, Node> map = new HashMap<>();
    Node cur = head;

    while (cur != null) {
      map.put(cur, new Node(cur.val));
      cur = cur.next;
    }

    cur = head;

    while (cur != null) {
      Node copyNode = map.get(cur);
      // 指向拷贝的节点
      copyNode.next = map.get(cur.next);
      copyNode.random = map.get(cur.random);

      cur = cur.next;
    }

    return map.get(head);
  }
}
