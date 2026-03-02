package com.ssl.note.leetcode.编号刷题.LC102_二叉树的层次遍历;

import com.ssl.note.leetcode.utils.TreeNode;

import java.util.*;

public class Solution {
  /**
   * 二叉树的层次遍历
   */
  public List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();

    Deque<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      // 关键：size锁定每一层从队列中遍历的长度
      int size = queue.size();
      // 每一层保存的遍历数据
      List<Integer> level = new ArrayList<>();

      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        if (node != null) {
          level.add(node.val);
          if (node.left != null) {
            queue.offer(node.left);
          }
          if (node.right != null) {
            queue.offer(node.right);
          }
        }
      }

      res.add(level);
    }

    return res;
  }


}
