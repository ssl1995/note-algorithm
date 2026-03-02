package com.ssl.note.leetcode.编号刷题.LC108_将有序数组转换为二叉搜索树;

import com.ssl.note.leetcode.utils.TreeNode;

public class Solution {
  /**
   * 将有序数组转换为高度平衡二叉搜索树
   * 输入：nums = [-10,-3,0,5,9]
   * 输出：[0,-3,9,-10,null,5]
   */
  public TreeNode sortedArrayToBST(int[] nums) {
    if (nums == null) {
      return null;
    }
    return buildBST(nums, 0, nums.length - 1);
  }

  // 函数签名
  private TreeNode buildBST(int[] nums, int left, int right) {
    // 递归终止条件
    if (left > right) {
      return null;
    }
    // 做什么
    int mid = left + (right - left) / 2;
    TreeNode node = new TreeNode(nums[mid]);
    // 左右递归
    node.left = buildBST(nums, left, mid - 1);
    node.right = buildBST(nums, mid + 1, right);

    return node;
  }

}
