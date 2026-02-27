package com.ssl.note.leetcode.编号刷题.LC148_排序链表;

import com.ssl.note.leetcode.utils.ListNode;

public class Solution {

  /**
   * 排序链表
   * 输入：head = [4,2,1,3]
   * 输出：[1,2,3,4]
   * 方法：归并排序法
   */
  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    // 拆
    ListNode mid = getMiddleNode(head);
    ListNode rightHead = mid.next;
    mid.next = null;

    // 排
    ListNode left = sortList(head);
    ListNode right = sortList(rightHead);

    // 合
    return merge(left, right);
  }


  // 偶数取左边
  private ListNode getMiddleNode(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  private ListNode merge(ListNode left, ListNode right) {
    if (left == null || right == null) {
      return left == null ? right : left;
    }

    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;

    while (left != null && right != null) {
      if (left.val < right.val) {
        cur.next = left;
        left = left.next;
      } else {
        cur.next = right;
        right = right.next;
      }
      cur = cur.next;
    }

    cur.next = left == null ? right : left;

    return dummy.next;
  }

}
