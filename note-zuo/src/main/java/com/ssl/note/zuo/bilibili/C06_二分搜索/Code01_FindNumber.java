package com.ssl.note.zuo.bilibili.C06_二分搜索;

import java.util.Arrays;


public class Code01_FindNumber {

    /**
     * 有序数组中查找一个num，是否存在
     * 二分搜索法：前提是保证数组有序
     */
    public static boolean exist(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int l = 0, r = arr.length - 1, m = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] == num) {
                return true;
            } else if (arr[m] > num) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }


    // 正确的方法：暴力方法，保证arr有序，才能用这个方法
    public static boolean right(int[] sortedArr, int num) {
        for (int cur : sortedArr) {
            if (cur == num) {
                return true;
            }
        }
        return false;
    }

    // 为了验证
    public static void main(String[] args) {
        int N = 100;
        int V = 1000;
        int testTime = 500000;
        System.out.println("测试开始");
        // 有序数组中是否存在一个数字
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N);
            int[] arr = randomArray(n, V);
            // 数组有序
            Arrays.sort(arr);
            int num = (int) (Math.random() * V);
            // 正确方法 和 二分搜素法
            if (right(arr, num) != exist(arr, num)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }

    // 为了验证
    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }


}