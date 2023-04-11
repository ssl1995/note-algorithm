package com.ssl.note.leetcode.剑指Offer.第三版.第7章_队列.q44_二叉树每层的最大值;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author SongShengLin
 * @date 2021/10/9 9:13 上午
 * @description
 */
public class Solution {

    /**
     * 二叉树中每层的最大值
     * 方法1：单队列，和有限几个变量，节省空间复杂度
     */
    public List<Integer> largestValues(TreeNode root) {
        // 记录当前层的结点数量
        int current = 0;
        // 记录下一层的结点数量
        int next = 0;
        // 记录当前层的最大值
        int max = Integer.MIN_VALUE;

        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            queue.offer(root);
            current = 1;
        }

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            current--;
            max = Math.max(max, node.val);

            if (node.left != null) {
                queue.offer(node.left);
                next++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                next++;
            }

            // 当前层为0，更新下一层，记录这一层最大值结果
            if (current == 0) {
                current = next;
                next = 0;
                // 当cur=0时，才将最大值保存进结果集中
                res.add(max);
                max = Integer.MIN_VALUE;
            }
        }
        return res;
    }
}
