package com.ssl.note.leetcode.剑指Offer.第三版.第8章_树.q53_二叉搜索树的下一个结点;

import java.util.LinkedList;

/**
 * @author SongShengLin
 * @date 2021/10/12 8:49 上午
 * @description
 */
public class Solution {

    /**
     * 找二叉搜索树中的中序后继结点
     * 法1：BST非递归中序遍历，用一个布尔值记录是否遍历到p
     * 时间复杂度和空间复杂度都是O(n)
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;

        Boolean flag = false;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (flag) {
                break;
            } else if (cur == p) {
                flag = true;
            }
            cur = cur.right;
        }

        return cur;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node8 = new TreeNode(8);
        TreeNode node6 = new TreeNode(6);
        TreeNode node10 = new TreeNode(10);
        TreeNode node5 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node11 = new TreeNode(11);
        node8.left = node6;
        node8.right = node10;
        node6.left = node5;
        node6.right = node7;
        node10.left = node9;
        node10.right = node11;

        System.out.println(solution.inorderSuccessor(node8, node8).val);
    }
}
