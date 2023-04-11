package com.ssl.note.leetcode.剑指Offer.第三版.第4章_链表.q21_删除倒数第k个节点;

import com.ssl.note.algorithm.leetcode.utils.ListNode;

/**
 * @author songshenglin
 * @date 2021/9/21 10:40
 * @description
 */
public class Solution {

    /**
     * 删除链表倒数第k个结点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = head;
        // fast先走n步
        while (n > 0) {
            // 在删除倒数第n个节点时，fast一定要来到null才停止
            if (fast == null) {
                return null;
            }
            fast = fast.next;
            n--;
        }
        // 在删除倒数第n个节点时，fast一定要来到null才停止
        // 由于slow指向dummy,所以fast要多走一步到达null，才能让slow到达待删除结点前一个位置
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        // 难点：因为可能删除的是头结点，返回的哑结点的下一个位置
        return dummy.next;
    }

    /*******************练习********************************/
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        if (n < 0) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = head;

        while (n > 0) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
            n--;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
//        return head;
        return dummy.next;
    }
}
