package com.ssl.note.leetcode.编号刷题.LC101_对称二叉树;


import com.ssl.note.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: SongShengLin
 * @Date: 2022/06/14 3:24 PM
 * @Describe:
 */
public class Solution1 {

  /**
   * 验证二叉树是否对称
   */
  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    // ArrayDeque不能Offer null，LinkedList可以
    Deque<TreeNode> queue = new LinkedList<>();
    // 100相同的树是p，q，不是left和right
    queue.offer(root.left);
    queue.offer(root.right);

    while (!queue.isEmpty()) {
      TreeNode node1 = queue.poll();
      TreeNode node2 = queue.poll();
      if (node1 == null && node2 == null) {
        continue;
      }
      if (node1 == null || node2 == null || node1.val != node2.val) {
        return false;
      }

      queue.offer(node1.left);
      queue.offer(node2.right);

      queue.offer(node1.right);
      queue.offer(node2.left);
    }

    return true;
  }

}
