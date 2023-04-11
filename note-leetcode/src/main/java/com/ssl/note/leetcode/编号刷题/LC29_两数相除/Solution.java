package com.ssl.note.leetcode.编号刷题.LC29_两数相除;

/**
 * @Author: SongShengLin
 * @Date: 2022/09/12 16:47
 * @Describe:
 */
public class Solution {
    /**
     * 两数相除
     * 输入: dividend = 10, divisor = 3
     * 输出: 3
     * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
     */
    public int divide(int dividend, int divisor) {

        long a = dividend;
        long b = divisor;

        int flag = 1;
        if ((a < 0 && b > 0) || (a > 0 && b < 0)) {
            flag = -1;
        }
        if (a < 0) {
            a = -a;
        }
        if (b < 0) {
            b = -b;
        }

        // 两个数为正，除的结果在[0,a]内
        long left = 0, right = a;
        while (left < right) {
            // 不能直接用/2;因为left = right时，就死循环
//            long mid = (left + right) / 2;
            // (..+1)/2,规避相同的情况
            long mid = (left + right + 1) >> 1;
            // 一个乘法
            if (multi(mid, b) <= a) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        long res = flag == -1 ? -right : right;
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) res;
    }

    private long multi(long a, long b) {
        long res = 0;
        while (b != 0) {
            if (((b & 1) == 1)) {
                res += a;
            }
//            a += a;
            a <<= 1;
            b >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int a = 10;
        int b = 3;
        solution.divide(a, b);
    }


}
