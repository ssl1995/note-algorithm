package com.ssl.note.leetcode.编号刷题.LC98_验证二叉搜索树;

import com.ssl.note.common.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;


public class Solution1 {

  /**
   * 验证是否是BST
   * 中序非递归
   */
  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    // 中序遍历是用栈
    Deque<TreeNode> stack = new ArrayDeque<>();
    long pre = Long.MIN_VALUE;

    while (!stack.isEmpty() || root != null) {
      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        TreeNode pop = stack.pop();

        // 陷阱：不能只比较当前节点: 左<根<右
        // 正确：子树所有节点<根<右子树所有节点，而不只是直接孩子
        if (pop.val <= pre) {
          return false;
        }
        pre = pop.val;
        // 中序非递归写法只有遍历左子树才push，这里只是切换遍历指针
        root = pop.right;
      }
    }

    return true;
  }


}
