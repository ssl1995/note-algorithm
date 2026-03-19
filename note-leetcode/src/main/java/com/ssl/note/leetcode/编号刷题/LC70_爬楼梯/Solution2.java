package com.ssl.note.leetcode.编号刷题.LC70_爬楼梯;

/**
 * @author SongShengLin
 * @date 2022/1/27 8:57 AM
 * @description
 */
public class Solution2 {

  /**
   * 爬楼梯
   * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
   * n=1,返回1;n=2,返回2;n=3，返回3;
   * - 时间复杂度：O(n)，只需一次遍历
   * - 空间复杂度：O(1)，只需要常数级别的额外空间
   */
  public int climbStairs(int n) {
    if (n <= 2) {
      return n;
    }
    int a = 1;
    int b = 2;

    for (int i = 3; i <= n; i++) {
      int c = a + b;

      a = b;
      b = c;
    }
    // 返回b的写法
    return b;
  }

  // 返回c的写法
  public int climbStairs1(int n) {
    if (n <= 2) {
      return n;
    }
    int a = 1;
    int b = 2;
    int c = a + b;

    for (int i = 3; i <= n; i++) {
      c = a + b;

      a = b;
      b = c;
    }

    return c;
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    int n = 4;
    // 3
    System.out.println(solution.climbStairs1(n));
  }

}
