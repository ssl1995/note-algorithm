package com.ssl.note.leetcode.剑指Offer.第三版.第4章_链表.q26_重排链表;

import com.ssl.note.algorithm.leetcode.utils.ListNode;

/**
 * @author SongShengLin
 * @date 2021/9/26 10:15 上午
 * @description 链表重排
 */
public class Solution {
    /**
     * 重排链表
     * 1->2->3->4->5
     * 返回：1->5->2->4->3
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        // 1.奇数返回中间，偶数返回前一个
        ListNode slow = getMidNode(head);
        ListNode next = slow.next;
        slow.next = null;
        // 2.反转后半部分链表
        ListNode head2 = reverseList(next);
        // 3.合并两个部分链表
        merge(head, head2);
    }

    /**
     * 返回链表中间节点
     * 奇数返回中间
     * 偶数返回前一个
     */
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


    private void merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        dummy.next = head1;
        ListNode pre = dummy;

        while (head1 != null && head2 != null) {
            ListNode next = head1.next;
            // 上一步中pre=head2，但是head2.next还没有改变指向
            // 这一步的pre.next相当于让head2.next =head1
            pre.next = head1;
            head1.next = head2;
            pre = head2;

            head2 = head2.next;
            head1 = next;
        }
        // getMidNode返回中间节点时候保证了head1的长度>=head2的长度
        // 所以只用观察head1是否没遍历完
        if (head1 != null) {
            pre.next = head1;
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        Solution1 solution1 = new Solution1();
        solution1.reorderList(n1);

        while (n1 != null) {
            System.out.println(n1.val + " ");
            n1 = n1.next;
        }
    }


}
