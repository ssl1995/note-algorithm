package com.ssl.note.leetcode.编号刷题.LC104_二叉树最大深度;

import com.ssl.note.leetcode.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution1 {

  /**
   * 求一个二叉树的最大深度
   * 层序遍历，记录深度
   */
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);

    int res = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode poll = queue.poll();
        if (poll == null) {
          continue;
        }
        if (poll.left != null) {
          queue.offer(poll.left);
        }
        if (poll.right != null) {
          queue.offer(poll.right);
        }
      }
      // 每一次层序遍历都得算完才能计算，耗时比较长
      res++;
    }
    return res;
  }


}
