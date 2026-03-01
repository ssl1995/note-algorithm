package com.ssl.note.leetcode.编号刷题.LC101_对称二叉树;


import com.ssl.note.leetcode.utils.TreeNode;

/**
 * @Author: SongShengLin
 * @Date: 2022/06/14 3:24 PM
 * @Describe:
 */
public class Solution {

  /**
   * 验证二叉树是否对称
   */
  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    // 比较2个孩子
    return process(root.left, root.right);
  }

  private boolean process(TreeNode p, TreeNode q) {
    // 两个都为空，是对称
    if (p == null && q == null) {
      return true;
    }
    // 有1个不为空，1个为空，不是对称
    if (p == null || q == null) {
      return false;
    }
    // 对称的条件：镜子原理，当前值相同 且 左=右 且 右=左
    return p.val == q.val && process(p.left, q.right) && process(p.right, q.left);
  }
}
