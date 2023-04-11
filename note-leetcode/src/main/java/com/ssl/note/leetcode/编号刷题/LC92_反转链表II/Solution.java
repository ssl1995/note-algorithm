package com.ssl.note.leetcode.编号刷题.LC92_反转链表II;

import com.ssl.note.algorithm.leetcode.utils.ListNode;

/**
 * @Author: SongShengLin
 * @Date: 2022/09/15 09:42
 * @Describe:
 */
public class Solution {

    /**
     * 反转链表II，在指定区域反转
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 1.pre指向待反转区域的第一个节点的前一个节点，不会发生改变
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        // 2.cur指向带待反转区域的left，cur.next不会发生改变
        ListNode cur = pre.next;
        // 3.指向需要头插的节点
        ListNode next;
        // 不需要再次遍历确定right后面的结点，因为可能right是末尾节点，反而加大了时间复杂度
        // 使用头插法，每次都将cur.next头插到带翻转区域的头部
        for (int i = 0; i < right - left; i++) {
            // next：每次需要头插到头部的结点
            next = cur.next;
            // 第一步：cur.next先记录next.next
            cur.next = next.next;
            // 第二步：头插的位置是pre.next，而不是cur，cur相当于遍历指针
            next.next = pre.next;
            // 第三步：next是新的头部，pre.next指向它
            pre.next = next;
        }
        return dummy.next;
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

        Solution solution = new Solution();
        ListNode listNode = solution.reverseBetween(n1, 2, 4);

        while (listNode != null) {
            System.out.print(listNode.val + ",");
            listNode = listNode.next;
        }
    }
}
