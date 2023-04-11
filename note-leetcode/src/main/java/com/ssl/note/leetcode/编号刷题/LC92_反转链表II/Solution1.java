package com.ssl.note.leetcode.编号刷题.LC92_反转链表II;

import com.ssl.note.leetcode.utils.ListNode;

/**
 * @author SongShengLin
 * @date 2023/3/6 14:55
 * @description
 */
public class Solution1 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return head;
        }
        if (left < 0 || right < 0) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode cur = pre.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        System.out.println("---");
        Solution1 solution1 = new Solution1();
        // 1 2 3 4 5
        // 1 4 3 2 5
        ListNode listNode = solution1.reverseBetween(n1, 2, 4);

        while (listNode != null) {
            System.out.print(listNode.val + ",");
            listNode = listNode.next;
        }
    }
}
