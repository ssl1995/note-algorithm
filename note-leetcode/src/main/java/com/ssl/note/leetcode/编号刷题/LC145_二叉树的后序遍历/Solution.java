package com.ssl.note.leetcode.编号刷题.LC145_二叉树的后序遍历;

import com.ssl.note.leetcode.utils.TreeNode;

import java.util.*;

public class Solution {
  /**
   * 二叉树后续遍历
   * 双栈法
   */
  public List<Integer> postorderTraversal(TreeNode root) {
    if (root == null) {
      return new LinkedList<>();
    }
    List<Integer> res = new LinkedList<>();
    // 前序遍历的栈
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);
    // 辅助栈
    Deque<TreeNode> temp = new ArrayDeque<>();

    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      // 辅助栈底到栈顶：根右左，出栈才是左右根
      temp.push(node);
      // 正常栈底到栈顶：根左右，出栈是根右左
      if (node.left != null) {
        stack.push(node.left);
      }
      if (node.right != null) {
        stack.push(node.right);
      }
    }

    while (!temp.isEmpty()) {
      res.add(temp.pop().val);
    }

    return res;
  }
}
