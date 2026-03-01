package com.ssl.note.leetcode.编号刷题.LC226_翻转二叉树;

import com.ssl.note.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author SongShengLin
 * @date 2022/6/18 21:34
 * @description
 */
public class Solution1 {

  /**
   * 翻转二叉树
   * 后序遍历-递归
   */
  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }

    invertTree(root.left);
    invertTree(root.right);

    TreeNode left = root.left;
    TreeNode right = root.right;

    root.left = right;
    root.right = left;

    return root;
  }

  /**
   * 后序遍历-非递归
   */
  public TreeNode invertTree1(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode head = root;
    Deque<TreeNode> stack = new ArrayDeque<>();

    TreeNode prev = null;
    while (!stack.isEmpty() || root != null) {
      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        TreeNode peek = stack.peek();
        if (peek.right != null && peek.right != prev) {
          root = peek.right;
        } else {
          TreeNode pop = stack.pop();

          // 交换
          TreeNode left = pop.left;
          TreeNode right = pop.right;

          pop.left = right;
          pop.right = left;

          prev = pop;
        }
      }
    }

    return head;
  }
}
