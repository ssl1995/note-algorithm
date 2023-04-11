package com.ssl.note.leetcode.剑指Offer.第三版.第7章_队列.q43_在完全二叉树中添加结点;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author SongShengLin
 * @date 2021/10/8 9:19 上午
 * @description
 */
public class CBTInserter {
    private Queue<TreeNode> queue;
    private TreeNode head;

    public CBTInserter(TreeNode root) {
        if (root == null) {
            return;
        }
        head = root;
        queue = new LinkedList<>();
        queue.offer(root);
        // 优化，因为是每次往完全二叉树添加节点，左右孩子节点双全的没必要再次遍历
        // 好处：每次队列头部都保留着左右孩子不双全的结点
        while (queue.peek().left != null && queue.peek().right != null) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public int insert(int v) {
        //
        TreeNode parent = queue.peek();
        if (parent == null) {
            return -1;
        }
        TreeNode node = new TreeNode(v);
        if (parent.left == null) {
            parent.left = node;
        } else {
            parent.right = node;
            queue.poll();
            queue.offer(parent.left);
            queue.offer(parent.right);
        }
        return parent.val;
    }

    public TreeNode get_root() {
        return this.head;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(0);

        CBTInserter cbtInserter = new CBTInserter(head);
        System.out.println(cbtInserter.insert(1));
        System.out.println(cbtInserter.insert(2));
        System.out.println(cbtInserter.insert(3));
        System.out.println(cbtInserter.insert(4));
    }
}
