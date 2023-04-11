package com.ssl.note.leetcode.编号刷题.LC796_旋转字符串;

/**
 * @Author: SongShengLin
 * @Date: 2022/10/21 16:15
 * @Describe:
 */
public class Solution {

    /**
     * LC796:判断s是否是goal的旋转串
     */
    public boolean rotateString(String s, String goal) {
        // 长度不相同
        if (s == null || goal == null || s.length() != goal.length()) {
            return false;
        }
        // 拼接两次b = 自己拼接自己，任何长度为自己的子串都是b2的子串
        String b2 = goal + goal;
        // 使用kmp算法判断a是否是b2的子串
        return getIndexOf(b2, s) != -1;
    }

    // KMP Algorithm
    public static int getIndexOf(String s, String m) {
        if (s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }
}
