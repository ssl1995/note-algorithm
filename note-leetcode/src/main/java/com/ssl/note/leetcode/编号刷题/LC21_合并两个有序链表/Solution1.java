package com.ssl.note.leetcode.编号刷题.LC21_合并两个有序链表;


import com.ssl.note.leetcode.utils.ListNode;

public class Solution1 {
  /**
   * 合并两个有序链表
   */
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    // 终止条件
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }
    // 做选择=归并排序中合的部分
    // 我只管当前这一步，剩下的交给递归
    if (l1.val < l2.val) {
      l1.next = mergeTwoLists(l1.next, l2);
      return l1;
    }
    l2.next = mergeTwoLists(l1, l2.next);
    return l2;
  }
}
