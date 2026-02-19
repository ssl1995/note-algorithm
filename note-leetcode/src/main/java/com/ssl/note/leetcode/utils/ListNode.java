package com.ssl.note.leetcode.utils;

public class ListNode {
  public int val;
  public ListNode next;

  public ListNode(int x) {
    this.val = x;
    this.next = null;
  }

  public static void printListNode(ListNode head) {
    while (head != null) {
      System.out.print(head.val + " ");
      head = head.next;
    }
    System.out.println();
  }
}
