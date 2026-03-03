package com.ssl.note.leetcode.编号刷题.LC199_二叉树的右视图;

import com.ssl.note.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: SongShengLin
 * @Date: 2022/08/16 17:43
 * @Describe:
 */
public class Solution1 {

  /**
   * LC199：二叉树的右视图
   */
  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    dfs(root, 0, res);
    return res;
  }

  private void dfs(TreeNode node, int depth, List<Integer> res) {
    if (node == null) {
      return;
    }
    //  如果 depth == res.size()：
    //  说明这一层还没有记录过结果
    //  当前节点就是这一层第一个被访问到的
    //  因为先右后左，第一个到达的一定是最右边的
    if (depth == res.size()) {
      res.add(node.val);
    }
    depth++;

    // 先右左，这样右视图才能保证是第一个遍历到的
    dfs(node.right, depth, res);
    dfs(node.left, depth, res);
  }
}
