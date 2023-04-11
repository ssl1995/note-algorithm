package com.ssl.note.leetcode.剑指Offer.第二版.算法.排序.q03_数组中重复的数字;


import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 数组中重复的元素
     * 题目：nums长度为n，数字为0到n-1，有些数字重复了，找出任意重复的一个数字
     */
    public int findRepeatNumber(int[] nums) {
        int index = 0;
        while (index < nums.length) {
            // 数字范围为0到n-1,重排后，数字i应该放在下标为i的位置上
            // 0下标放元素0，不是重复元素，跳过本次循环，移动指针
            if (nums[index] == index) {
                // 不推荐使用for原因:index只有遇到相同时才移动
                index++;
                continue;
            }
            // x下标没有元素x
            // 如果元素x等于对应的x下标上元素，就是重复元素，返回
            if (nums[index] == nums[nums[index]]) {
                return nums[index];
            } else {// 否则，交换元素x来到它对应的x下标位置
                int temp = nums[index];
                nums[index] = nums[temp];
                // 不能使用swap方法原因：和swap方法不一样
                nums[temp] = temp;
            }
        }
        return -1;
    }

    /**
     * map方法
     * 缺点：多使用了一个map
     */
    public int findRepeatNumber1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!map.containsKey(num)) {
                map.put(num, 1);
                continue;
            }
            return num;
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(solution.findRepeatNumber(nums));
    }

}
