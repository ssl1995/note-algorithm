package com.ssl.note.zuo.learn.C02_时间复杂度和二分;

public class Code07_BSAwesome {

    /**
     * 局部最小值问题
     * 数组：任意两个相邻的数字不相等
     */
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1; // no exist
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        // 因为单调(相邻两个数不相等)，所以局部最小值问题能二分
        while (left < right) {
            mid = (left + right) / 2;

            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                // arr[mid-1] >= arr[mid]
                // arr[mid] <= arr[mid+1]
                // 两个等于都满足，就找到了
                return mid;
            }
        }
        return left;
    }

}
