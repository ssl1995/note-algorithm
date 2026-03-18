package com.ssl.note.leetcode.编号刷题.LC45_跳跃游戏II;

public class Solution {

  /**
   * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置在下标 0。
   * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在索引 i 处，你可以跳转到任意 (i + j) 处：
   * 0 <= j <= nums[i] 且
   * i + j < n
   * 返回到达 n - 1 的最小跳跃次数。测试用例保证可以到达 n - 1。
   * 示例 1:
   * 输入: nums = [2,3,1,1,4]
   * 输出: 2
   * 解释: 跳到最后一个位置的最小跳跃数是 2。
   * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
   */
  public int jump(int[] nums) {
    // -1表示不可达，但是本题case说明数据肯定可达
    if (nums.length == 0) {
      return -1;
    }
    if (nums.length == 1) {
      return 0;
    }
    // 从当前跳跃范围内的所有位置出发，能够到达的最远位置
    int maxReach = 0;
    // 上一次跳跃能到达的最远位置
    int end = 0;
    int jump = 0;
    for (int i = 0; i < nums.length; i++) {
      // -1表示不可达，但是本题case说明数据肯定可达
      if (i > maxReach) {
        return -1;
      }
      maxReach = Math.max(maxReach, i + nums[i]);
      // 当遍历到end时，已经评估完当前跳跃范围内的所有可能起点
      // 此时需要进行一次跳跃，end更新为新的maxReach
      if (i == end) {
        end = maxReach;
        jump++;
        if (end >= nums.length - 1) {
          return jump;
        }
      }
    }
    return jump;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {2, 3, 1, 1, 4};
    System.out.println(solution.jump(nums));
  }
}
