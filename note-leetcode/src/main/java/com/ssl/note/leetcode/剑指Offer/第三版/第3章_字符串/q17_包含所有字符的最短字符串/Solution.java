package com.ssl.note.leetcode.剑指Offer.第三版.第3章_字符串.q17_包含所有字符的最短字符串;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * s中含有t的最短子字符串
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * 解释：最短子字符串 "BANC" 包含了字符串 t 的所有字符 'A'、'B'、'C'
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        // 1.先记录t中所有字符，出现的次数
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 2.count表示出现在t中，但还没有出现在s中子字符串中字符个数
        int count = map.size();
        // 因为需要记录最短子字符串,使用4个指针来保存
        int start = 0, end = 0, minStart = 0, minEnd = 0;
        int minLen = Integer.MAX_VALUE;
        // 循环结束条件：1.还有元素需要遍历。2.遍历指针end遍历来到最后位置，左指针必须来到再次使它+1的位置跳出
        while (end < s.length() || (count == 0 && end == s.length())) {
            // count>0说明还有在t中出现，但s中子字符串没有出现的字符
            if (count > 0) {
                char endChar = s.charAt(end);
                if (map.containsKey(endChar)) {
                    map.put(endChar, map.get(endChar) - 1);
                    if (map.get(endChar) == 0) {// =0说明在t和s中出现次数相同
                        count--;
                    }
                }
                end++;
            } else {// count<=0，说明s子字符串中都出现了t
                // 更新左右指针
                if (end - start < minLen) {
                    // end已经提前+1,所以这里长度不需要+1
                    minLen = end - start;
                    minStart = start;
                    minEnd = end;
                }
                char startChar = s.charAt(start);
                if (map.containsKey(startChar)) {
                    map.put(startChar, map.get(startChar) + 1);
                    // 只有从0->1的字符，表示左指针多减了t中出现的字母，count++
                    if (map.get(startChar) == 1) {
                        count++;
                    }
                }
                start++;
            }
        }
        return minLen < Integer.MAX_VALUE ? s.substring(minStart, minEnd) : "";
    }

    /*************************练习***********************************/
    public String minWindow1(String s, String t) {
        if (t == null || s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int start = 0;
        int end = 0;
        int minStart = 0;
        int minEnd = 0;
        int minLen = Integer.MAX_VALUE;

        int count = map.size();
        while (end < s.length() || (count == 0 && end == s.length())) {
            if (count > 0) {
                char c = s.charAt(end);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) - 1);
                    if (map.get(c) == 0) {
                        count--;
                    }
                }

                end++;
            } else {
                if (end - start < minLen) {
                    minLen = end - start;
                    minStart = start;
                    minEnd = end;
                }
                char c = s.charAt(start);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                    if (map.get(c) == 1) {
                        count++;
                    }
                }
                start++;
            }
        }
        return minLen < Integer.MAX_VALUE ? s.substring(minStart, minEnd) : "";
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "ADOBECODEBANC";
        String t = "BANC";
        System.out.println(solution.minWindow(s, t));
        System.out.println(solution.minWindow1(s, t));
    }
}
