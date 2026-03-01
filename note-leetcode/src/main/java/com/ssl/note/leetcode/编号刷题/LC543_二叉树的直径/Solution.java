package com.ssl.note.leetcode.编号刷题.LC543_二叉树的直径;

import com.ssl.note.leetcode.utils.TreeNode;

/**
 * @author SongShengLin
 * @date 2022/2/27 12:01 AM
 * @description
 */
public class Solution {

  //   二叉树直径= 任意两个节点间的边最长,需要一个max去记录所有节点深度只和的最大值
  private int max = 0;

  /**
   * 二叉树的直径
   */
  public int diameterOfBinaryTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    // 单独使用一个函数计算max
    // 如果直接在diameterOfBinaryTree写，得到的是二叉树的深度，而不是二叉树的直径
    dfs(root);
    return max;
  }

  /*
   dfs=104题 二叉树的深度代码加一行
   */

  private int dfs(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = dfs(root.left);
    int right = dfs(root.right);
    // 比104，就多这一行
    max = Math.max(max, left + right);

    return Math.max(left, right) + 1;
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;

    System.out.println(solution.diameterOfBinaryTree(node1));

  }
}
