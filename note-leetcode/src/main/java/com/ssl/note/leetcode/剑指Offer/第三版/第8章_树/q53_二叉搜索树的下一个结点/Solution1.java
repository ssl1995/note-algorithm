package com.ssl.note.leetcode.剑指Offer.第三版.第8章_树.q53_二叉搜索树的下一个结点;

/**
 * @author SongShengLin
 * @date 2021/10/12 8:49 上午
 * @description
 */
public class Solution1 {
    /**
     * 找二叉搜索树中的中序后继结点
     * 法2：BST的p结点的下一个结点：>p，且是它右孩子中最小的那个
     * 时间复杂度O(高度)
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        TreeNode cur = root;
        TreeNode res = null;

        while (cur != null) {
            // <=p的都不看，直接往右走
            if (cur.val <= p.val) {
                cur = cur.right;
            } else {
                res = cur;
                cur = cur.left;
            }
        }

        return res;
    }

    public static void main(String[] args) {
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

        Solution1 solution1 = new Solution1();
        System.out.println(solution1.inorderSuccessor(node8, node8).val);
    }
}
