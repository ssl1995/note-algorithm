package com.ssl.note.leetcode.编号刷题.LC98_验证二叉搜索树;

import com.ssl.note.leetcode.utils.TreeNode;

import java.util.LinkedList;


public class Solution {

    /**
     * pre是会超过int类型的边界值,所以使用Long的边界值
     */
    private long pre = Long.MIN_VALUE;

    /**
     * 验证是否是BST
     * 中序递归法
     */
    public boolean isValidBST(TreeNode root) {
        // 空节点，是BST，返回true
        if (root == null) {
            return true;
        }
        // 中序遍历
        // 左孩子是否是BST
        boolean leftIsBST = isValidBST(root.left);
        if (!leftIsBST) {
            return false;
        }

        // BST的中序遍历：小到大
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;

        // 右孩子是否是BST
        return isValidBST(root.right);
    }


    /**
     * 验证是否是BST
     * 中序非递归
     */
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        long pre = Long.MIN_VALUE;
        while (!stack.isEmpty() || root != null) {
            // 中序非递归遍历先把所有左子树入栈
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                // 左子树到null就出栈，操作+入右子树
                root = stack.pop();

                if (root.val <= pre) {
                    return false;
                } else {
                    pre = root.val;
                }

                root = root.right;
            }
        }
        return true;
    }


}
