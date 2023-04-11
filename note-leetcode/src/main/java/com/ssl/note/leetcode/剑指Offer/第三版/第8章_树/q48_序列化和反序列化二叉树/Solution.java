package com.ssl.note.leetcode.剑指Offer.第三版.第8章_树.q48_序列化和反序列化二叉树;

/**
 * @author SongShengLin
 * @date 2021/10/10 10:01 上午
 * @description
 */
public class Solution {
    // 序列化二叉树
    public String serialize(TreeNode root) {
        // 先序遍历，反序列化最方便，因为第一个字符就是根节点
        if (root == null) {
            return "#";
        }
        String left = serialize(root.left);
        String right = serialize(root.right);
        return root.val + "," + left + "," + right;
    }

    // 反序列化二叉树
    public TreeNode deserialize(String data) {
        String[] serialize = data.split(",");
        // 使用数组传递坐标，调用者可以改变
        int[] index = {0};
        return dfs(serialize, index);
    }

    private TreeNode dfs(String[] serialize, int[] index) {
        String nodeStr = serialize[index[0]];
        index[0]++;

        if (("#").equals(nodeStr)) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(nodeStr));
        node.left = dfs(serialize, index);
        node.right = dfs(serialize, index);

        return node;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(6);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        String serialize = solution.serialize(node1);
        // 6,6,6,#,#,6,#,#,6,#,#
        System.out.println(serialize);
        // 返回根节点
        System.out.println(solution.deserialize(serialize));
    }
}
