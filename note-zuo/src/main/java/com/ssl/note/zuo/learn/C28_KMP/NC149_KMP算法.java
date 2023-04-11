package com.ssl.note.zuo.learn.C28_KMP;

/**
 * @Author: SongShengLin
 * @Date: 2022/10/20 08:57
 * @Describe:
 */
public class NC149_KMP算法 {
    /**
     * 给你一个文本串 T ，一个非空模板串 S ，问 S 在 T 中出现了多少次
     * 输入：
     * "ababab","abababab"
     * 返回值：
     * 2
     */
    public static int kmp(String S, String T) {
        if (S == null || T == null || T.length() == 0 || T.length() < S.length()) {
            return 0;
        }
        int[] next = getNext(S);
        char[] s1 = T.toCharArray();
        char[] s2 = S.toCharArray();
        int x = 0;
        int y = 0;

        int ans = 0;
        while (x < s1.length && y < s2.length) {
            if (s1[x] == s2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
                y++;
            } else {
                y = next[y];
            }
            // 改造2
            if (y == s2.length) {
                ans++;
                y = next[y];
            }
        }
        return ans;
    }

    public static int[] getNext(String S) {
        if (S.length() == 1) {
            return new int[]{-1};
        }
        char[] str = S.toCharArray();
        // 改造1:next数组多加一位，保存整个S字符串的前后缀最大匹配长度
        int[] next = new int[str.length + 1];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i <= str.length) {
            if (str[i - 1] == str[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String s1 = "abababab";
        String s2 = "ababab";// next:-1 0 0 1 2 3 4
        System.out.println(kmp(s2, s1));
    }
}
