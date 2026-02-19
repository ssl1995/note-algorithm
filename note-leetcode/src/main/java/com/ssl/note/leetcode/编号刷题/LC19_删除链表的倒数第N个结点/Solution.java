package com.ssl.note.leetcode.编号刷题.LC19_删除链表的倒数第N个结点;

import com.ssl.note.leetcode.utils.ListNode;

public class Solution {

  /**
   * 删除链表的倒数第N个结点
   * 注意:建立dummy节点
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null || n < 1) {
      return null;
    }
    // 1.哑结点
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    // 从 dummy 出发 = 给 head 前面垫一个前驱，这样无论删的是中间结点还是头结点
    // slow 都能停在前驱上安全执行 slow.next = slow.next.next，不会空指针。
    // 2.快慢指针指向dummy
    ListNode fast = dummy;
    ListNode slow = dummy;
    // 3.快指针先走n步
    while (n > 0) {
      fast = fast.next;
      n--;
    }
    // 注意循环条件：fast.next != null
    // 换句话：fast.next ==null结束遍历
    // 4.快慢指针一起走
    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
    }
    // 5.删除倒数第n个节点
    slow.next = slow.next.next;
    // 6.返回哑结点.next
    return dummy.next;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    // 虚拟 1 2 3 4 5
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);
    ListNode node5 = new ListNode(5);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;
    int n = 2;

    ListNode newHead = solution.removeNthFromEnd(node1, n);

    while (newHead != null) {
      System.out.print(newHead.val);
      System.out.print(" ");
      newHead = newHead.next;
    }
  }

}
