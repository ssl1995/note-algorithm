package com.ssl.note.leetcode.剑指Offer.第二版.数据结构.树.q36_二叉搜索树和双向链表;

public class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
