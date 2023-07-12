package com.ssl.note.zuo.learn.C04_基础的数据结构;

import com.ssl.note.zuo.utils.ListNode;

public class Code02_DeleteGivenValue {

    /**
     * 单链表：删除给定的值 = LC:剑指Offer18 删除链表的节点
     * 返回：删除节点后的头结点
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return head.next;
        }
        ListNode pre = head;
        ListNode cur = head;
        while (cur != null) {
            // v1.0: 冗余写法
//            if (cur.val != val) {
//                pre = cur;
//                cur = cur.next;
//                continue;
//            }
//            pre.next = cur.next;
//            cur = cur.next;

            // v2.0: 优化写法
            if (cur.val != val) {
                pre = cur;
            } else {
                pre.next = cur.next;
            }
            // 无论是否删除节点，cur只能到next
            // 不能到达next.next，因为防止空指针
            cur = cur.next;
        }
        return head;
    }

}
