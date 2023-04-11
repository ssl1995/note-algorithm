package com.ssl.note.leetcode.剑指Offer.第三版.第8章_树.q57_值和下标之差都在给定的范围内;

import java.util.TreeSet;

/**
 * @author SongShengLin
 * @date 2021/10/14 8:52 上午
 * @description
 */
public class Solution {

    /**
     * 无序数组是否存在值和下标都在指定范围内的数
     *
     * @param nums 无序数组
     * @param k    下班之差绝对值<=k
     * @param t    值之和绝对值<=k
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            // set中ceiling:>=它的第一个数
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            // 不能缺少 ceiling <= (long) nums[i] + (long) t，因为数组是无序的，i前面的数都可能比i大
//            if (ceiling != null) {
//                return true;
//            }
            set.add((long) nums[i]);

            // set只保存i的前k个数
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        TreeSet<Long> set = new TreeSet<>();
//        set.add(2L);
//        set.add(3L);
//        set.add(6L);
//        set.add(10L);
//        System.out.println(set.ceiling(5L));
        int[] nums = {1, 5, 9, 1, 5, 9};
        int k = 2;
        int t = 2;
        Solution solution = new Solution();
        System.out.println(solution.containsNearbyAlmostDuplicate(nums, k, t));
    }
}
