package com.ssl.note.leetcode.剑指Offer.第二版.数据结构.树.q55_I_二叉树的深度.练习;

import com.ssl.note.leetcode.utils.TreeNode;

/**
 * @author SongShengLin
 * @date 2022/1/16 3:07 PM
 * @description
 */
public class Solution {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
