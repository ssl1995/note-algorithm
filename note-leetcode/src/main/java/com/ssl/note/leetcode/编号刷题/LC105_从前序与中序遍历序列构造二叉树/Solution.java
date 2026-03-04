package com.ssl.note.leetcode.编号刷题.LC105_从前序与中序遍历序列构造二叉树;

import com.ssl.note.leetcode.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {

  /**
   * 给定两个整数数组 preorder 和 inorder，请构造二叉树并返回其根节点。
   * preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历
   * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
   * 输出: [3,9,20,null,null,15,7]
   */
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      map.put(inorder[i], i);
    }

    return dfs(0, 0, preorder.length - 1, preorder, map);
  }

  /**
   * 前序找根，中序划范围。
   * root   = 前序数组的指针，告诉你"根是谁"
   * left   = 中序数组的左边界，告诉你"从哪开始"
   * right  = 中序数组的右边界，告诉你"到哪结束"
   */
  private TreeNode dfs(int preRoot, int inLeft, int inRight,
                       int[] preorder, Map<Integer, Integer> map) {
    if (inLeft > inRight) {
      return null;
    }

    TreeNode node = new TreeNode(preorder[preRoot]);

    int rootIndex = map.get(preorder[preRoot]);
    // 左子树长度
    int leftSize = rootIndex - inLeft;

    node.left = dfs(preRoot + 1, inLeft, rootIndex - 1, preorder, map);
    node.right = dfs(preRoot + leftSize + 1, rootIndex + 1, inRight, preorder, map);

    return node;
  }
}
