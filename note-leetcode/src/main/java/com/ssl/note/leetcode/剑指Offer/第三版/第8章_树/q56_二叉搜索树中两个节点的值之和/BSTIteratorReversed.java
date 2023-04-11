package com.ssl.note.leetcode.剑指Offer.第三版.第8章_树.q56_二叉搜索树中两个节点的值之和;

import java.util.LinkedList;

/**
 * @Author: SongShengLin
 * @Date: 2022/11/11 08:38
 * @Describe:
 */
public class BSTIteratorReversed {

    private LinkedList<TreeNode> stack;
    private TreeNode cur;

    public BSTIteratorReversed(TreeNode root) {
        stack = new LinkedList<>();
        cur = root;
    }

    public int pre() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
        cur = stack.pop();
        int res = cur.val;
        cur = cur.left;
        return res;
    }

    public boolean hasPre() {
        return !stack.isEmpty() || cur != null;
    }
}
