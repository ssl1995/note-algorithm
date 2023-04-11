package com.ssl.note.leetcode.编号刷题.LC236_二叉树最近公共祖先;


import com.ssl.note.algorithm.leetcode.utils.TreeNode;

/**
 * @author SongShengLin
 * @date 2022/6/19 10:43
 * @description
 */
public class Solution1 {

    /**
     * 二叉树最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        return process(root, p, q).node;
    }

    private Info process(TreeNode x, TreeNode a, TreeNode b) {
        if (x == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(x.left, a, b);
        Info rightInfo = process(x.right, a, b);
        boolean findA = (x == a) || leftInfo.findA || rightInfo.findA;
        boolean findB = (x == b) || leftInfo.findB || rightInfo.findB;
        TreeNode ans = null;
        if (leftInfo.node != null) {
            ans = leftInfo.node;
        } else if (rightInfo.node != null) {
            ans = rightInfo.node;
        } else {
            if (findA && findB) {
                ans = x;
            }
        }
        return new Info(findA, findB, ans);

    }

    class Info {
        boolean findA;
        boolean findB;
        TreeNode node;

        Info(boolean findA, boolean findB, TreeNode node) {
            this.findA = findA;
            this.findB = findB;
            this.node = node;
        }
    }
}
