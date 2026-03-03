package com.ssl.note.leetcode.编号刷题.LC98_验证二叉搜索树;

import com.ssl.note.leetcode.utils.TreeNode;

import java.util.LinkedList;


public class Solution {

  /**
   * pre是会超过int类型的边界值,所以使用Long的边界值
   */
  private long pre = Long.MIN_VALUE;

  /**
   * 验证是否是BST
   * 中序递归法
   */
  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    // 左子树
    boolean left = isValidBST(root.left);
    if (!left) {
      return false;
    }

    // 陷阱：不能只比较当前节点: 左<根<右
    // 正确：子树所有节点<根<右子树所有节点，而不只是直接孩子
    if (root.val <= pre) {
      return false;
    }
    pre = root.val;

    // 右子树
    boolean right = isValidBST(root.right);
    if (!right) {
      return false;
    }

    return true;
  }


  /**
   * 验证是否是BST
   * 中序非递归
   */
  public boolean isValidBST1(TreeNode root) {
    if (root == null) {
      return true;
    }
    LinkedList<TreeNode> stack = new LinkedList<>();
    long pre = Long.MIN_VALUE;
    while (!stack.isEmpty() || root != null) {
      // 中序非递归遍历先把所有左子树入栈
      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        // 左子树到null就出栈，操作+入右子树
        root = stack.pop();

        if (root.val <= pre) {
          return false;
        } else {
          pre = root.val;
        }

        root = root.right;
      }
    }
    return true;
  }


}
