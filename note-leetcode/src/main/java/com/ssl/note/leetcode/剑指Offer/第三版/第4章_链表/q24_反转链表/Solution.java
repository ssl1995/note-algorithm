package com.ssl.note.leetcode.剑指Offer.第三版.第4章_链表.q24_反转链表;

import com.ssl.note.algorithm.leetcode.utils.ListNode;

/**
 * @author SongShengLin
 * @date 2021/9/23 9:20 上午
 * @description 反转单向链表
 */
public class Solution {
    /**
     * 反转单链表
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    /**
     * 反转单链表
     * 递归法
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ret = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return ret;
    }
}
