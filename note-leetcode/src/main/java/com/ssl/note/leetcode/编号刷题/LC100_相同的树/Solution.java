package com.ssl.note.leetcode.编号刷题.LC100_相同的树;

import com.ssl.note.leetcode.utils.TreeNode;

public class Solution {

  /**
   * 相同的树
   */
  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    }

    if (p == null || q == null) {
      return false;
    }

    return p.val == q.val
        // 相同的树：左=左，右=右
        && isSameTree(p.left, q.left)
        && isSameTree(p.right, q.right);
  }
}
