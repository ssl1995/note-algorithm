package com.ssl.note.leetcode.剑指Offer.第三版.第8章_树.q50_向下的路径节点之和;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2021/10/11 8:14 上午
 * @description 从根节点到叶节点的所有路径之和；路径：不一定是从根节点开始、叶子节点结束
 */
public class Solution {

    /**
     * 二叉树中任意路径和等于target的路径和
     * 路径定义：只能从当前节点向下经过的结点
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        // 前缀和
        HashMap<Long, Integer> map = new HashMap<>();
        // 根节点的前缀和为0，出现1次
        map.put(0L, 1);

        return dfs(root, targetSum, map, 0L);
    }

    private int dfs(TreeNode root, int target, Map<Long, Integer> map, Long curSum) {
        if (root == null) {
            return 0;
        }
        curSum += root.val;

        Integer count = map.getOrDefault(curSum - target, 0);

        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        count += dfs(root.left, target, map, curSum);
        count += dfs(root.right, target, map, curSum);

        map.put(curSum, map.get(curSum) - 1);

        return count;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node5 = new TreeNode(5);
        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node3 = new TreeNode(3);
        TreeNode node7 = new TreeNode(7);
        node5.left = node2;
        node5.right = node4;
        node2.left = node1;
        node2.right = node6;
        node4.left = node3;
        node4.right = node7;
        int target = 8;
        System.out.println(solution.pathSum(node5, target));
    }
}
