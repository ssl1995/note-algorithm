package com.ssl.note.zuo.practice;

import java.util.ArrayList;

public class Solution {

    /**
     * 例如，数组 s = [1, 3, 5, 2, 4, 6] ，
     * 在 s[0] 的左边小于或等于 s[0] 的数的和为 0 ；
     * 在 s[1] 的左边小于或等于 s[1] 的数的和为 1 ；
     * 在 s[2] 的左边小于或等于 s[2] 的数的和为 1+3=4 ；
     * 在 s[3] 的左边小于或等于 s[3] 的数的和为 1 ；
     * 在 s[4] 的左边小于或等于 s[4] 的数的和为 1+3+2=6 ；
     * 在 s[5] 的左边小于或等于 s[5] 的数的和为 1+3+5+2+4=15 。
     * 所以 s 的小和为 0+1+4+1+6+15=27
     */
    public long calArray(ArrayList<Integer> nums) {
        if (nums == null || nums.size() < 2) {
            return 0;
        }
        Integer[] arr = new Integer[nums.size()];
        nums.toArray(arr);
        return process(arr, 0, nums.size() - 1);
    }

    private long process(Integer[] nums, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return process(nums, l, m) + process(nums, m + 1, r) + merge(nums, l, m, r);
    }

    private long merge(Integer[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;

        int p1 = l;
        int p2 = m + 1;

        long res = 0L;
        while (p1 <= m && p2 <= r) {
            // [1, 3, 5], [2, 4, 6]
            res += arr[p1] <= arr[p2] ? (long)(r - p2 + 1) * arr[p1] : 0;

            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= m) {
            help[i++] = arr[p1++];
        }

        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }

        return res;
    }
}
