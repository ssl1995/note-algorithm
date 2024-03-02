package com.ssl.note.zuo.bilibili.C06_二分搜索;

public class Code04_FindPeekElement {

    /**
     * LC162：寻找峰值
     * 1. 数组中任意相邻元素都不相同
     * 2. 峰值元素是比左右都大
     * 3. 数组两边认为无穷小
     * 测试链接 : https://leetcode.cn/problems/find-peak-element/
     */
    public int findPeakElement(int[] nums) {
        if (nums == null) {
            return -1;
        }
        // 数组有1个值
        if (nums.length == 1) {
            return 0;
        }
        // 数组>=2个值，从左右开始比较
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }
        // 如果左右两值都不是峰值，说明中间的值“必有”峰值 = 某测必有峰值，就可以二分
        int l = 1;
        int r = nums.length - 2;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // 峰值两边的元素，都小
            if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            // 否则，必定有1边有峰值：那边是连续的，另一边才会有峰值
            if (nums[mid - 1] < nums[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        Code04_FindPeekElement peekElement = new Code04_FindPeekElement();
        int res = peekElement.findPeakElement(nums);
        System.out.println(res);
    }
}
