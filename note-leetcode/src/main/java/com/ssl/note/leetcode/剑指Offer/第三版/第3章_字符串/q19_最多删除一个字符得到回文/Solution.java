package com.ssl.note.leetcode.剑指Offer.第三版.第3章_字符串.q19_最多删除一个字符得到回文;

public class Solution {

    /**
     * 验证回文串II
     * s只由小写字母组成
     */
    public boolean validPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        // 1.s中只包含小写，如果整体上是回文串，就遍历到中间位置即可
        for (; start < s.length() / 2; start++, end--) {
            if (s.charAt(start) != s.charAt(end)) {
                break;
            }
        }
        // 1.s整体就是一个回文
        // aba，start和end都来到len/2的位置
        // abba,start=2,end=1,start来到len/2的位置
        return start == s.length() / 2
                // 2.如果start没有来到len/2位置，
                // 那么说明start和end位置不相同，分别删除这2个字符判断是否还是回文
                || isValid(s, start + 1, end)
                || isValid(s, start, end - 1);
    }

    private boolean isValid(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abca";
        System.out.println(solution.validPalindrome(s));
    }
}
