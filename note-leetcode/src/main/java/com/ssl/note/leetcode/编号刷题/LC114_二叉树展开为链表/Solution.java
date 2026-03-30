package com.ssl.note.leetcode.编号刷题.LC114_二叉树展开为链表;


import com.ssl.note.common.utils.TreeNode;

import java.util.*;

/**
 * @author SongShengLin
 * @date 2022/2/21 10:55 PM
 * @description
 */
public class Solution {
  /**
   * 二叉树展开为链表
   * 输入：root = [1,2,5,3,4,null,6]
   * 输出：[1,null,2,null,3,null,4,null,5,null,6]
   * 注意：
   * （1）right 子指针指向链表中下一个结点，而左子指针始终为 null 。
   * （2）展开后的单链表与二叉树先序顺序相同
   */
  public void flatten(TreeNode root) {
    if (root == null) {
      return;
    }
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);

    TreeNode pre = null;
    // 结果集中的单链表是前序遍历的结果
    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();

      // 中序遍历前节点重置左右
      if (pre != null) {
        pre.left = null;
        pre.right = node;
      }
      pre = node;

      // 非递归前序：先右后左
      if (node.right != null) {
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    }
  }
}
