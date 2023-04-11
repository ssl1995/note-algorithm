package com.ssl.note.leetcode.剑指Offer.第三版.第4章_链表.q28_展平多级双向链表;


/**
 * @author SongShengLin
 * @date 2021/9/27 9:16 上午
 * @description 展平多级双向链表
 */
public class Solution {
    /**
     * 展平多级链表
     */
    public Node flatten(Node head) {
        flattenGetTail(head);
        return head;
    }

    // 获得head为头结点链表的尾结点，并且展平多级节点
    private Node flattenGetTail(Node head) {
        Node cur = head;
        Node tail = null;
        while (cur != null) {
            Node next = cur.next;
            // node有子链表
            if (cur.child != null) {
                // 子的头
                Node child = cur.child;
                // 子的尾
                Node childTail = flattenGetTail(child);

                // cur连接子的头
                cur.next = child;
                child.prev = cur;
                cur.child = null;
                // cur有next，连接子的尾
                if (next != null) {
                    childTail.next = next;
                    next.prev = childTail;
                }
                // node有子，tail指向孩子的尾
                tail = childTail;
            } else {
                // node无子，tail指向cur
                tail = cur;
            }
            // cur后移
            cur = cur.next;
        }
        // 整个函数，返回tail
        return tail;
    }
}
