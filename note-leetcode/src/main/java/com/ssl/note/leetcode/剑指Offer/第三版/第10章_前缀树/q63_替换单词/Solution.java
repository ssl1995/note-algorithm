package com.ssl.note.leetcode.剑指Offer.第三版.第10章_前缀树.q63_替换单词;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2021/10/19 8:46 上午
 * @description
 */
public class Solution {

    /**
     * 替换单词
     * 输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
     * 输出："the cat was rat by the bat "
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        if (dictionary == null || sentence == null) {
            return "";
        }
        // 1.建立dict前缀树
        Trie trie = new Trie();
        for (String dict : dictionary) {
            trie.insert(dict);
        }
        // 2.匹配到就放入结果集，匹配不到就放入原单词
        StringBuilder sb = new StringBuilder();
        String[] sentenceList = sentence.split(" ");
        for (String word : sentenceList) {
            String prefix = trie.getPrefix(word);
            if (prefix == null || prefix.equals("")) {
                sb.append(word).append(" ");
            } else {
                sb.append(prefix).append(" ");
            }
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> dict = new ArrayList<>();
        dict.add("cat");
        dict.add("bat");
        dict.add("rat");
        String sentence = "the cattle was rattled by the battery";
        System.out.println(solution.replaceWords(dict, sentence));
    }

    static class Trie {

        private Node root;

        static class Node {
            private int pass;
            private int end;
            private Map<Integer, Node> nexts;

            public Node() {
                pass = 0;
                end = 0;
                nexts = new HashMap<>();
            }
        }

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] cs = word.toCharArray();
            Node cur = root;
            int index = 0;
            for (char c : cs) {
                index = c;
                if (!cur.nexts.containsKey(index)) {
                    cur.nexts.put(index, new Node());
                }
                cur = cur.nexts.get(index);
                cur.pass++;
            }
            cur.end++;
        }

        public boolean search(String word) {
            if (word == null) {
                return false;
            }
            char[] cs = word.toCharArray();
            Node cur = root;
            int index = 0;
            for (char c : cs) {
                index = c;
                if (!cur.nexts.containsKey(index)) {
                    cur.nexts.put(index, new Node());
                }
                cur = cur.nexts.get(index);
            }
            return cur.end != 0;
        }

        public boolean startsWith(String prefix) {
            if (prefix == null) {
                return false;
            }
            char[] cs = prefix.toCharArray();
            Node cur = root;
            int index = 0;
            for (char c : cs) {
                index = c;
                if (!cur.nexts.containsKey(index)) {
                    cur.nexts.put(index, new Node());
                }
                cur = cur.nexts.get(index);
            }
            return cur.pass != 0;
        }

        public String getPrefix(String prefix) {
            if (prefix == null) {
                return "";
            }
            Node cur = root;
            int index = 0;
            StringBuilder sb = new StringBuilder();
            char[] cs = prefix.toCharArray();
            for (char c : cs) {
                index = c;
                if (!cur.nexts.containsKey(index)) {
                    return "";
                }
                cur = cur.nexts.get(index);
                sb.append((char) (index));
                if (cur.end != 0) {
                    return sb.toString();
                }
            }
            return sb.toString();
        }
    }

}
