package com.ssl.note.leetcode.剑指Offer.第三版.第8章_树.q47_二叉树剪枝;

/**
 * @author SongShengLin
 * @date 2021/10/9 7:42 下午
 * @description
 */
public class Solution {

    /**
     * 二叉树的剪枝
     * 节点值只能是1or0，删除0的节点
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        // 要删除一个值为0的节点，必须是它的左右孩子都是0，并且自身值是0
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }

        return root;
    }
}
