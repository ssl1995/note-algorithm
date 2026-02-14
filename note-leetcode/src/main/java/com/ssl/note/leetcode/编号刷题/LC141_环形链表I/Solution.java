package com.ssl.note.leetcode.编号刷题.LC141_环形链表I;

import com.ssl.note.leetcode.utils.ListNode;

public class Solution {

  /**
   * 判断链表是否有环，返回值是布尔值
   */
  public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    ListNode slow = head;
    ListNode fast = head;
    // 快慢指针
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        return true;
      }
    }
    return false;
  }

}
