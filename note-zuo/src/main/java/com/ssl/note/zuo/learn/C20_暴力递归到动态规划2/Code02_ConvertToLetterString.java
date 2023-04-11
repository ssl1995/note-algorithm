package com.ssl.note.zuo.learn.C20_暴力递归到动态规划2;

public class Code02_ConvertToLetterString {

    /**
     * 数字转换为字母的可能性
     * str:只包含0-9字符,将str转换字母有多少种可能性？
     * 方法1：暴力尝试
     */
    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    /**
     * str[i.....]去转化，返回有多少种转化方法
     */
    public static int process(char[] str, int i) {
        // 越界了，说明之前的可能性是正确的，返回1
        if (i == str.length) {
            return 1;
        }
        // i没到最后，说明有字符
        // 之前的决定有问题
        if (str[i] == '0') {
            return 0;
        }
        // str[i] != '0'
        // 可能性一：i单转
        int ways = process(str, i + 1);
        // 可能性二：i和i+1是否可以转
        if (i + 1 < str.length && ((str[i] - '0') * 10 + str[i + 1] - '0') < 27) {
            ways += process(str, i + 2);
        }
        return ways;
    }

    /**
     * 数字转换为字母的可能性
     * str:只包含0-9字符,将str转换字母有多少种可能性？
     * 方法2：动态规划
     */
    public static int dp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        // 递归结束条件
        dp[N] = 1;
        // 由于动态转移是i+1后面开始判断，所以从N-1往前遍历
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] == '0') {
                continue;
            }
            int ways = dp[i + 1];
            if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
                ways += dp[i + 2];
            }
            dp[i] = ways;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String str = "111";
        System.out.println(number(str));
        System.out.println(dp(str));
    }

}
