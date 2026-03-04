package com.ssl.note.leetcode.编号刷题.LC437_路径之和III;


import com.ssl.note.leetcode.utils.TreeNode;

import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

public class Solution {

  // int类型不要做对象参数传递
  private int res = 0;

  /**
   * 路径之和III
   * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
   * 输出：3
   * 解释：和等于 8 的路径有 3 条，如图所示。
   * LC560 和为k的子数组的二叉树版本
   */
  public int pathSum(TreeNode root, int targetSum) {
    // 后序要用root.val,防止空指针
    if (root == null) {
      return 0;
    }

    // 初始化：路径和0，出现的次数为1
    Map<Long, Integer> map = new HashMap<>();
    map.put(0L, 1);

    dfs(root, root.val, targetSum, map);

    return res;
  }

  /**
   * long 防止溢出,为了兼容TreeNode的val定义，使用基本数据类型
   */
  private void dfs(TreeNode node, long sum,
                   Integer target, Map<Long, Integer> map) {
    // sum - target = 曾经的某个前缀和，如果出现过，说明结果+1
    if (map.containsKey(sum - target)) {
      res += map.get(sum - target);
    }

    map.put(sum, map.getOrDefault(sum, 0) + 1);

    if (node.left != null) {
      dfs(node.left, sum + node.left.val, target, map);
    }
    if (node.right != null) {
      dfs(node.right, sum + node.right.val, target, map);
    }

    // DFS从左子树回到父节点后要去右子树，左子树路径上的前缀和不能留在 map 中影响右子树的计算
    // 回溯，消除该节点路径和的影响
    map.put(sum, map.getOrDefault(sum, 0) - 1);
  }


}
