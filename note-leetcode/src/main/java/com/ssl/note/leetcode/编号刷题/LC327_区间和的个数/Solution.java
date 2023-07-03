package com.ssl.note.leetcode.编号刷题.LC327_区间和的个数;

public class Solution {
    /**
     * LC327 区间和的个数
     * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
     * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
     * 输入：nums = [-2,5,-1], lower = -2, upper = 2
     * 输出：3
     * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null) {
            return 0;
        }
        long[] preSum = getPreSum(nums);
        return process(preSum, 0, preSum.length - 1, lower, upper);
    }


    private int process(long[] pre, int l, int r, int lower, int upper) {
        if (l == r) {
            return pre[l] >= lower && pre[l] <= upper ? 1 : 0;
        }
        int mid = l + (r - l) / 2;
        return process(pre, l, mid, lower, upper) + process(pre, mid + 1, r, lower, upper)
                + merge(pre, l, mid, r, lower, upper);
    }

    private int merge(long[] pre, int L, int M, int R, long lower, long upper) {
        int res = 0;
        int windowL = L;
        int windowR = L;

        for (int i = M + 1; i <= R; i++) {
            long min = pre[i] - upper;
            long max = pre[i] - lower;

            while (pre[windowR] <= max && windowR <= M) {
                windowR++;
            }
            while (pre[windowL] < min && windowL <= M) {
                windowL++;
            }

            res += Math.max(0, windowR - windowL);
        }

        long[] help = new long[R - L + 1];
        int index = 0;

        int p1 = L;
        int p2 = M + 1;

        while (p1 <= M && p2 <= R) {
            help[index++] = pre[p1] <= pre[p2] ? pre[p1++] : pre[p2++];
        }

        while (p1 <= M) {
            help[index++] = pre[p1++];
        }

        while (p2 <= R) {
            help[index++] = pre[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            pre[i + L] = help[i];
        }

        return res;
    }

    private long[] getPreSum(int[] nums) {
        long[] pre = new long[nums.length];
        pre[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pre[i] = pre[i - 1] + nums[i];
        }
        return pre;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 5, -1};
        Solution solution = new Solution();
        int i = solution.countRangeSum(nums, -2, 2);
        System.out.println(i);
    }


}
