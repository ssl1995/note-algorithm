package com.ssl.note.leetcode.编号刷题.LC198_打家劫舍;

public class Solution1 {
  /**
   * 打家劫舍
   * 要求：不能盗窃相邻房间
   * 输入：[2,7,9,3,1]
   * 输出：12
   * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
   * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
   */
  public int rob(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    if (nums.length == 1) {
      return nums[0];
    }

    int pre1 = nums[0];
    int pre2 = Math.max(nums[0], nums[1]);

    for (int i = 2; i < nums.length; i++) {
      // 陷阱：前一天不偷pre2 vs (前两天偷pre1 + 当天偷nums[i])
      int temp = Math.max(pre2, pre1 + nums[i]);
      pre1 = pre2;
      pre2 = temp;
    }
    return pre2;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {2, 7, 9, 3, 1};
    System.out.println(solution.rob(nums));
  }
}
