package com.ssl.note.leetcode.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
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
          do {
            i++;
          } while (i < j && nums[i] == nums[i - 1]);
        } else if (sum > 0) {
          do {
            j--;
          } while (i < j && nums[j] == nums[j + 1]);
        } else {
          List<Integer> list = new ArrayList<>();
          list.add(nums[k]);
          list.add(nums[i]);
          list.add(nums[j]);
          res.add(list);
          do {
            i++;
          } while (i < j && nums[i] == nums[i - 1]);
          do {
            j--;
          } while (i < j && nums[j] == nums[j + 1]);
        }
      }
    }

    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {-1, 0, 1, 2, -1, -4};
    List<List<Integer>> res = solution.threeSum(nums);
    System.out.println(res);
  }

}
