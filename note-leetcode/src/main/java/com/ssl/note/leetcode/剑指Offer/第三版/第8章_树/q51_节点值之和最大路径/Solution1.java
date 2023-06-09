package com.ssl.note.leetcode.剑指Offer.第三版.第8章_树.q51_节点值之和最大路径;

/**
 * @author SongShengLin
 * @date 2021/10/11 8:38 上午
 * @description
 */
public class Solution1 {


    private int maxSum = Integer.MIN_VALUE;

    // 这个方法跑力扣124不会超时
    public int maxPathSum(TreeNode root) {
        dfs(root);

        return maxSum;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左右孩子的最大值
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        // 包含当前节点的最大值
        int containsRoot = left + right + root.val;
        maxSum = Math.max(containsRoot, maxSum);

        return root.val + Math.max(left, right);
    }


}
