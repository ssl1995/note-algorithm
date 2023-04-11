package com.ssl.note.leetcode.编号刷题.LC876_链表中间节点;

import com.ssl.note.algorithm.leetcode.utils.ListNode;


public class Solution {

    /**
     * 返回链表中间节点
     * 注意：奇数在中间,偶数在中间靠右
     */
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head.next;
        /**
         * 奇数：返回中间
         * 偶数：1->2->3->4;返回3
         */
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 变种:奇数在中间,偶数在中间靠左
    public ListNode middleNode1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        /**
         * 奇数：返回中间
         * 偶数：1->2->3->4;返回3
         */
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
//        node4.next = node5;

        Solution solution = new Solution();
        System.out.println(solution.middleNode(node1).val);
        System.out.println(solution.middleNode1(node1).val);
    }
}
