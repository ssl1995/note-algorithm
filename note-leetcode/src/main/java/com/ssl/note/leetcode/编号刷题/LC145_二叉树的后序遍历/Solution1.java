package com.ssl.note.leetcode.编号刷题.LC145_二叉树的后序遍历;

import com.ssl.note.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution1 {

  /**
   * 二叉树后续遍历
   * 单栈+prev
   * 解决：时间和空间复杂度和双栈法是一样的
   */
  public List<Integer> postorderTraversal(TreeNode root) {
    if (root == null) {
      return new LinkedList<>();
    }
    List<Integer> res = new LinkedList<>();

    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode prev = null;

    while (!stack.isEmpty() || root != null) {
      // 一直遍历左
      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        // 栈顶元素先不弹出
        TreeNode peek = stack.peek();
        // 栈顶元素不能出栈：右子树存在 且 没有访问过，就再访问右子树
        if (peek.right != null && peek.right != prev) {
          root = peek.right;
        } else {
          // 如果右子树为空，或右子树已经访问过，则可以出栈
          TreeNode pop = stack.pop();
          res.add(pop.val);
          // 记录访问过的右子树
          prev = pop;
        }
      }
    }

    return res;
  }
}
