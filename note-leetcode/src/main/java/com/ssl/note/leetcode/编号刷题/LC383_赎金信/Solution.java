package com.ssl.note.leetcode.编号刷题.LC383_赎金信;


import java.util.HashMap;
import java.util.Map;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/09 20:12
 * @Describe:
 */
public class Solution {

    /**
     * 赎金信
     * 输入：ransomNote = "a", magazine = "b"
     * 输出：false
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        // magazine中的元素每次只能用1次
        // 并且只有小写的字母
        int[] map = new int[26];
        for (char c : magazine.toCharArray()) {
            map[c - 'a']++;
        }

        for (char c : ransomNote.toCharArray()) {
            map[c - 'a']--;
            if (map[c - 'a'] < 0) {
                return false;
            }
        }

        return true;

    }


    /**
     * map方法
     */
    public boolean canConstruct1(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();

        for (char c : magazine.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (char c : ransomNote.toCharArray()) {
            if (map.containsKey(c) && map.get(c) > 0) {
                map.put(c, map.get(c) - 1);
            } else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s1 = "aa";
        String s2 = "ab";
        Solution solution = new Solution();
        boolean b = solution.canConstruct1(s1, s2);
        System.out.println(b);
    }
}
