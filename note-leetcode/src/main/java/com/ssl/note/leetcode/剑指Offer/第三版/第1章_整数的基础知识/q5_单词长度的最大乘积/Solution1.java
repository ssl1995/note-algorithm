package com.ssl.note.leetcode.剑指Offer.第三版.第1章_整数的基础知识.q5_单词长度的最大乘积;

/**
 * @author SongShengLin
 * @date 2021/9/10
 */
public class Solution1 {
    /**
     * 给定一个字符串数组words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，
     * 它们长度的乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
     * 输入: words = ["abcw","baz","foo","bar","fxyz","abcdef"]
     * 输出: 16
     * 解释: 这两个单词为 "abcw", "fxyz"。它们不包含相同字符，且长度的乘积最大。
     */
    // 法2：利用位运算将二维数组降低成一维数组
    public int maxProduct(String[] words) {
        // 将之前的二维数组中的二维，使用1位的int类型来判断字符串是否相同
        int[] flags = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                // abcw
                // 防止重复字符干扰结果,使用|=：0与0=0，其余为1，保证位运算
                flags[i] |= 1 << (c - 'a');
            }
        }
        int res = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                // 只有所有二进制不相同，才能说明不是含有相同字符的字符串
                // int[] flags = {4194311, 16416, 131075, 58720288, 63};
                if ((flags[i] & flags[j]) == 0) {
                    int prod = words[i].length() * words[j].length();
                    res = Math.max(res, prod);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"abcw", "foo", "bar", "fxyz", "abcdef"};
        Solution1 s1 = new Solution1();
        int res = s1.maxProduct(words);
        System.out.println(res);
        int[] flags = {4194311, 16416, 131075, 58720288, 63};
    }
}
