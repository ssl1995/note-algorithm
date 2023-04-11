package com.ssl.note.leetcode.剑指Offer.第三版.第5章_哈希表.q34_外星语言是否排序;

/**
 * @author SongShengLin
 * @date 2021/10/6 12:02 下午
 * @description
 */
public class Solution {
    /**
     * 外星语排序
     * 输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
     * 输出：true
     * 解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
     * 输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
     * 输出：false
     * 解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
     */
    public boolean isAlienSorted(String[] words, String order) {
        // 1.建立外星语言的排序表
        int[] orderArr = new int[order.length()];
        for (int i = 0; i < order.length(); i++) {
            orderArr[order.charAt(i) - 'a'] = i;
        }
        // 2.判断是否是否符合排序规则
        for (int i = 0; i < words.length - 1; i++) {
            if (!isSorted(words[i], words[i + 1], orderArr)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断word1是否按照orderArr规则，排在word2前面
     */
    private boolean isSorted(String word1, String word2, int[] orderArr) {
        for (int i = 0; i < word1.length() && i < word2.length(); i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            // 排序表小的排在前面,符合排序要求
            if (orderArr[c1 - 'a'] < orderArr[c2 - 'a']) {
                return true;
            }
            // 否则，排序失败
            if (orderArr[c1 - 'a'] > orderArr[c2 - 'a']) {
                return false;
            }
        }
        // 循环结束
        // apple和app,返回false
        // app和apple,发挥true
        return word1.length() <= word2.length();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = {"hello", "leetcode"};
        String orders = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println(solution.isAlienSorted(words, orders));
        String[] words1 = {"app", "appg"};
        String orders1 = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(solution.isAlienSorted(words1, orders1));
    }
}
