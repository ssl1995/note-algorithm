package com.ssl.note.leetcode.剑指Offer.第三版.第2章_数组.q7_数组中和为0的3个数;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * 三数之和
     * 是否存在3个数使得a+b+c=0
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        // 1.将原数组排序
        // 原 nums = [-1,0,1,2,-1,-4]
        // 排序后 nums = [-4,-1,-1,0,1,2]
        Arrays.sort(nums);

        int n = nums.length;
        // 2.遍历
        for (int i = 0; i < n; i++) {
            // 3.已排序，若>0，就跳出
            if (nums[i] > 0) {
                break;
            }
            // 4.已排序，是否需要去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 5.固定i位置，后面的位置双指针判断sum==0
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                // 固定三个数和
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    // 6.加入结果集
                    // 因为数组已排序，从小到大:nums[i]、nums[left]、nums[right]比较好看
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 7.sum==0.相同就就要跳过无需判断
                    // sum==0时，left后面的值判断去重
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // sum==0时，right前面的值判断去重
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
