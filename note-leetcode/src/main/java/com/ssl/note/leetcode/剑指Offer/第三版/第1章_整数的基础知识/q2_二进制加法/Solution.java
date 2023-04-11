package com.ssl.note.leetcode.剑指Offer.第三版.第1章_整数的基础知识.q2_二进制加法;

/**
 * @author SongShengLin
 * @date 2021/9/8
 */
public class Solution {

    /**
     * 两个字符串二进制相加
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int x = i >= 0 ? a.charAt(i--) - '0' : 0;
            int y = j >= 0 ? b.charAt(j--) - '0' : 0;
            int sum = x + y + carry;
            carry = sum >= 2 ? 1 : 0;
            sum = sum >= 2 ? sum - 2 : sum;
            sb.append(sum);
        }
        if (carry == 1) {
            sb.append(1);
        }
        // 返回结果反转
        return sb.reverse().toString();
    }
}
