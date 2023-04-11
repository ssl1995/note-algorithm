package com.ssl.note.leetcode.编号刷题.LC337_打家劫舍III;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();

        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = reader.nextInt();
        }

        int[] tmp1 = new int[n - 1];
        int[] tmp2 = new int[n - 1];
        System.arraycopy(p, 0, tmp1, 0, n - 1);
        System.arraycopy(p, 1, tmp2, 0, n - 1);
        System.out.println(Math.max(helper(tmp1), helper(tmp2)));
    }

    // 打家劫舍II
    public static int helper(int[] p) {
        if (p == null || p.length == 0)
            return 0;
        int n = p.length;
        int[][] dp = new int[n][2];

        // init
        dp[0][0] = 0;
        dp[0][1] = p[0];

        // dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        // dp[i][1] = dp[i - 1][0] + p[i]

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + p[i];
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}