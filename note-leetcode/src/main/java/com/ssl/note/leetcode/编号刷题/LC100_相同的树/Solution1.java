package com.ssl.note.leetcode.编号刷题.LC100_相同的树;

import com.ssl.note.leetcode.utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class Solution1 {

  /**
   * 相同的树
   */
  public boolean isSameTree(TreeNode p, TreeNode q) {
    // ArrayDeque不能Offer null，LinkedList可以
    Deque<TreeNode> queue = new LinkedList<>();
    //
    queue.offer(p);
    queue.offer(q);

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
      queue.offer(node2.left);

      queue.offer(node1.right);
      queue.offer(node2.right);

    }

    return true;
  }
}
