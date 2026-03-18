package com.ssl.note.leetcode.编号刷题.LC55_跳跃游戏;

/**
 * @author SongShengLin
 * @date 2022/1/26 8:58 AM
 * @description
 */
public class Solution1 {
  /**
   * 跳跃游戏
   * 数组中的每个元素代表你在该位置可以跳跃的最大长度。判断你是否能够到达最后一个下标。
   * 输入：nums = [2,3,1,1,4]
   * 输出：true
   * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
   */
  public boolean canJump(int[] nums) {
    if (nums == null || nums.length == 0) {
      return false;
    }
    if (nums.length == 1) {
      return true;
    }

    int maxReach = 0;
    for (int i = 0; i < nums.length; i++) {
      // 当前位置没有被曾经的最大跳跃位置命中
      if (i > maxReach) {
        return false;
      }
      // 计算当前可达的最长位置
      maxReach = Math.max(maxReach, i + nums[i]);
      // 是否超过数组长度
      if (maxReach >= nums.length - 1) {
        return true;
      }
    }
    // 是否超过数组长度
    return maxReach >= nums.length - 1;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {2, 3, 1, 1, 4};
    System.out.println(solution.canJump(nums));
  }
}
