package com.ssl.note.leetcode.剑指Offer.第三版.第4章_链表.q29_排序的循环链表;

/**
 * @Author: SongShengLin
 * @Date: 2022/10/28 09:19
 * @Describe:
 */
public class Solution1 {


    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        if (head == null) {
            node.next = node;
            return node;
        } else if (head.next == null) {
            head.next = node;
            node.next = head;
            return head;
        }
        insertNode(head, node);
        return head;
    }

    private void insertNode(Node head, Node node) {
        Node cur = head;
        Node big = head;
        Node next = cur.next;

        while (!(cur.val <= node.val && node.val <= next.val)
                && next != head) {
            cur = next;
            next = next.next;
            if (cur.val >= big.val) {
                big = cur;
            }
        }

        if (cur.val <= node.val && node.val <= next.val) {
            cur.next = node;
            node.next = next;
        } else {
            Node bigNext = big.next;
            big.next = node;
            node.next = bigNext;
        }
    }

}
