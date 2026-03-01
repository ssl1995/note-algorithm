package com.ssl.note.leetcode.编号刷题.LC226_翻转二叉树;

import com.ssl.note.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author SongShengLin
 * @date 2022/6/18 21:34
 * @description
 */
public class Solution {

  /**
   * 翻转二叉树
   * 前序遍历-递归
   */
  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }

    TreeNode left = root.left;
    TreeNode right = root.right;

    root.left = right;
    root.right = left;

    invertTree(root.left);
    invertTree(root.right);

    return root;
  }

  /**
   * 前序遍历-非递归
   */
  public TreeNode invertTree1(TreeNode root) {
    if (root == null) {
      return null;
    }

    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      TreeNode left = node.left;
      TreeNode right = node.right;

      if (left != null) {
        stack.push(left);
      }
      if (right != null) {
        stack.push(right);
      }

      node.left = right;
      node.right = left;
    }

    return root;
  }
}
