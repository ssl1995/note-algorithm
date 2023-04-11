package com.ssl.note.leetcode.剑指Offer.第三版.第10章_前缀树.q62_实现前缀树;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2021/10/18 9:20 上午
 * @description
 */
public class Trie {

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

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("a");
        System.out.println(trie.getPrefix("abcdsdadasd"));
    }
}
