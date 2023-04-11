package com.ssl.note.leetcode.编号刷题.LC9_回文数;

/**
 * @Author: SongShengLin
 * @Date: 2022/09/05 12:29
 * @Describe:
 */
public class Solution {

    /**
     * 回文数
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * 例如，121 是回文，而 123 不是。
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int oldNum = x;
        int newNum = 0;
        while (x != 0) {
            int temp = x % 10;
            newNum = newNum * 10 + temp;
            x /= 10;
        }
        // 如果最大整数溢出，也没事
        // 因为int最大的回文数是小于最大整型的，及时溢出了==也是false
        return oldNum == newNum;
    }
}
