package com.ssl.note.leetcode.剑指Offer.第三版.第3章_字符串.q15_字符串中的所有变位词;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Solution {

    /**
     * 找到s1中所有s2的异位词
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     */
    public List<Integer> findAnagrams(String s1, String s2) {
        List<Integer> indices = new ArrayList<>();
        if (s1.length() < s2.length()) {
            return indices;
        }
        int[] counts = new int[26];
        for (int i = 0; i < s2.length(); i++) {
            counts[s2.charAt(i) - 'a']++;
            counts[s1.charAt(i) - 'a']--;
        }
        if (areAllZero(counts)) {
            // 记录起始坐标
            indices.add(0);
        }
        for (int i = s2.length(); i < s1.length(); i++) {
            counts[s1.charAt(i) - 'a']--;
            counts[s1.charAt(i - s2.length()) - 'a']++;
            if (areAllZero(counts)) {
                // 异位词起始坐标需要+1
                indices.add(i - s2.length() + 1);
            }
        }
        return indices;
    }

    /**
     * 判断数组元素是否全为0
     *
     * @param counts
     * @return 布尔值
     */
    private boolean areAllZero(int[] counts) {
        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /*************************练习***********************************/
    public List<Integer> findAnagrams1(String s, String p) {
        // s:长。p：短
        if (p == null || s.length() < p.length()) {
            return new ArrayList<>();
        }
        int M = s.length();
        int N = p.length();
        List<Integer> res = new ArrayList<>();
        int[] map = new int[26];
        for (int i = 0; i < N; i++) {
            map[p.charAt(i) - 'a']++;
            map[s.charAt(i) - 'a']--;
        }
        if (checkZero(map)) {
            res.add(0);
        }

        for (int i = N; i < M; i++) {
            map[s.charAt(i) - 'a']--;
            map[s.charAt(i - N) - 'a']++;
            if (checkZero(map)) {
                // s = abac,p=abc
                // 当i来到3位置c的位置时，需要保存1位置b的坐标
                // 3-(p的长度)+1
                res.add(i - N + 1);
            }
        }

        return res;
    }

    private boolean checkZero(int[] count) {
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> res1 = solution.findAnagrams(s, p);
        List<Integer> res2 = solution.findAnagrams1(s, p);
        if (res1 != res2) {
            System.out.println("错");
            System.out.println("res1:" + res1);
            System.out.println("res2:" + res2);
        } else {
            System.out.println("对");
        }
    }

    private boolean checkCollection(List<Integer> res1, List<Integer> res2) {
        if (res1.size() != res2.size()) {
            return false;
        }
        for (int i = 0; i < res1.size(); i++) {
            if (!Objects.equals(res1.get(i), res2.get(i))) {
                return false;
            }
        }
        return true;
    }
}
