package com.ssl.note.leetcode.编号刷题.LC10_正则表达式匹配;

/**
 * @author SongShengLin
 * @date 2022/1/19 11:31 PM
 * @description
 */
public class Solution {
    /**
     * 实现匹配.和*的正则表达式；.表示任意一个字符，*表示前面的字符可以出现任意次（包含0次）
     * 动态规划法：s=aaa，p=ab*.*，返回true
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // dp[i][j]表示s前i个字符,p前j个字符是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 初始化dp[0][0] :两个空串是匹配的
        dp[0][0] = true;
        // 初始化首行:i=0,将s为看为空串;j=2开始遍历,步长为2
        for (int j = 2; j < n + 1; j += 2) {
            // 偶数位匹配且p的奇数位全为*
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        // 动态转移
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                // 当p[j-1]=*时,有三种情况
                if (p.charAt(j - 1) == '*') {
                    if (dp[i][j - 2]) {
                        // 1.j的前一个数出现0次
                        dp[i][j] = true;
                    } else if (dp[i - 1][j] && s.charAt(i - 1) == p.charAt(j - 2)) {
                        // 2.j的位置前一个数出现1次
                        dp[i][j] = true;
                    } else if (dp[i - 1][j] && p.charAt(j - 2) == '.') {
                        // 3.万能匹配：.*表示任意一个字符出现任意次
                        dp[i][j] = true;
                    }
                } else {
                    // 当p[j-1]!=*时,有两种情况
                    if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) {
                        // 1.前面元素之前都匹配 且 当前元素也相同
                        dp[i][j] = true;
                    } else if (dp[i - 1][j - 1] && p.charAt(j - 1) == '.') {
                        // 2.前面元素之前都匹配 且 p的当期元素是.
                        dp[i][j] = true;
                    }
                }
            }
        }
        // 打印dp
        printDp(dp);
        return dp[m][n];
    }

    private void printDp(boolean[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String s = "aaa";
        String p = "ab*.*";
        Solution solution = new Solution();
        /*
         true	false	false	false	false	false
         false	true	false	true	false	true
         false	false	false	false	true	true
         false	false	false	false	false	true
         */
        System.out.println(solution.isMatch(s, p));
    }
}


