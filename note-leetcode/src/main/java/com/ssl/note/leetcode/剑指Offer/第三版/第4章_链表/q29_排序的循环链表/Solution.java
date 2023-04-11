package com.ssl.note.leetcode.剑指Offer.第三版.第4章_链表.q29_排序的循环链表;

/**
 * @author SongShengLin
 * @date 2021/9/29 9:16 上午
 * @description
 */
public class Solution {
    /**
     * 排序的循环链表
     * 在一个已经递增排序的链表中插入一个结点，使得它还是递增排序的
     */
    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        // 原链表没有结点
        if (head == null) {
            head = node;
            node.next = head;
        } else if (head.next == null) {
            // 原链表只有一个结点
            head.next = node;
            node.next = head;
        } else {
            // 原链表超过一个结点
            insertNode(head, node);
        }
        return head;
    }

    private void insertNode(Node head, Node node) {
        Node cur = head;
        Node next = head.next;
        Node biggest = head;
        // 1.找到cur<=node<=next 且 next不是头结点
        while (!(cur.val <= node.val && node.val <= next.val)
                && next != head) {
            cur = next;
            next = next.next;
            // 遍历过程中记录最大值结点
            if (cur.val >= biggest.val) {
                biggest = cur;
            }
        }
        // 插入node结点:cur.val<=node.val<=next.val存在，就插入中间
        if (cur.val <= node.val && node.val <= next.val) {
            cur.next = node;
            node.next = next;
        } else {
            // 如果不存在，就插入到最大值和头结点之间
            // head不一定是从最小值开始指向，所以需要先记录bigNext
            Node bigNext = biggest.next;
            biggest.next = node;
            node.next = bigNext;
        }
    }
}
