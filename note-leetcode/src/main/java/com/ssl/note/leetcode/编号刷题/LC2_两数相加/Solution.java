package com.ssl.note.leetcode.编号刷题.LC2_两数相加;

import com.ssl.note.leetcode.utils.ListNode;

public class Solution {

  /**
   * 两数相加
   * 输入：l1 = [2,4,3], l2 = [5,6,4]
   * 输出：[7,0,8]
   * 解释：342 + 465 = 807.
   */
  public ListNode addTwoNumbers(ListNode head1, ListNode head2) {
    ListNode dummyNode = new ListNode(-1);
    ListNode cur = dummyNode;

    int carry = 0;
    // 只要有一个链表还有节点，就继续
    while (head1 != null || head2 != null) {
      // 当前数的合
      int sum = (head1 != null ? head1.val : 0) + (head2 != null ? head2.val : 0) + carry;
      // sum=11，取1
      int value = sum % 10;
      // sum=11，判断是否进位
      carry = sum / 10;

      if (head1 != null) {
        head1 = head1.next;
      }
      if (head2 != null) {
        head2 = head2.next;
      }

      cur.next = new ListNode(value);
      cur = cur.next;
    }
    // 最高位是否有进位
    if (carry == 1) {
      cur.next = new ListNode(1);
    }

    return dummyNode.next;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    // 写出这个链表：l1 = [2,4,3], l2 = [5,6,4]
    ListNode head1 = new ListNode(2);
    ListNode node2 = new ListNode(4);
    ListNode node3 = new ListNode(3);
    head1.next = node2;
    node2.next = node3;

    ListNode head2 = new ListNode(5);
    ListNode node21 = new ListNode(6);
    ListNode node31 = new ListNode(4);
    head2.next = node21;
    node21.next = node31;

    // 期望：[7,0,8]
    ListNode node = solution.addTwoNumbers(head1, head2);
    while (node != null) {
      System.out.print(node.val);
      if (node.next != null) {
        System.out.print("->");
      }
      node = node.next;
    }
    System.out.println();
  }

}
