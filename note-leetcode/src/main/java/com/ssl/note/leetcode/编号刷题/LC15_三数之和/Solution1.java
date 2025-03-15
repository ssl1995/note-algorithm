package com.ssl.note.leetcode.编号刷题.LC15_三数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {

  public List<List<Integer>> threeSum(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);


    for (int k = 0; k < nums.length - 2; k++) {
      if (nums[k] > 0) {
        continue;
      }
      if (k > 0 && nums[k] == nums[k - 1]) {
        continue;
      }
      int i = k + 1;
      int j = nums.length - 1;
      while (i < j) {
        int sum = nums[k] + nums[i] + nums[j];
        if (sum < 0) {
          i++;
        } else if (sum > 0) {
          j--;
        } else {
          res.add(Arrays.asList(nums[k], nums[i], nums[j]));
          i++;
          j--;
          while (i < j && nums[i] == nums[i - 1]) {
            i++;
          }
          while (i < j && nums[j] == nums[j + 1]) {
            j--;
          }
        }
      }
    }

    return res;
  }
}
