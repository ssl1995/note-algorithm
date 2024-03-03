package com.ssl.note.zuo.bilibili.C11_两个链表相加;

import com.ssl.note.zuo.utils.ListNode;

public class AddTwoNumbers {

    /**
     * LC2：两数相加
     */
    public static ListNode addTwoNumbers(ListNode h1, ListNode h2) {
        if (h1 == null || h2 == null) {
            return h2 == null ? h1 : h2;
        }
        int carry = 0;
        int value = 0;
        ListNode cur1 = h1;
        ListNode cur2 = h2;

        ListNode res = null;
        ListNode cur = null;
        // 只要有1个不为空，就要往后遍历，防止如下情况：
        // 9,9,9,9,9
        // 9,9,9
        while (cur1 != null || cur2 != null) {
            int sum = 0;
            if (cur1 != null) {
                sum += cur1.val;
            }
            if (cur2 != null) {
                sum += cur2.val;
            }
            sum += carry;
            value = sum % 10;
            carry = sum / 10;

            if (res == null) {
                res = new ListNode(value);
                cur = res;
            } else {
                cur.next = new ListNode(value);
                cur = cur.next;
            }

            cur1 = cur1 == null ? null : cur1.next;
            cur2 = cur2 == null ? null : cur2.next;
        }
        if (carry == 1) {
            cur.next = new ListNode(1);
        }
        return res;
    }
}
