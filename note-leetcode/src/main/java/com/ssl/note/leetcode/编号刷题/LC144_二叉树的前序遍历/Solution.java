package com.ssl.note.leetcode.编号刷题.LC144_二叉树的前序遍历;

import com.ssl.note.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
  /**
   * 二叉树前序遍历
   */
  public List<Integer> preorderTraversal(TreeNode root) {
    if (root == null) {
      return new LinkedList<>();
    }
    List<Integer> res = new LinkedList<>();

    Deque<TreeNode> stack = new ArrayDeque<>();
    // 前序第一次压根节点
    stack.push(root);

    while (!stack.isEmpty()) {
      // 弹出
      root = stack.pop();
      // 加结果
      res.add(root.val);
      // 压右左孩子 = 栈出就是左右
      if (root.right != null) {
        stack.push(root.right);
      }
      if (root.left != null) {
        stack.push(root.left);
      }
    }
    return res;
  }
}
