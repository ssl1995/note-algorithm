package com.ssl.note.leetcode.编号刷题.LC199_二叉树的右视图;

import com.ssl.note.common.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Author: SongShengLin
 * @Date: 2022/08/16 17:43
 * @Describe:
 */
public class Solution {

  /**
   * LC199：二叉树的右视图
   * 层序遍历非递归
   */
  public List<Integer> rightSideView(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);

    List<Integer> res = new ArrayList<>();

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();

        if (node != null) {
          if (i == size - 1) {
            res.add(node.val);
          }
          if (node.left != null) {
            queue.offer(node.left);
          }
          if (node.right != null) {
            queue.offer(node.right);
          }
        }
      }
    }

    return res;
  }


}
