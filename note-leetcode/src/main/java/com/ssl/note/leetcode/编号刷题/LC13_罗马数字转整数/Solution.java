package com.ssl.note.leetcode.编号刷题.LC13_罗马数字转整数;

/**
 * @Author: SongShengLin
 * @Date: 2022/09/06 10:22
 * @Describe:
 */
public class Solution {
    /**
     * 罗马数字转整数
     * 输入: s = "LVIII"
     * 输出: 58
     * 解释: L = 50, V= 5, III = 3.
     */
    public int romanToInt(String s) {
        if (s == null || s.trim().length() == 0) {
            return 0;
        }
        char[] cs = s.trim().toCharArray();

        int sum = 0;
        // 罗马数字记录pre、next
        int pre = getValue(cs[0]);
        for (int i = 1; i < cs.length; i++) {
            int next = getValue(cs[i]);
            if (pre < next) {
                sum -= pre;
            } else {
                sum += pre;
            }
            pre = next;
        }
        // pre最后会迭代到最后一个数
        sum += pre;
        return sum;
    }


    private int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }


}
