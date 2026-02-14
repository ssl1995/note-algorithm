package com.ssl.note.leetcode.编号刷题.LC142_环形链表II;

import com.ssl.note.leetcode.utils.ListNode;

public class Solution {

  /**
   * LC142_判断环形链表II：
   * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回null
   */
  public ListNode detectCycle(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) {
      return null;
    }
    ListNode slow = head;
    ListNode fast = head;

    boolean isCycle = false;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        isCycle = true;
        // 必须退出，有环时就会一直死循环
        break;
      }
    }

    if (!isCycle) {
      return null;
    }

    fast = head;

    while (fast != slow) {
      fast = fast.next;
      slow = slow.next;
    }

    return fast;
  }
}
