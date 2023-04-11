package com.ssl.note.leetcode.剑指Offer.第三版.第8章_树.q49_从根节点到叶节点的路劲数字之和;

/**
 * @author SongShengLin
 * @date 2021/10/11 8:02 上午
 * @description
 */
public class Solution {

    /**
     * 从根节点到叶子节点的路径数字之和
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, 0);
    }

    /**
     * 计算root当前节点的路径和
     */
    private int dfs(TreeNode root, int pathSum) {
        if (root == null) {
            return 0;
        }
        pathSum = pathSum * 10 + root.val;
        // 来到叶子节点
        if (root.left == null && root.right == null) {
            return pathSum;
        }
        // 非叶子节点
        return dfs(root.left, pathSum) + dfs(root.right, pathSum);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node0 = new TreeNode(0);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node3.left = node9;
        node3.right = node0;
        node9.left = node5;
        node9.right = node1;
        node0.right = node2;
        System.out.println(solution.sumNumbers(node3));
    }
}
