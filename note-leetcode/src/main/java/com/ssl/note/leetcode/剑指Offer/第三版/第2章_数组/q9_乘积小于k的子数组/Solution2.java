package com.ssl.note.leetcode.剑指Offer.第三版.第2章_数组.q9_乘积小于k的子数组;

/**
 * @author SongShengLin
 * @date 2021/9/14 9:24 上午
 */
public class Solution2 {

    /**
     * 乘积小于k的子数组个数
     * 方法2：滑动窗口
     * 时间复杂度:O(n),因为left和right移动次数都不超过n次
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0;// left、right初始化指向数组第一位
        long product = 1;// 乘积值
        int count = 0;// 统计次数
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            // 判断是否需要右移left：product>=k
            while (left <= right && product >= k) {
                product /= nums[left++];
            }
            // 统计次数
            count += right >= left ? right - left + 1 : 0;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] nums = {57, 44, 92, 28, 66, 60, 37, 33, 52, 38, 29, 76, 8, 75, 22};
        int t = 18;
        System.out.println(solution.numSubarrayProductLessThanK(nums, t));
    }
}
