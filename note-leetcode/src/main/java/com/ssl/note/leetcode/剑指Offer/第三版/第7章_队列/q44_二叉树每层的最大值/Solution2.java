package com.ssl.note.leetcode.剑指Offer.第三版.第7章_队列.q44_二叉树每层的最大值;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: SongShengLin
 * @Date: 2022/11/02 09:47
 * @Describe:
 */
public class Solution2 {


    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        int cur = 0;
        int next = 0;
        int max = Integer.MIN_VALUE;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        cur = 1;


        List<Integer> res = new LinkedList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            cur--;
            max = Math.max(max, node.val);

            if (node.left != null) {
                queue.offer(node.left);
                next++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                next++;
            }

            if (cur == 0) {
                res.add(max);
                cur = next;
                next = 0;
                max = Integer.MIN_VALUE;
            }
        }

        return res;
    }
}
