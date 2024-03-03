package com.ssl.note.zuo.bilibili.C10_合并两个有序链表;

// 将两个升序链表合并为一个新的 升序 链表并返回
// 新链表是通过拼接给定的两个链表的所有节点组成的
// 测试链接 : https://leetcode.cn/problems/merge-two-sorted-lists/
public class MergeTwoLists {

    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * LC21:将两个升序链表合并为一个新的 升序 链表并返回
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode head = list1.val <= list2.val ? list1 : list2;
        ListNode cur1 = head.next;
        ListNode cur2 = head == list1 ? list2 : list1;

        ListNode sort = head;// sort指向已经排好序的最后一个节点
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                sort.next = cur1;
                cur1 = cur1.next;
            } else {
                sort.next = cur2;
                cur2 = cur2.next;
            }
            sort = sort.next;
        }
        if (cur1 != null) {
            sort.next = cur1;
        }
        if (cur2 != null) {
            sort.next = cur2;
        }
        return head;
    }

}