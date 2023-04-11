package com.ssl.note.leetcode.编号刷题.LC18_四数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/10/7 15:38
 * @description
 */
public class Solution {

    /**
     * 四数之和
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // 结果数组是元素值，联想到使用双指针->排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 去重：i从第1个数后，和前面的数相同的话，就不用再判断了
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < nums.length; j++) {

                // 去重：i从第1个数后，和前面的数相同的话，就不用再判断了
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }


                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        //  去重：left和right需要去重
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        right--;
                        left++;
                    }
                }
            }

        }
        return res;
    }
}
