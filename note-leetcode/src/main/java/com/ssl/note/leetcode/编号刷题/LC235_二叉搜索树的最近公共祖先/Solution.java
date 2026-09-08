package com.ssl.note.leetcode.编号刷题.LC235_二叉搜索树的最近公共祖先;

import com.ssl.note.common.utils.TreeNode;

public class Solution {

  /**
   * 二叉搜索树的最近公共祖先
   * 口诀：BST 的 LCA：p、q 都小往左走，都大往右走，分叉处就是答案。
   * 递归写法:和LC236普通二叉树的最近公共祖先做对比
   */
  public TreeNode lowestCommonAncestorRecur(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) {
      return null;
    }
    // p、q 都小往左走
    if (p.val < root.val && q.val < root.val) {
      return lowestCommonAncestorRecur(root.left, p, q);
    }
    // p、q 都大往右走
    if (p.val > root.val && q.val > root.val) {
      return lowestCommonAncestorRecur(root.right, p, q);
    }
    // 只要分叉就返回root
    return root;
  }

  /**
   * 更适合迭代写法：利用 BST 左小右大的性质，O(H) 时间，O(1) 空间
   */
  public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode cur = root;
    while (cur != null) {
      // 先判断是否能同侧走，因为会遍历到自己，不能有等于
      if (p.val < cur.val && q.val < cur.val) {
        cur = cur.left;
      } else if (p.val > cur.val && q.val > cur.val) {
        cur = cur.right;
      } else {
        return cur;
      }
    }
    return null;
  }

  /**
   * 更适合迭代写法：利用 BST 左小右大的性质，O(H) 时间，O(1) 空间
   */
  public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode cur = root;
    while (cur != null) {
      // 先判断是否是公共祖先，就可以有等于
      if ((p.val <= cur.val && cur.val <= q.val) || (q.val <= cur.val && cur.val <= p.val)) {
        return cur;
      } else if (p.val > cur.val) {
        cur = cur.right;
      } else {
        cur = cur.left;
      }
    }
    return null;
  }

}
