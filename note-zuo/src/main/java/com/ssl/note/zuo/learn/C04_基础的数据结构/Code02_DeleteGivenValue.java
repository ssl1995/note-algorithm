package com.ssl.note.zuo.learn.C04_基础的数据结构;

public class Code02_DeleteGivenValue {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 删除给定的值
     */
    public static Node removeValue(Node head, int num) {
        // 1.head来到第一个不需要删的位置，作为返回值
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        // 2.双指针遍历
        // 1 ) head == null
        // 2 ) head != null
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

}
