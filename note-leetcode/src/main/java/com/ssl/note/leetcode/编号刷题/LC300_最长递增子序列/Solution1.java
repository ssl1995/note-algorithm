package com.ssl.note.leetcode.编号刷题.LC300_最长递增子序列;

public class Solution1 {

  /**
   * 最长递增子序列
   * 输入：nums = [10,9,2,5,3,7,101,18]
   * 输出：4
   * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4
   * 方法：贪心+二分查找优化，
   * 时间复杂度：O(nlogn)
   */
  public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    // 维护一个递增序列 tail，其中 tail[i] 表示长度为 i+1 的最长递增子序列的最小末尾元素。
    int[] tail = new int[nums.length];
    // tail的长度就是最长递增子序列的长度
    int len = 0;

    for (int num : nums) {
      int left = 0, right = len;
      // 对于每个新元素 num，在 tail 中找到第一个大于等于 num 的位置，替换为 num。
      while (left < right) {
        int mid = left + (right - left) / 2;
        if (tail[mid] < num) {
          left = mid + 1;
        } else {
          right = mid;
        }
      }

      tail[left] = num;
      if (left == len) {
        len++;
      }
    }

    return len;
  }


  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] arr1 = {10, 9, 2, 5, 3, 7, 101, 18};
    //   tails = {2, 3, 7, 18, 0, 0, 0, 0};
    System.out.println(solution.lengthOfLIS(arr1));
  }
}