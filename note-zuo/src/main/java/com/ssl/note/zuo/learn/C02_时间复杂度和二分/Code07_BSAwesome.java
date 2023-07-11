package com.ssl.note.zuo.learn.C02_时间复杂度和二分;

public class Code07_BSAwesome {

    /**
     * 局部最小值问题
     * 数组：任意两个相邻的数字不相等
     * 返回最小值下标
     */
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        // 左边：第一个数比第二个数小
        if (arr[0] < arr[1]) {
            return 0;
        }
        // 右边：倒数第二个数 > 倒数第一个数
        if (arr[arr.length - 2] > arr[arr.length - 1]) {
            return arr.length - 1;
        }
        // 因为单调(相邻两个数不相等)，所以局部最小值问题能二分
        int left = 1;
        int right = arr.length - 2;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            // 和普通的二分条件发生了变化:
            // 打破arr[mid-1] >= arr[mid] && arr[mid] <= arr[mid+1]
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

}
