package com.ssl.note.leetcode.编号刷题.LC707_设计链表;

/**
 * 设计一个链表
 */
public class MyLinkedList {

    /**
     * 链表长度
     */
    private int size;
    /**
     * 虚拟头结点
     */
    private ListNode dummy;

    class ListNode {
        int val;
        ListNode next;

        public ListNode() {

        }

        public ListNode(int val) {
            this.val = val;
        }
    }

    public MyLinkedList() {
        dummy = new ListNode();
        size = 0;
    }

    /**
     * get:根据index获取元素
     * index：从0开始
     */
    public int get(int index) {
        // index在get时候永远<size
        if (index < 0 || index >= size) {
            return -1;
        }
        // cur从dummy开始，所以<=index
        ListNode cur = dummy;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    /**
     * addAtHead：头部插入数据
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * addAtTail：尾部插入数据
     */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /**
     * addAtIndex:指定位置插入元素
     */
    public void addAtIndex(int index, int val) {
        // 新增的时候,index会在原size长度新增1，所以指针没有=
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        size++;

        ListNode pre = dummy;
        for (int i = 0; i <= index - 1; i++) {
            pre = pre.next;
        }

        ListNode node = new ListNode(val);
        node.next = pre.next;
        pre.next = node;
    }

    /**
     * deleteAtIndex:指定位置删除元素
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        size--;

        ListNode pre = dummy;
        for (int i = 0; i <= index - 1; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);// 1->2->3
        list.get(1);// 2
        list.deleteAtIndex(1);// 1->3
        list.get(1);// 3
    }
}