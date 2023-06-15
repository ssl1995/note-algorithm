package com.ssl.note.zuo.learn.C04_基础的数据结构;

public class Code08_GetMax {


    /**
     * 学习递归:求arr中的最大值
     * 递归时间复杂度：master公式
     * 形如
     * T(N) = a * T(N/b) + O(N^d)(其中的a、b、d都是常数)
     * 递归函数，可以直接通过Master公式来确定时间复杂度
     * 如果 log(b,a) < d，复杂度为O(N^d)
     * 如果 log(b,a) > d，复杂度为O(N^log(b,a))
     * 如果 log(b,a) == d，复杂度为O(N^d  * logN)
     */
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]范围上求最大值  L ... R   N
    public static int process(int[] arr, int L, int R) {
        // arr[L..R]范围上只有一个数，直接返回，base case
        if (L == R) {
            return arr[L];
        }
        // L...R 不只一个数
        // mid = (L + R) / 2
        int mid = L + ((R - L) >> 1); // 中点   	1
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

    private static int getMax1(int[] arr) {
        return process1(arr, 0, arr.length - 1);
    }

    private static int process1(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int mid = l + (r - l) / 2;
        int leftMax = process1(arr, l, mid);
        int rightMax = process1(arr, mid + 1, r);
        return Math.max(leftMax, rightMax);
    }

}
