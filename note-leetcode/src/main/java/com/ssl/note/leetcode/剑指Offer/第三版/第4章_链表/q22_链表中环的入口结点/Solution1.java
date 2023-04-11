package com.ssl.note.leetcode.剑指Offer.第三版.第4章_链表.q22_链表中环的入口结点;

import com.ssl.note.leetcode.utils.ListNode;

/**
 * @author songshenglin
 * @date 2021/9/21 11:14
 * @description 链表有环，K神书的解法，用这个更容易记
 */
public class Solution1 {
    /**
     * 判断链表是否有环，有环就返回入环结点，无环就返回null
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /*******************练习********************************/
    public ListNode detectCycle1(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode node4 = new ListNode(4);
        node3.next = node2;
        node2.next = node0;
        node0.next = node4;
        node4.next = node2;
        ListNode res1 = solution.detectCycle(node3);
        ListNode res2 = solution.detectCycle1(node3);
        if (res1.val != res2.val) {
            System.out.println("error!");
        }else {
            System.out.println("yes!");
        }
    }

}
