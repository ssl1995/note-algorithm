package com.ssl.note.leetcode.剑指Offer.第三版.第8章_树.q56_二叉搜索树中两个节点的值之和;

/**
 * @Author: SongShengLin
 * @Date: 2022/11/11 08:36
 * @Describe:
 */
public class Solution2 {
    /**
     * 寻找二叉搜索树中，是否存在节点值之和为k
     * 方法2：模拟排序数组查找2数之和，BST可使用2个迭代器遍历，时间空间复杂度O(h)
     */
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        BSTIterator nextIt = new BSTIterator(root);
        BSTIteratorReversed preIt = new BSTIteratorReversed(root);

        int next = nextIt.next();
        int pre = preIt.pre();
        // 循环结束条件，两个指针相遇
        while (next != pre) {
            if (next + pre == k) {
                return true;
            } else if (next + pre < k) {
                next = nextIt.next();
            } else {
                pre = preIt.pre();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
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
        int t = 12;
        boolean target = solution2.findTarget(node8, t);
        System.out.println("存在:" + target);
    }
}
