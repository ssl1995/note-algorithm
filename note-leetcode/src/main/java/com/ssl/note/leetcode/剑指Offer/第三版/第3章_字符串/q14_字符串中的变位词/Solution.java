package com.ssl.note.leetcode.剑指Offer.第三版.第3章_字符串.q14_字符串中的变位词;

public class Solution {

    /**
     * 判断一个字符是否是另一个字符的排列
     * 输入：s1 = "ab" s2 = "eidbaooo"
     * 输出：true
     * 解释：s2 包含 s1 的排列之一 ("ba").
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }
        // 因为只有26个小写字母，可以使用数组充当map
        // 数组下标从0-25，分别对应a-z
        int[] counts = new int[26];
        // 只用遍历s1长度
        for (int i = 0; i < s1.length(); i++) {
            // s1++
            counts[s1.charAt(i) - 'a']++;
            // s2--
            counts[s2.charAt(i) - 'a']--;
        }
        // 如果s1、s2的字符都相同、长度一样长
        if (areAllZero(counts)) {
            return true;
        }
        // 指向到这里，说明s2的长度一定>s1的长度
        // ab
        // eidbaoo
        for (int i = s1.length(); i < s2.length(); i++) {
            // 这个逻辑需要重新理解：
            // i相当于右指针=添加的元素，值-1
            counts[s2.charAt(i) - 'a']--;
            // i - s1.length()相当于左指针=删除元素，值+1
            counts[s2.charAt(i - s1.length()) - 'a']++;
            if (areAllZero(counts)) {
                return true;
            }
        }
        return false;
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
    public boolean checkInclusion1(String s1, String s2) {
        if (s2 == null || s1.length() > s2.length()) {
            return false;
        }
        int[] map = new int[256];
        int M = s1.length();
        int N = s2.length();
        for (int i = 0; i < M; i++) {
            map[s1.charAt(i) - 'a']++;
            map[s2.charAt(i) - 'a']--;
        }
        if (checkZero(map)) {
            return true;
        }

        for (int i = M; i < N; i++) {
            map[s2.charAt(i) - 'a']--;
            map[s2.charAt(i - M) - 'a']++;
            if (checkZero(map)) {
                return true;
            }
        }
        return false;
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
        String s1 = "ab";
        String s2 = "eidbaoo";
        Solution solution = new Solution();
        boolean res = solution.checkInclusion(s1, s2);
        boolean res1 = solution.checkInclusion1(s1, s2);
        if (res1 != res) {
            System.out.println("错了！");
        } else {
            System.out.println("对了！结果：" + res);
        }
    }
}
