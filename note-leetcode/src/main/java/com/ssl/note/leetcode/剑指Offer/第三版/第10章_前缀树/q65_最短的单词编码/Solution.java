package com.ssl.note.leetcode.剑指Offer.第三版.第10章_前缀树.q65_最短的单词编码;

/**
 * @author SongShengLin
 * @date 2021/10/22 9:36 上午
 * @description
 */
public class Solution {

    private int count;

    /**
     * 最短单词编码
     * 输入：words = ["time", "me", "bell"]
     * 输出：10
     * 解释：一组有效编码为 s = "time#bell#" 和 indices = [0, 2, 5] 。
     * words[0] = "time" ，s 开始于 indices[0] = 0 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
     * words[1] = "me" ，s 开始于 indices[1] = 2 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
     * words[2] = "bell" ，s 开始于 indices[2] = 5 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
     */
    public int minimumLengthEncoding(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        TrieNode root = build(words);

        dfs(root, 1);
        return count;
    }

    private void dfs(TrieNode node, int len) {
        boolean isLeaf = true;

        for (TrieNode next : node.nexts) {
            if (next != null) {
                isLeaf = false;
                dfs(next, len + 1);
            }
        }

        if (isLeaf) {
            count += len;
        }
    }

    private TrieNode build(String[] words) {
        if (words == null || words.length == 0) {
            return new TrieNode();
        }
        // 记录根节点
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode node = root;

            char[] cs = word.toCharArray();
            // 从后往前，形成后缀字符串的前缀树
            for (int j = cs.length - 1; j >= 0; j--) {
                char c = cs[j];
                if (node.nexts[c - 'a'] == null) {
                    node.nexts[c - 'a'] = new TrieNode();
                }
                node = node.nexts[c - 'a'];
            }
        }
        return root;
    }

    class TrieNode {
        private TrieNode[] nexts;

        public TrieNode() {
            nexts = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = {"time", "me", "bell"};
        System.out.println(solution.minimumLengthEncoding(words));
    }
}
