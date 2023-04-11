package com.ssl.note.leetcode.剑指Offer.第三版.第1章_整数的基础知识.q5_单词长度的最大乘积;

/**
 * @author SongShengLin
 * @date 2021/9/10
 */
public class Solution {

    /**
     * 给定一个字符串数组words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，
     * 它们长度的乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
     * 输入: words = ["abcw","baz","foo","bar","fxyz","abcdef"]
     * 输出: 16
     * 解释: 这两个单词为 "abcw", "fxyz"。它们不包含相同字符，且长度的乘积最大。
     */
    // map法：时间复杂度O(nk+n²)
    public int maxProduct(String[] words) {
        int N = 26;
        boolean[][] flags = new boolean[words.length][N];
        for (int i = 0; i < words.length; i++) {// 记录每个单词字符是否出现过
            for (char c : words[i].toCharArray()) {
                flags[i][c - 'a'] = true;
            }
        }
        int res = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                int k = 0;
                for (; k < N; k++) {
                    if (flags[i][k] && flags[j][k]) {
                        break;
                    }
                }
                if (k == N) {// k遍历到N=26，说明该单词已经排查完毕，和其余单词没有重复字符=符合要求
                    int prod = words[i].length() * words[j].length();
                    res = Math.max(res, prod);
                }
            }
        }
        return res;
    }
}
