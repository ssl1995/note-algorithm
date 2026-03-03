package com.ssl.note.leetcode.编号刷题.LC114_二叉树展开为链表;


import com.ssl.note.leetcode.utils.TreeNode;

import java.util.*;

/**
 * @author SongShengLin
 * @date 2022/2/21 10:55 PM
 * @description
 */
public class Solution1 {
  /**
   * 二叉树展开为链表
   * 输入：root = [1,2,5,3,4,null,6]
   * 输出：[1,null,2,null,3,null,4,null,5,null,6]
   * 注意：
   * （1）right 子指针指向链表中下一个结点，而左子指针始终为 null 。
   * （2）展开后的单链表与二叉树先序顺序相同
   * 最优解：原地转换
   */
  public void flatten(TreeNode root) {
    while (root != null) {
      // 核心：如果它有左子树，那么前序遍历中左子树的最右节点就是右子树的前驱
      if (root.left != null) {
        // 找到左子树的最右节点
        TreeNode rightmost = root.left;
        while (rightmost.right != null) {
          rightmost = rightmost.right;
        }
        // 右子树挂到左子树最右边
        rightmost.right = root.right;
        // 左子树移到右边
        root.right = root.left;
        root.left = null;
      }
      // 移动到下一个（沿right走）
      root = root.right;
    }
  }
}
