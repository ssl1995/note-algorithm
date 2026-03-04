package com.ssl.note.leetcode.utils;

import java.util.*;

public class Solution {

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      map.put(inorder[i], i);
    }

    return dfs(preorder, map, 0, 0, preorder.length - 1);
  }


  private TreeNode dfs(int[] preorder, Map<Integer, Integer> map,
                       int preRoot, int inLeft, int inRight) {
    if (inLeft > inRight) {
      return null;
    }

    TreeNode node = new TreeNode(preorder[preRoot]);

    int rootIndex = map.get(preorder[preRoot]);
    int leftSize = rootIndex - inLeft;

    node.left = dfs(preorder, map, preRoot + 1, inLeft, rootIndex - 1);
    node.right = dfs(preorder, map, preRoot + leftSize + 1, rootIndex + 1, inRight);

    return node;
  }
}
