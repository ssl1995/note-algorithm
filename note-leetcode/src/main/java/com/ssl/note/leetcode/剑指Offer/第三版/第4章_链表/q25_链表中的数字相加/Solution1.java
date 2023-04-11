package com.ssl.note.leetcode.剑指Offer.第三版.第4章_链表.q25_链表中的数字相加;

import com.ssl.note.leetcode.utils.ListNode;

/**
 * @Author: SongShengLin
 * @Date: 2022/10/26 08:38
 * @Describe:
 */
public class Solution1 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head1 = reverseList(l1);
        ListNode head2 = reverseList(l2);
        ListNode res = addList(head1, head2);
        return reverseList(res);
    }


    private ListNode addList(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (head1 != null || head2 != null) {
            int sum = 0;
            if (head1 != null) {
                sum += head1.val;
                head1 = head1.next;
            }
            if (head2 != null) {
                sum += head2.val;
                head2 = head2.next;
            }
            if (carry != 0) {
                sum += carry;
            }
            if (sum >= 10) {
                carry = 1;
                sum = sum % 10;
            } else {
                // carry对下一轮造成的影响不是1就是0，所以必须设置
                carry = 0;
            }
            cur.next = new ListNode(sum);
            cur = cur.next;
        }

        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
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

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        ListNode n7 = new ListNode(7);
        ListNode n2 = new ListNode(2);
        ListNode n4 = new ListNode(4);
        ListNode n3 = new ListNode(3);
        n7.next = n2;
        n2.next = n4;
        n4.next = n3;

        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n41 = new ListNode(4);
        n5.next = n6;
        n6.next = n41;

        ListNode res = solution1.addTwoNumbers(n7, n5);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
