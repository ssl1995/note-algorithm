package com.ssl.note.leetcode.编号刷题.LC124_二叉树中的最大路径和;

import com.ssl.note.leetcode.utils.TreeNode;


public class Solution {

  // 初始化：MIN值，防止root=负数，直接返回0
  private int maxSum = Integer.MIN_VALUE;

  /**
   * 二叉树中的最大路径和
   * 输入：root = [-10,9,20,null,null,15,7]
   * 输出：42
   * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
   * 注意：路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
   */
  public int maxPathSum(TreeNode root) {
    maxGain(root);
    return maxSum;
  }

  private int maxGain(TreeNode node) {
    if (node == null) {
      return 0;
    }
    // 后续遍历获得左右子树最大路径和
    // 注意：左右子树最大路径和可能是负数，需要与0比较取较大值
    int left = Math.max(maxGain(node.left), 0);
    int right = Math.max(maxGain(node.right), 0);

    // 路径和可以拐弯，所以自己+左+右
    maxSum = Math.max(maxSum, node.val + left + right);

    // 返回给上级信息不能拐弯：自己 + 左右的最大
    return node.val + Math.max(left, right);
  }

}
