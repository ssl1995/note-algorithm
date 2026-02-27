package com.ssl.note.leetcode.编号刷题.LC25_k个一组反转链表;

import com.ssl.note.leetcode.utils.ListNode;

/**
 * @Author: SongShengLin
 * @Date: 2022/12/27 10:28
 * @Describe:
 */
public class Solution {

  /**
   * k个一组反转链表
   */
  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k < 0) {
      return null;
    }
    ListNode dummy = new ListNode(-1);
    dummy.next = head;

    // 将链表分为已反转区域、待反转区域、未反转区域
    // 待反转区域四个指针: pre [start、end] next
    ListNode pre = dummy;
    ListNode start = null;
    ListNode end = dummy;
    ListNode next = null;

    while (end.next != null) {
      // 1、end走k步
      for (int i = 0; i < k && end != null; i++) {
        end = end.next;
      }
      if (end == null) {
        break;
      }

      // 2、pre,[start,end],next
      start = pre.next;
      next = end.next;

      // 3、切断、翻转、连接
      // pre,[end,start],next
      end.next = null;// 切断
      pre.next = reverse(start);// 翻转
      start.next = next;// 连接next

      // 4、归位：将pre和end重置到start位置，开启下一个遍历
      end = start;
      pre = start;
    }

    return dummy.next;
  }


  /**
   * 反转单个链表
   */
  private ListNode reverse(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode cur = head;
    ListNode pre = null;
    while (cur != null) {
      ListNode next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }
    return pre;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    ListNode head = getTestHead();
    int k = 2;
    System.out.print("原始链表:");
    printNode(head);
    System.out.print("反转k个链表后:");
    ListNode res = solution.reverseKGroup(head, k);
    printNode(res);
  }

  private static ListNode getTestHead() {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);
    ListNode node5 = new ListNode(5);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;
    return node1;
  }

  private static void printNode(ListNode node) {
    while (node != null) {
      if (node.next != null) {
        System.out.print(node.val + "->");
      } else {
        System.out.println(node.val);
      }
      node = node.next;
    }
  }
}
