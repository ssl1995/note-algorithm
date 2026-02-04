package com.ssl.note.leetcode.编号刷题.LC1_两数之和;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2022/1/18 8:48 AM
 * @description
 */
public class Solution {

  /**
   * 两数之和
   * 输入：nums = [2,7,11,15], target = 9
   * 输出：[0,1]
   * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
   */
  public int[] twoSum(int[] nums, int target) {
    if (nums == null || nums.length <= 1) {
      return new int[]{-1, -1};
    }
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i <= nums.length - 1; i++) {
      int otherNum = target - nums[i];
      // 单词：containsKey
      if (!map.containsKey(otherNum)) {
        map.put(nums[i], i);
        // 单词：continue
        continue;
      }
      int otherIndex = map.get(otherNum);
      return new int[]{otherIndex, i};
    }
    return new int[]{-1, -1};

  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {2, 7, 11, 15};
    int target = 9;
    System.out.println(Arrays.toString(solution.twoSum(nums, target)));
  }
}
