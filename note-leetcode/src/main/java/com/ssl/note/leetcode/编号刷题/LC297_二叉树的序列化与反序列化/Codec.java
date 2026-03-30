package com.ssl.note.leetcode.编号刷题.LC297_二叉树的序列化与反序列化;

import com.ssl.note.common.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Codec {

  // 分隔符：反序列化确定队列
  private final String SPLIT = "!";
  // null符：前序遍历，把nul记录下来，一个前序序列就能唯一确定一棵树
  private final String NULL = "#";

  /**
   * 序列化-二叉树
   * 先序遍历
   */
  public String serialize(TreeNode root) {
    if (root == null) {
      // 难点：null节点也需要一个分隔符
      return NULL + SPLIT;
    }
    return root.val + SPLIT +
        serialize(root.left) +
        serialize(root.right);
  }

  /**
   * 反序列化-二叉树
   */
  public TreeNode deserialize(String data) {
    // 按照分隔符分割了，队列中已经没有分隔符啦
    String[] split = data.split(SPLIT);
    // 1 2 # # 3 4 # # 5 # #
    Deque<String> queue = new ArrayDeque<>();
    for (String str : split) {
      queue.offer(str);
    }
    return dfs(queue);
  }

  private TreeNode dfs(Queue<String> queue) {
    if (queue.isEmpty()) {
      return null;
    }
    // 1 2 # # 3 4 # # 5 # #
    String poll = queue.poll();
    if (poll.equals(NULL)) {
      return null;
    }
    TreeNode node = new TreeNode(Integer.parseInt(poll));
    node.left = dfs(queue);
    node.right = dfs(queue);
    return node;
  }

  public static void main(String[] args) {
    Codec codec = new Codec();
    TreeNode node1 = new TreeNode(12);
    TreeNode node2 = new TreeNode(3);
    node1.left = node2;

    String serialize = codec.serialize(node1);
    System.out.println("序列化：" + serialize);

    TreeNode root = codec.deserialize(serialize);
    System.out.println("反序列化后根：" + root.val);

  }
}
