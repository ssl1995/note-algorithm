package com.ssl.note.leetcode.剑指Offer.第三版.第8章_树.q51_节点值之和最大路径;

/**
 * @author SongShengLin
 * @date 2021/10/11 8:38 上午
 * @description
 */
public class Solution {
    private int maxSum = Integer.MIN_VALUE;

    /**
     * 二叉树路径中结点值之和最大值
     * 路径定义：可能是从上往下，也可能包含左、当前、右路径
     */
    public int maxPathSum(TreeNode root) {
        dfs(root);

        return maxSum;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 递归返回左右孩子的最大值
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);

        // 全局变量，包含当前节点和的最大值
        int containsRoot = left + right + root.val;
        maxSum = Math.max(containsRoot, maxSum);

        // 递归返回，路径只能向下的路径和
        return root.val + Math.max(left, right);
    }
}
