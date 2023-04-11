package com.ssl.note.leetcode.剑指Offer.第三版.第5章_哈希表.q34_外星语言是否排序;

/**
 * @Author: SongShengLin
 * @Date: 2022/10/30 21:21
 * @Describe:
 */
public class Solution1 {


    public boolean isAlienSorted(String[] words, String order) {
        int[] map = new int[order.length()];
        for (int i = 0; i < order.length(); i++) {
            map[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; i++) {
            if (!isSorted(words[i], words[i + 1], map)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSorted(String word1, String word2, int[] map) {
        for (int i = 0; i < word1.length() && i < word2.length(); i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            if (map[c1 - 'a'] < map[c2 - 'a']) {
                return true;
            }
            if (map[c1 - 'a'] > map[c2 - 'a']) {
                return false;
            }
        }
        // 循环结束
        // apple和app,返回false
        // app和apple,发挥true
        return word1.length() <= word2.length();
    }

}
