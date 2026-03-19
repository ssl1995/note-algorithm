package com.ssl.note.leetcode.编号刷题.LC70_爬楼梯;

public class Solution3 {

  /**
   * 爬楼梯
   * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
   * n=1,返回1;n=2,返回2;n=3，返回3;
   * 公式法：F(n) = [((1+√5)/2)^(n+1) - ((1-√5)/2)^(n+1)]/√5
   * - 时间复杂度：O(logn)，因为Math.pow的时间复杂度是O(logn)
   * - 空间复杂度：O(1)，只需要常数级别的额外空间
   */
  public int climbStairs(int n) {
    double sqrt5 = Math.sqrt(5);
    double fin = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
    return (int) Math.round(fin / sqrt5);
  }
}
