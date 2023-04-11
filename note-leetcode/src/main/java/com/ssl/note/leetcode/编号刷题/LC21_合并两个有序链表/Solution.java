package com.ssl.note.leetcode.编号刷题.LC21_合并两个有序链表;


import com.ssl.note.algorithm.leetcode.utils.ListNode;

public class Solution {
    /**
     * 合并两个有序链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 1.建立dummy指针
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        // 2.两个节点不为空才能比较
        while (l1 != null && l2 != null) {
            // 3.比较，从小到大结点排序
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            // 4.移动cur
            cur = cur.next;
        }
        // 5.没有遍历完的链表直接挂在最后
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
