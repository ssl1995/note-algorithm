package com.ssl.note.zuo.learn.C21_暴力递归到动态规划3;

// 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/
public class Code01_PalindromeSubsequence {

    /**
     * LC516 最长回文子序列
     * 输入：s = "bbbab"
     * 输出：4
     * 解释：一个可能的最长回文子序列为 "bbbb" 。
     * 方法1：暴力尝试
     */
    public static int lpsl1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return f(str, 0, str.length - 1);
    }

    // str[L..R]最长回文子序列长度返回
    public static int f(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }
        // 普通情况的可能性:4种可能
        // 遇到这种思考怎么转换成动态规划
        int p1 = f(str, L + 1, R - 1);
        int p2 = f(str, L, R - 1);
        int p3 = f(str, L + 1, R);
        int p4 = str[L] == str[R] ? 2 + f(str, L + 1, R - 1) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    /**
     * LC516 最长回文子序列
     * 输入：s = "bbbab"
     * 输出：4
     * 解释：一个可能的最长回文子序列为 "bbbb" 。
     * 方法2：DP
     */
    public static int lpsl2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        // 初始化
        // 对角线后面那根线末尾位置，没有R-1，所以先填上
        dp[N - 1][N - 1] = 1;
        // 到N-1停止
        for (int i = 0; i < N - 1; i++) {
            // 对角线
            dp[i][i] = 1;
            // 对角线后面那根线
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        // (L,R)需要左下角(L+1,R-1)，左边(L,R-1),下边(L+1,R)三个位置
        // 所以从右下角开始，从左往右遍历
        for (int L = N - 3; L >= 0; L--) {
            // R从对角线后面的第二根线就开始往后遍历
            for (int R = L + 2; R < N; R++) {
                // 暴力递归还可以优化
                // 可能性p1 = dp[L+1][R-1]可能省掉，因为p2,p3意见算过p1
                dp[L][R] = Math.max(dp[L][R - 1], dp[L + 1][R]);
                if (str[L] == str[R]) {
                    dp[L][R] = Math.max(dp[L][R], 2 + dp[L + 1][R - 1]);
                }
                // 直接抄暴力递归
//                int p1 = dp[L + 1][R - 1];
//                int p2 = dp[L][R - 1];
//                int p3 = dp[L + 1][R];
//                int p4 = str[L] == str[R] ? 2 + dp[L + 1][R - 1] : 0;
//                dp[L][R] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][N - 1];
    }

    /**
     * 最长公共回文子序列长度
     * 方法3：反转str，然后求最长公共子序列
     */
    public static int longestPalindromeSubseq3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] str = s.toCharArray();
        char[] reverse = reverse(str);
        return longestCommonSubsequence(str, reverse);
    }

    public static char[] reverse(char[] str) {
        int N = str.length;
        char[] reverse = new char[str.length];
        for (int i = 0; i < str.length; i++) {
            reverse[--N] = str[i];
        }
        return reverse;
    }

    public static int longestCommonSubsequence(char[] str1, char[] str2) {
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[N - 1][M - 1];
    }

    public static int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                if (str[i] == str[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }
            }
        }
        return dp[0][N - 1];
    }

}
