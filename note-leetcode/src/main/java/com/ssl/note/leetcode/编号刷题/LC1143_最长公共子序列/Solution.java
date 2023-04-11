package com.ssl.note.leetcode.编号刷题.LC1143_最长公共子序列;

public class Solution {
    /**
     * 最长公共子序列
     * 输入：text1 = "abcde", text2 = "ade"输出：3
     * 解释：最长公共子序列是 "ade"，它的长度为 3。
     */
    public int longestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        // i==j==0情况
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N - 1][M - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "abcde";
        String s2 = "ade";
        System.out.println(solution.longestCommonSubsequence(s1, s2));
    }
}
