package com.ssl.note.leetcode.剑指Offer.第三版.第4章_链表.q27_回文链表;

import com.ssl.note.leetcode.utils.ListNode;

/**
 * @author SongShengLin
 * @date 2021/9/27 8:51 上午
 * @description 回文链表
 */
public class Solution {

    /**
     * 回文链表
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            // 注意：空节点或者单个节点是回文链表
            return true;
        }
        // 1.找到中间节点：奇数返回中间，偶数返回前一个
        ListNode mid = getMidNode(head);
        // 2.反转后半部分链表
        ListNode head2 = mid.next;
        head2 = reverseList(head2);
        // 3.前后2个链表值判断是否回文
        return isPalindromeList(head, head2);
    }


    private ListNode getMidNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ret = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return ret;
    }

    private boolean isPalindromeList(ListNode head1, ListNode head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head2 == null && head1 != null) {
            return false;
        }
        while (head1 != null && head2 != null) {
            if (head1.val != head2.val) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(2);
//        ListNode n4 = new ListNode(3);
//        ListNode n5 = new ListNode(2);
        n1.next = n2;
        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(n1));

    }

}
