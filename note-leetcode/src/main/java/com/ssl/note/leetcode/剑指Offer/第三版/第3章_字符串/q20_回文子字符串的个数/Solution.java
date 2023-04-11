package com.ssl.note.leetcode.剑指Offer.第三版.第3章_字符串.q20_回文子字符串的个数;

public class Solution {
    /**
     * 回文字符串的个数
     * 输入：s = "abc"
     * 输出：3
     * 解释：三个回文子串: "a", "b", "c"
     */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        // 遍历一次，统计以每个位置往外扩，能构成的回文字符串个数
        for (int i = 0; i < s.length(); i++) {
            // 奇数回文中心，i，i
            count += countPalindrome(s, i, i);
            // 偶数会问中心，i，i+1
            count += countPalindrome(s, i, i + 1);
        }
        return count;
    }

    private int countPalindrome(String s, int start, int end) {
        int count = 0;
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            count++;
            // 回文中心往外扩
            start--;
            end++;
        }
        return count;
    }

    /*************************练习***********************************/
    public int countSubstrings1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += getPCount(s, i, i);
            count += getPCount(s, i, i+1);
        }
        return count;
    }


    private int getPCount(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                count++;
            } else {
                break;
            }
        }

        return count;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abc";
        System.out.println("solution.countSubstrings(s) = " + solution.countSubstrings(s));
        System.out.println("solution.countSubstrings1(s) = " + solution.countSubstrings1(s));
    }
}
