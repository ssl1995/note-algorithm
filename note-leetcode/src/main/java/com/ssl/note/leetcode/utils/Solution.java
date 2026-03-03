package com.ssl.note.leetcode.utils;

import java.util.*;

public class Solution {

  public List<List<Integer>> threeSum(int[] nums) {
    if (nums == null || nums.length < 3) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);

    int n = nums.length;
    for (int i = 0; i < n - 2; i++) {
      if (nums[i] > 0) {
        break;
      }

      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      int left = i + 1;
      int right = n - 1;

      while (left < right) {
        int sum = nums[i] + nums[left] + nums[right];
        if (sum < 0) {
          left++;
        } else if (sum > 0) {
          right--;
        } else {
          List<Integer> temp = new ArrayList<>();
          temp.add(nums[i]);
          temp.add(nums[left]);
          temp.add(nums[right]);
          res.add(temp);

          while (left < right && nums[left] == nums[left + 1]) {
            left++;
          }

          while (left < right && nums[right] == nums[right - 1]) {
            right--;
          }

          left++;
          right--;
        }
      }
    }

    return res;
  }
}
