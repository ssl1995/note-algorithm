package com.ssl.note.leetcode.编号刷题.LC1480_数组的动态和;

import java.util.Arrays;

/**
 * @Author: SongShengLin
 * @Date: 2022/12/28 10:10
 * @Describe:
 */
public class Solution {

    /**
     * 一维数组的动态和
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,6,10]
     * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
     */
    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int sum = 0;
        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            sum += nums[i];
            res[i] = sum;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] res = {1, 2, 3, 4};
        res = solution.runningSum(res);
        System.out.println(Arrays.toString(res));
    }
}
