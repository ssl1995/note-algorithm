package com.ssl.note.leetcode.编号刷题.LC21_合并两个有序链表;


import com.ssl.note.leetcode.utils.ListNode;

public class Solution {
  /**
   * 合并两个有序链表
   */
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null || l2 == null) {
      return l1 == null ? l2 : l1;
    }
    // 虚拟节点：占位符 + 返回值=它的.next
    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;

    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        cur.next = l1;
        l1 = l1.next;
      } else {
        cur.next = l2;
        l2 = l2.next;
      }

      cur = cur.next;
    }

    cur.next = l1 == null ? l2 : l1;

    return dummy.next;
  }
}
