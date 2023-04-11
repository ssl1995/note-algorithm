package com.ssl.note.leetcode.编号刷题.LC19_删除链表的倒数第N个结点;

import com.ssl.note.algorithm.leetcode.utils.ListNode;

public class Solution {

    /**
     * 删除链表的倒数第N个结点
     * 注意:建立dummy节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 1) {
            return null;
        }
        // 1.哑结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 如果fast、slow都从head开始遍历，当链表只有一个元素时候，n=1删除倒数第一个元素时
        // slow.next 会报空指针异常，思考指针next的用途，我们需要从dummy虚拟头结点开始
        // 2.快慢指针指向dummy
        ListNode fast = dummy;
        ListNode slow = dummy;
        // 3.快指针先走n步
        while (n > 0) {
            // 这一步其实不用验证，题目保证n在链表长度内
            if (fast == null) {
                return null;
            }
            fast = fast.next;
            n--;
        }
        // 注意循环条件：fast.next != null
        // 换句话：fast.next ==null结束遍历
        // 4.快慢指针一起走
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 5.删除倒数第n个节点
        slow.next = slow.next.next;
        // 6.返回哑结点.next
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        int n = 2;

        System.out.println(solution.removeNthFromEnd(node1, 2));
    }

}
