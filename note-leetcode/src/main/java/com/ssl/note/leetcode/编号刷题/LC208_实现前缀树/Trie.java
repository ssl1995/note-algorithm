package com.ssl.note.leetcode.编号刷题.LC208_实现前缀树;

public class Trie {

  // 前缀树一定要有根
  TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  // 前缀树加上一个单词
  public void insert(String word) {
    TrieNode cur = root;

    for (char c : word.toCharArray()) {
      int index = c - 'a';
      if (cur.children[index] == null) {
        TrieNode child = new TrieNode();
        cur.children[index] = child;
      }
      // 找下一个
      cur = cur.children[index];
    }
    // 插入操作，末尾节点标记为true
    cur.isEnd = true;
  }

  // 查询单词=isEnd必须为true
  public boolean search(String word) {
    return searchWord(word, false);
  }

  // 前缀查询=isEnd不需要为true，只要存在就行
  public boolean startsWith(String prefix) {
    return searchWord(prefix, true);
  }

  // 通用的查询方法，搜索单词或者前缀
  private boolean searchWord(String word, boolean isSearchPrefix) {
    TrieNode cur = root;

    for (char c : word.toCharArray()) {
      int index = c - 'a';
      if (cur.children[index] == null) {
        return false;
      }
      cur = cur.children[index];
    }

    return isSearchPrefix || cur.isEnd;
  }


  static class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    public TrieNode() {
      // 初始化数组，长度为26
      // 只适用于26个字母
      children = new TrieNode[26];
    }
  }

}
