package com.ssl.note.leetcode.编号刷题.LC215_数组中的第K大元素;

import java.util.Random;

/**
 * @author SongShengLin
 * @date 2022/6/18 18:35
 * @description
 */
public class Solution {


    /**
     * 数组中第k大的数
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     */
    public int findKthLargest(int[] nums, int k) {
        int t = nums.length - k;
        int left = 0;
        int right = nums.length - 1;

        // 防止顺序/倒叙数组造成的极端情况，每次都随机交换pivot和第一位的元素
        int pivot = partition(nums, left, right);
        // 快速排序 => 修改为类似二分的思想去快速选择
        while (pivot != t) {
            if (pivot < t) {
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }

            pivot = partition(nums, left, right);
        }

        return nums[pivot];
    }

    private int partition(int[] nums, int left, int right) {

        int random = new Random().nextInt(right - left + 1) + left;
        // 以最右边元素为基准，将<right放左边，>=放右边
        swap(nums, random, right);

        int p1 = left - 1;
        int p2 = left;

        while (p2 <= right) {
            if (nums[p2] < nums[right]) {
                p1++;
                swap(nums, p1, p2);
            }
            p2++;
        }

        p1++;
        swap(nums, p1, right);
        // 返回>=的第一个元素的坐标=最右元素放回位置的位置
        return p1;
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        // 随机生成[1,10]的坐标
//        for (int i = 0; i < 10; i++) {
//            System.out.println(new Random().nextInt(10 - 1 + 1) + 1);
//        }
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        Solution solution = new Solution();
        System.out.println(solution.findKthLargest(nums, k));
    }
}
