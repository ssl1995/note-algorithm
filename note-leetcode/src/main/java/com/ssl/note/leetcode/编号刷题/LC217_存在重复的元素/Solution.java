package com.ssl.note.leetcode.编号刷题.LC217_存在重复的元素;

/**
 * @author SongShengLin
 * @date 2023/3/4 09:10
 * @description
 */
public class Solution {
    /**
     * 存在重复的元素
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 如果有min、max重置说明无重复，continue
            if (min > nums[i]) {
                min = nums[i];
                continue;
            }
            if (max < nums[i]) {
                max = nums[i];
                continue;
            }

            for (int j = 0; j < i; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }

        return false;
    }
}
