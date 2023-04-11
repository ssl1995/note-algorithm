package com.ssl.note.leetcode.剑指Offer.第三版.第8章_树.q54_所有大于或等于节点的值之和;

import java.util.LinkedList;

/**
 * @author SongShengLin
 * @date 2021/10/13 8:40 上午
 * @description
 */
public class Solution {

    /**
     * 所有大于或等于节点的值之和
     * root是BST的根节点
     */
    public TreeNode convertBST(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        int sum = 0;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                // 改变常规的中序遍历，此时指向右孩子
                cur = cur.right;
            }
            // 颠倒后，出栈时候，>=cur的都已经遍历过了
            cur = stack.pop();
            // 累加>=的和
            sum += cur.val;
            cur.val = sum;
            cur = cur.left;
        }
        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node6 = new TreeNode(6);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);

        node4.left = node2;
        node4.right = node6;
        node2.left = node1;
        node2.right = node3;
        node6.left = node5;
        node6.right = node7;

        TreeNode root = solution.convertBST(node4);

        System.out.println(solution.serialize(root));
    }

    public String serialize(TreeNode root) {
        // 先序遍历，反序列化最方便，因为第一个字符就是根节点
        if (root == null) {
            return "#";
        }
        String left = serialize(root.left);
        String right = serialize(root.right);
        return root.val + "," + left + "," + right;
    }
}
