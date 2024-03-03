package com.ssl.note.zuo.bilibili.C12_划分链表;

public class PartitionList {

    /**
     * LC86:分割链表
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，
     * 使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     */
    public static ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode left1 = null, left2 = null;// <x的区域
        ListNode right1 = null, right2 = null; // >=x的区域,保证相对顺序不变，val=x的无论多少个，依次放就行

        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                if (left1 == null) {
                    left1 = new ListNode(cur.val);
                    left2 = left1;
                } else {
                    left2.next = new ListNode(cur.val);
                    left2 = left2.next;
                }
            } else {
                if (right1 == null) {
                    right1 = new ListNode(cur.val);
                    right2 = right1;
                } else {
                    right2.next = new ListNode(cur.val);
                    right2 = right2.next;
                }
            }
            cur = cur.next;
        }
        // 如果没有比x小的
        if (left1 == null) {
            return right1;
        }
        left2.next = right1;
        return left1;
    }

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
}