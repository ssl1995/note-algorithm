package com.ssl.note.leetcode.编号刷题.LC94_二叉树的中序遍历;

import com.ssl.note.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
  /**
   * 二叉树中序遍历
   */
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new LinkedList<>();

    Deque<TreeNode> stack = new ArrayDeque<>();

    while (!stack.isEmpty() || root != null) {
      // 不断将左子节点压栈，直到左子树为空
      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        TreeNode node = stack.pop();
        res.add(node.val);
        root = node.right;
      }
    }

    return res;
  }
}
