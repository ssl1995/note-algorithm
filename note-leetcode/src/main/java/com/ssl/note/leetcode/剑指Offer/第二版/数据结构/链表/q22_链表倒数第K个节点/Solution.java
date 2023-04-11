package com.ssl.note.leetcode.剑指Offer.第二版.数据结构.链表.q22_链表倒数第K个节点;


import com.ssl.note.leetcode.utils.ListNode;

public class Solution {

    /**
     * 链表倒数第k个节点
     * k从1开始
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        // 快指针先走k步
        while (k > 0) {
            // 注意:fast==null,不是fast.next!=null
            if (fast == null) {
                return null;
            }
            fast = fast.next;
            k--;
        }
        // 当快指针走到末尾节点的下一个节点=null时,slow走到倒数第K个节点
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
