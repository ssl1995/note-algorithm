package com.ssl.note.leetcode.编号刷题.LC279_完全平方数;

public class Solution1 {

  /**
   * 完全平方数
   * 输入：n = 12
   * 输出：3
   * 解释：12 = 4 + 4 + 4
   */
  public int numSquares(int n) {
    // 四平方和定理：任何正整数都可以表示为最多四个完全平方数的和
    // 如果n是完全平方数，返回1
    // 如果n可以表示为两个完全平方数之和，返回2
    // 如果n % 8 == 7，返回4（勒让德三平方定理）
    // 否则返回3

    // 判断是否是完全平方数
    if (isPerfectSquare(n)) {
      return 1;
    }

    // 判断是否等于两个完全平方数之和
    for (int i = 1; i * i <= n; i++) {
      if (isPerfectSquare(n - i * i)) {
        return 2;
      }
    }

    // 勒让德三平方定理：n = 4^a * (8b + 7)
    // 不断除去n中的因子4
    while (n % 4 == 0) {
      n /= 4;
    }
    if (n % 8 == 7) {
      return 4;
    }

    // 否则=3
    return 3;
  }

  private boolean isPerfectSquare(int n) {
    int sqrt = (int) Math.sqrt(n);
    return sqrt * sqrt == n;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int n = 12;
    // dp=[0, 1, 2, 3, 1, 2, 3, 4, 2, 1, 2, 3, 3]
    System.out.println(solution.numSquares(n));
  }
}

