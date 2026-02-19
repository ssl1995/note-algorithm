package com.ssl.note.leetcode.编号刷题.LC24_两两交换;

import com.ssl.note.leetcode.utils.ListNode;

public class Solution1 {

  /**
   * 递归法
   */
  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode first = head;
    ListNode second = head.next;
    first.next = swapPairs(second.next);
    second.next = first;
    return second;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    ListNode.printListNode(solution.swapPairs(node1));
  }
}
