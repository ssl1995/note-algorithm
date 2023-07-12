package com.ssl.note.zuo.practice;

import java.util.ArrayList;

public class Solution1 {

    public long calArray(ArrayList<Integer> nums) {
        if (nums == null || nums.size() < 2) {
            return 0;
        }
        Integer[] arr = new Integer[nums.size()];
        nums.toArray(arr);
        return mergeSort(arr, 0, nums.size() - 1);
    }

    public long mergeSort(Integer[] arr, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return mergeSort(arr, l, mid) +
                mergeSort(arr, mid + 1, r) +
                merge(arr, l, mid, r);
    }

    public long merge(Integer[] arr, int l, int m, int r) {
        Integer[] help = new Integer[r - l + 1];
        long res = 0l;
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 <= m && p2 <= r) {
            res += arr[p1] <= arr[p2] ? arr[p1] * (r - p2 + 1) : 0;
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; ++i) {
            arr[l + i] = help[i];
        }
        return res;
    }
}
