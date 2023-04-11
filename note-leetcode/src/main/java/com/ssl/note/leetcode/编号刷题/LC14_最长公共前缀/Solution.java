package com.ssl.note.leetcode.编号刷题.LC14_最长公共前缀;

import java.util.Objects;

/**
 * @Author: SongShengLin
 * @Date: 2022/09/07 10:24
 * @Describe:
 */
public class Solution {
    /**
     * 最长公共前缀
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * 如果不存在公共前缀，返回空字符串 ""。
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 1.取第一个字符串
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            // 2.j同时遍历res和子串
            for (; j < strs[i].length() && j < res.length(); j++) {
                char c1 = strs[i].charAt(j);
                char c2 = res.charAt(j);
                if (!Objects.equals(c1, c2)) {
                    break;
                }
            }
            // 3.取值
            res = strs[i].substring(0, j);
        }
        return res;
    }
}
