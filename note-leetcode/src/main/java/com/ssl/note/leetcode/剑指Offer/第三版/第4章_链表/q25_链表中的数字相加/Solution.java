package com.ssl.note.leetcode.剑指Offer.第三版.第4章_链表.q25_链表中的数字相加;

import com.ssl.note.algorithm.leetcode.utils.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author SongShengLin
 * @date 2021/9/23 9:33 上午
 * @description 链表中的两数相加
 */
public class Solution {

    /**
     * 链表中的两数相加
     * 方法1：使用反转链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
//        ListNode reversedHead = addReversed(l1, l2);
        ListNode reversedHead = addReversed1(l1, l2);
        return reverseList(reversedHead);
    }

    private ListNode addReversed(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode sumNode = dummy;
        int carry = 0;

        while (head1 != null || head2 != null) {
            int sum = (head1 == null ? 0 : head1.val) + (head2 == null ? 0 : head2.val) + carry;
            // carry对下一轮造成的影响不是1就是0，所以必须设置
            carry = sum >= 10 ? 1 : 0;
            sum = sum >= 10 ? sum - 10 : sum;

            sumNode.next = new ListNode(sum);
            sumNode = sumNode.next;

            head1 = head1 == null ? null : head1.next;
            head2 = head2 == null ? null : head2.next;
        }
        // 最后结点判断是否还有进位值
        if (carry > 0) {
            sumNode.next = new ListNode(carry);
        }
        return dummy.next;
    }

    private ListNode addReversed1(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (head1 != null || head2 != null) {
            // 我自己的写法
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
        // pre = 待反转结点的前一个结点，最后返回它
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    /**
     * 链表中的两数相加
     * 方法2：使用栈，不使用反转链表
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new LinkedList<>();
        Deque<Integer> stack2 = new LinkedList<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode res = null;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int sum = carry;
            if (!stack1.isEmpty()) {
                sum += stack1.pop();
            }

            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }
            carry = sum >= 10 ? 1 : 0;
            sum = sum >= 10 ? sum % 10 : sum;
            // 这里做一个反转
            ListNode node = new ListNode(sum);
            node.next = res;
            res = node;
        }
        if (carry == 1) {
            ListNode node = new ListNode(carry);
            node.next = res;
            res = node;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution1 = new Solution();
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

        ListNode res = solution1.addTwoNumbers1(n7, n5);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
