package com.ssl.note.leetcode.编号刷题.LC7_整数反转;

/**
 * @Author: SongShengLin
 * @Date: 2022/09/05 11:59
 * @Describe:
 */
public class Solution {

    /**
     * 整数反转
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     * 假设环境不允许存储 64 位整数（有符号或无符号）
     * 背Java的32位(int类型范围)：-2,147,483,648至+2,147,483,647
     */
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        int res = 0;
        while (x != 0) {
            // 取模就能得到末尾数字
            int temp = x % 10;
            // 判断溢出
            // 整数末尾是7
            if ((res == Integer.MAX_VALUE / 10 && temp > Integer.MAX_VALUE % 10)
                    || res > Integer.MAX_VALUE / 10) {
                return 0;
            }
            // 负数最大值末尾是-8，需要取整
            if ((res == Integer.MIN_VALUE / 10 && temp > Math.abs(Integer.MIN_VALUE % 10))
                    || res < Integer.MIN_VALUE / 10) {
                return 0;
            }
            res = res * 10 + temp;
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE % 10);
        System.out.println(Integer.MIN_VALUE % 10);
    }
}
