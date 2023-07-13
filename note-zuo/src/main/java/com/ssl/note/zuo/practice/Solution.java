package com.ssl.note.zuo.practice;

import java.util.ArrayList;

public class Solution {

    public int InversePairs(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]既要排好序，也要求逆序对数量返回
    // 所有merge时，产生的逆序对数量，累加，返回
    // 左 排序 merge并产生逆序对数量
    // 右 排序 merge并产生逆序对数量
    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return (process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r)) % 1000000007;
    }

    public static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;

        int res = 0;
        while (p1 <= M && p2 <= R) {
            // 逆序对
            // [3,4] [1,2]
            res += arr[p1] > arr[p2] ? (M - p1 + 1) : 0;
            help[i++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }

        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res % 1000000007;
    }
}
