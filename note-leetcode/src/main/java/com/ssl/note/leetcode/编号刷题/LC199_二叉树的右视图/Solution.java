package com.ssl.note.leetcode.编号刷题.LC199_二叉树的右视图;

import com.ssl.note.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: SongShengLin
 * @Date: 2022/08/16 17:43
 * @Describe:
 */
public class Solution {

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
        // 如何将右视图装进结果集：根 右 左
        if (depth == res.size()) {
            res.add(node.val);
        }
        depth++;
        dfs(node.right, depth, res);
        dfs(node.left, depth, res);
    }
}
