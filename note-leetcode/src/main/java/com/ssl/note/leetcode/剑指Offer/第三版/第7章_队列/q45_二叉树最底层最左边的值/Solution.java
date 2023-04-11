package com.ssl.note.leetcode.剑指Offer.第三版.第7章_队列.q45_二叉树最底层最左边的值;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author SongShengLin
 * @date 2021/10/9 2:49 下午
 * @description
 */
public class Solution {
    /**
     * 二叉树最底层最左边的值
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> queue1 = new LinkedList<>();
        queue1.add(root);
        Deque<TreeNode> queue2 = new LinkedList<>();
        int leftBottom = root.val;

        while (!queue1.isEmpty()) {
            TreeNode node = queue1.poll();

            if (node.left != null) {
                queue2.offer(node.left);
                // 不能再加入left时就判断是最底层的最左值，因为可能最底层只有最右边的值
//                leftBottom = node.left.val;
            }

            if (node.right != null) {
                queue2.offer(node.right);
            }

            if (queue1.isEmpty()) {
                Deque<TreeNode> temp = queue1;
                queue1 = queue2;
                queue2 = temp;
                // 在转换时候，判断最左值就是对的
                if (!queue1.isEmpty()) {
                    // 最左边的值 = First
                    leftBottom = queue1.peekFirst().val;
                    // 最右边的值 = Last
//                    leftBottom = queue1.peekLast().val;
                }
            }
        }

        return leftBottom;
    }
}
