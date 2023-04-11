package com.ssl.note.leetcode.剑指Offer.第三版.第5章_哈希表.q33_异位词分组;

import java.util.*;

/**
 * @author SongShengLin
 * @date 2021/10/6 11:39 上午
 * @description
 */
public class Solution {
    /**
     * 字母异位词分组
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] cs = s.toCharArray();
//            Arrays.sort(cs);
//            String sorted = new String(cs);
            // 如果不让用系统库函数排序，就手写一个
            String sorted = getSortedString(cs);
            map.putIfAbsent(sorted, new LinkedList<>());
            map.get(sorted).add(s);
        }
        return new LinkedList<>(map.values());
    }

    private String getSortedString(char[] cs) {
        int[] map = new int[256];
        for (char c : cs) {
            map[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            if (map[i] != 0) {
                // c是原字母；map[i]是该字母出现的次数
                char c = (char) (i + 'a');
                for (int j = 0; j < map[i]; j++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] strs = {"ddddddddddg", "dgggggggggg"};
        List<List<String>> lists = solution.groupAnagrams(strs);
        System.out.println(lists);
    }

}
