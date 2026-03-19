package com.ssl.note.leetcode.编号刷题.LC118_杨辉三角;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {

  /**
   * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
   * 1.第一行只有一个数字1。
   * 2.每一行的首尾数字都是1。
   * 3.每一行中间的数字等于上一行中相邻两个数字的和。
   * 输入: numRows = 5
   * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
   * - 时间复杂度：O(n²)，其中n是numRows，需要计算每个元素
   * - 空间复杂度：O(n²)，需要存储整个杨辉三角
   */
  public List<List<Integer>> generate(int numRows) {

    List<List<Integer>> res = new ArrayList<>();

    List<Integer> row = new ArrayList<>();
    row.add(1);
    res.add(row);

    for (int i = 1; i < numRows; i++) {
      List<Integer> pre = res.get(i - 1);

      List<Integer> cur = new ArrayList<>();
      // 第一个元素=1
      cur.add(1);
      // 中间元素=上一行相邻两个元素之和
      for (int j = 1; j < i; j++) {
        cur.add(pre.get(j - 1) + pre.get(j));
      }
      // 最后一个元素=1
      cur.add(1);

      res.add(cur);
    }

    return res;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int numRows = 5;
    System.out.println(solution.generate(numRows));
  }
}
