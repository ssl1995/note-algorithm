package com.ssl.note.leetcode.剑指Offer.第三版.第10章_前缀树.q64_神奇的字典;

/**
 * @author SongShengLin
 * @date 2021/10/21 6:16 下午
 * @description
 */
public class MagicDictionary {
    // 定义前缀树数据结构
    static class TrieNode {
        public TrieNode[] children;
        public boolean isWord;

        public TrieNode() {
            // 这里不能使用哈希表判断，因为要跳过一个字符
            children = new TrieNode[26];
        }
    }

    private TrieNode root;

    public MagicDictionary() {
        root = new TrieNode();
    }

    public void buildDict(String[] dictionary) {
        for (String s : dictionary) {
            TrieNode cur = root;
            for (char c : s.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                // 遍历指针指向下一个父节点
                cur = cur.children[c - 'a'];
            }
            // 循环结束，遍历指针指向word单词结尾，最后一个单词字符设置为完整的一个单词true
            cur.isWord = true;
        }
    }

    public boolean search(String searchWord) {
        return dfs(root, searchWord, 0, 0);
    }

    /**
     * 深度优先遍历查找只修改一个字符的字符串
     *
     * @param cur 前缀树的根
     * @param word 单词
     * @param i    遍历单词的下标
     * @param edit word中已经修改的字符个数
     * @return 结果
     */
    private boolean dfs(TrieNode cur, String word, int i, int edit) {
        if (cur == null) {
            return false;
        }
        if (cur.isWord && i == word.length() && edit == 1) {
            return true;
        }
        if (i < word.length() && edit <= 1) {
            boolean found = false;
            for (int j = 0; j < 26 && !found; j++) {
                // 如果不匹配，就edit+1
                int next = j == word.charAt(i) - 'a' ? edit : edit + 1;
                found = dfs(cur.children[j], word, i + 1, next);
            }
            return found;
        }
        return false;
    }

    public static void main(String[] args) {
        String[] dict = {"new"};
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(dict);
        System.out.println(magicDictionary.search("now"));

    }

}
