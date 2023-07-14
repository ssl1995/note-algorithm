package com.ssl.note.zuo.practice;

import java.util.ArrayList;

public class Solution {

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = nums[i] + sum[i - 1];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    private int process(long[] sum, int L, int R, int lower, int upper) {
        if (L == R) {
            return sum[L] >= lower && sum[R] <= upper ? 1 : 0;
        }
        int M = L + ((R - L) >> 1);
        return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper) + merge(sum, L, M, R, lower, upper);
    }

    private int merge(long[] sum, int L, int M, int R, int lower, int upper) {
        int res = 0;
        int windowL = L;
        int windowR = L;
        for (int i = M + 1; i <= R; i++) {
            long min = sum[i] - upper;
            long max = sum[i] - lower;

            while (windowR <= M && sum[windowR] <= max) {
                windowR++;
            }

            while (windowL <= M && sum[windowL] < min) {
                windowL++;
            }

            res += Math.max(0, windowR - windowL);
        }

        long[] help = new long[R - L + 1];

        int p1 = L;
        int p2 = M + 1;
        int i = 0;
        while (p1 <= M && p2 <= R) {
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }

        while (p1 <= M) {
            help[i++] = sum[p1++];
        }

        while (p2 <= R) {
            help[i++] = sum[p2++];
        }

        System.arraycopy(help, 0, sum, L, help.length);
//        for (i = 0; i < help.length; i++) {
//            sum[L + i] = help[i];
//        }
        return res;
    }


    public static void main(String[] args) {
        int[] num = {-2, 5, -1};
        Solution solution = new Solution();
        int i = solution.countRangeSum(num, -2, 2);
        System.out.println(i);
    }
}
