package com.ssl.note.leetcode.编号刷题.LC98_验证二叉搜索树;

import com.ssl.note.common.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

  /**
   * LC98_验证二叉搜索树
   * 中序非递归
   */
  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    Deque<TreeNode> stack = new ArrayDeque<>();
    // 中序遍历前一个节点
    TreeNode pre = null;

    while (root != null || !stack.isEmpty()) {
      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        TreeNode pop = stack.pop();
        if (pre != null && pre.val >= pop.val) {
          return false;
        } else {
          pre = pop;
          root = pop.right;
        }
      }
    }

    return true;
  }
}
