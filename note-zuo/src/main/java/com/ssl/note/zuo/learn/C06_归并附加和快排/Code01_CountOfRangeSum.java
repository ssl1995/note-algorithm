package com.ssl.note.zuo.learn.C06_归并附加和快排;

// 这道题直接在leetcode测评：
// https://leetcode.com/problems/count-of-range-sum/
// https://leetcode.cn/problems/count-of-range-sum/
public class Code01_CountOfRangeSum {

    /**
     * LC327 区间数的个数
     * 给你一个整数数组nums 以及两个整数lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含lower和upper）之内的 区间和的个数 。
     * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
     * 输入：nums = [-2,5,-1], lower = -2, upper = 2
     * 输出：3
     * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
     */
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 为什么想到用前缀和数组：便于获取arr[i,j]的累加和
        // 1.如果每次都是暴力累加arr[i,j]位置的和，太慢了
        // 2.可以提前生成前缀和数组，sum[i]表示arr[0,i]的累加和
        // 3.求arr[i,j]的累加和=sum[j]-sum[i-1]
        // nums= [-2,5,-1]
        // sum = [-2,3,2]
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public static int process(long[] sum, int L, int R, int lower, int upper) {
        if (L == R) {
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }
        int M = L + ((R - L) >> 1);
        return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper) + merge(sum, L, M, R, lower, upper);
    }

    public static int merge(long[] sum, int L, int M, int R, int lower, int upper) {
        int ans = 0;
        int windowL = L;
        int windowR = L;
        // sum =  [-2,3,2], lower = -2, upper = 2
        // sum[i]= 2, [min,max] = [-4,0]
        for (int i = M + 1; i <= R; i++) {
            // 核心思路：已知sum[i]和[lower,upper]
            // = 以arr[i]结尾的所有子数组中，有多少个子数组的累加和在[sum[i]-upper,sum[i]-lower]范围上
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            // 因为sum[i]在sum[M+1,R]中是递增有序，-upper和-lower也是递增有序
            // 推出wL和wR也是递增的 = 窗口不回退，降低了时间复杂度
            while (windowR <= M && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= M && sum[windowL] < min) {
                windowL++;
            }
            ans += Math.max(windowR - windowL, 0);
        }

        // 归并排序
        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= M) {
            help[i++] = sum[p1++];
        }
        while (p2 <= R) {
            help[i++] = sum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            sum[L + i] = help[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] num = {-2, 5, -1};
        int i = countRangeSum(num, -2, 2);
        System.out.println(i);
    }

}
