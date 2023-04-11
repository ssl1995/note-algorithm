package com.ssl.note.leetcode.剑指Offer.第三版.第4章_链表.q28_展平多级双向链表;

/**
 * @Author: SongShengLin
 * @Date: 2022/10/27 17:15
 * @Describe:
 */
public class Solution1 {

    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        process(head);
        return head;
    }

    private Node process(Node node) {
        Node cur = node;
        Node tail = null;
        while (cur != null) {
            Node next = cur.next;

            if (cur.child != null) {
                Node child = cur.child;
                Node childTail = process(child);

                cur.next = child;
                child.prev = cur;
                cur.child = null;

                if (next != null) {
                    childTail.next = next;
                    next.prev = childTail;
                }
                tail = childTail;
            } else {
                tail = cur;
            }

            cur = cur.next;

        }

        return tail;
    }
}
