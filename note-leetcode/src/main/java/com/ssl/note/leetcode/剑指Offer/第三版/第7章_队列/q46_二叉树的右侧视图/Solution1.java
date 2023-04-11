package com.ssl.note.leetcode.剑指Offer.第三版.第7章_队列.q46_二叉树的右侧视图;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: SongShengLin
 * @Date: 2022/11/07 09:36
 * @Describe:
 */
public class Solution1 {

    /**
     * 二叉树的右侧视图
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> queue1 = new LinkedList<>();
        queue1.add(root);
        Deque<TreeNode> queue2 = new LinkedList<>();

        List<Integer> res = new ArrayList<>();
        while (!queue1.isEmpty()) {
            TreeNode node = queue1.poll();

            if (node.left != null) {
                queue2.offer(node.left);
            }

            if (node.right != null) {
                queue2.offer(node.right);
            }

            if (queue1.isEmpty()) {
                res.add(node.val);

                queue1 = queue2;
                queue2 = new LinkedList<>();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.right = node5;
        node3.right = node4;

        System.out.println(solution1.rightSideView(node1));
    }
}
