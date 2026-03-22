package com.ssl.note.leetcode.编号刷题.LC416_分割等和子集;

public class Solution1 {
  /**
   * 分割等和子集
   * 将这个数组分割成两个子集，使得两个子集的元素和相等。
   * 输入：nums = [1,5,11,5]
   * 输出：true
   * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
   */
  public boolean canPartition(int[] nums) {
    // 边界检查：数组为空直接返回false
    if (nums == null) {
      return false;
    }
    
    // 计算数组元素的总和
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    
    // 如果总和是奇数，无法分割成两个和相等的子集，直接返回false
    if ((sum & 1) == 1) {
      return false;
    }

    // 目标值为总和的一半
    int target = sum / 2;

    // dp数组：dp[j]表示是否可以通过选择数组中的元素，使得它们的和等于j
    boolean[] dp = new boolean[target + 1];
    // 初始化：和为0总是可以实现的（不选任何元素）
    dp[0] = true;

    // 遍历数组中的每个元素
    for (int num : nums) {
      // 从target开始倒序遍历，避免重复选择同一元素 =01背包
      for (int j = target; j >= num; j--) {
        // 状态转移方程：
        // dp[j] = dp[j] || dp[j - num]
        // 表示不选当前元素时的状态，或选当前元素时的状态（即j - num是否可以实现）
        dp[j] = dp[j] || dp[j - num];
      }
    }

    // 返回是否可以找到和为target的子集
    return dp[target];
  }

  public static void main(String[] args) {
    Solution1 solution1 = new Solution1();
    int[] nums = {2, 2, 3, 5};
    System.out.println(solution1.canPartition(nums));
  }
}