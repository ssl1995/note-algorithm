package com.ssl.note.leetcode.编号刷题.LC230_二叉搜索树中第K小的元素;

import com.ssl.note.common.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
  /**
   * 二叉搜索树中第K小的元素
   * 中序迭代：BST中序遍历是递增序列，弹出第k个就是答案
   */
  public int kthSmallest(TreeNode root, int k) {
    if (root == null || k < 0) {
      return -1;
    }

    Deque<TreeNode> stack = new ArrayDeque<>();
    // 二叉搜素树，中序遍历天然就是有序的=数组，用count计数
    int count = k;
    while (!stack.isEmpty() || root != null) {
      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        TreeNode pop = stack.pop();

        // 计数
        count--;
        if (count == 0) {
          return pop.val;
        }

        root = pop.right;
      }
    }

    return -1;
  }
}
