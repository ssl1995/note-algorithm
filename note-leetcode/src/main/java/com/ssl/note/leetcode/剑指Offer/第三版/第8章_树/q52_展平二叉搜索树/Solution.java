package com.ssl.note.leetcode.剑指Offer.第三版.第8章_树.q52_展平二叉搜索树;

import java.util.LinkedList;

/**
 * @author SongShengLin
 * @date 2021/10/12 8:33 上午
 * @description
 */
public class Solution {


    /**
     * 展平二叉搜索树
     */
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode newRoot = null;
        TreeNode prev = null;
        while (!stack.isEmpty() || root != null) {
            // 当前的左孩子全部进栈,找到最左孩子
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (prev == null) {
                // 新的根节点
                newRoot = root;
            } else {
                // 否则，右边指向当前出栈的
                prev.right = root;
            }

            prev = root;
            root.left = null;
            root = root.right;
        }
        return newRoot;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node4.left = node2;
        node4.right = node5;
        node2.left = node1;
        node2.right = node3;
        node5.right = node6;

        TreeNode root = solution.increasingBST(node4);
        while (root != null) {
            System.out.println(root.val + " ");
            if (root.left != null) {
                System.out.println("error");
            } else {
                root = root.right;
            }
        }
    }
}
