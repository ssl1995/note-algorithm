package com.ssl.note.leetcode.剑指Offer.第三版.第5章_哈希表.q32_有效的变位词;

/**
 * @author SongShengLin
 * @date 2021/10/6 11:17 上午
 * @description
 */
public class Solution {

    /**
     * 有效的变位词
     * 若 s 和 t 中每个字符出现的次数都相同且字符顺序不完全相同，则称 s 和 t 互为变位词（字母异位词）。
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     */
    public boolean isAnagram(String s, String t) {
        // 1.长度不相同，不是变位词
        if (s.length() != t.length()) {
            return false;
        }
        // 2.长度相同，每个位置元素都相同，不是变位词
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                break;
            }
            if (i == s.length() - 1) {
                // LC242:返回true
                return false;
            }
        }
        // 3.长度不相同，每个位置不完全相同，使用map和int[]判断每个字符出现的次数
        // 进阶：如果是Unicode编码集，可以用map
        int[] map = new int[256];
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            if (map[c - 'a'] == 0) {
                return false;
            }
            map[c - 'a']--;
        }
        return true;
    }
}
