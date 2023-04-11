package com.ssl.note.leetcode.编号刷题.LC29_两数相除.加减法;

/**
 * @Author: SongShengLin
 * @Date: 2022/09/12 17:02
 * @Describe:
 */
public class Solution {

    // 加法:无进位相加(^)+进位的结果(&<<1)
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;// sum:记录^的结果
            b = (a & b) << 1;// b:进位的结果，进位信息需要左移才能相加
            a = sum;
        }
        return sum;
    }

    // 减法:a-b=a+(-b)
    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    // 获得一个数相反数:取反+1
    public static int negNum(int n) {
        return add(~n, 1);
    }

    // 乘法:左移a,无符号右移b
    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            // b末尾是1,说明要加1个a
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            // a有符号左移一位
            a <<= 1;
            // b无符号右移一位,因为需要将b磨成0,所以是无符号右移缩小
            b >>>= 1;
        }
        return res;
    }

    // 除法
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
        int n = 10;
        System.out.println("n:" + n);
        System.out.println("~n:" + ~n);
        int n1 = n;
        n1 <<= 1;
        System.out.println("<<n:" + n1);
        System.out.println("-n:" + negNum(n));

        int a = 2;
        int b = 3;
        System.out.println(multi(a, b));
    }


}
