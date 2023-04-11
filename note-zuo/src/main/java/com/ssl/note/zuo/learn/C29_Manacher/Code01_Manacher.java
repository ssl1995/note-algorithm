package com.ssl.note.zuo.learn.C29_Manacher;

public class Code01_Manacher {

    /**
     * 最长回文子串长度
     * manacher算法
     */
    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // "12132" -> "#1#2#1#3#2#"
        char[] str = manacherString(s);
        // 回文半径数组
        int[] pArr = new int[str.length];
        // 取得最右回文右边界的中心位置，初始化-1
        int C = -1;
        // 讲述中：R代表最右的扩成功的位置
        // coding：最右的扩成功位置的，再下一个位置
        int R = -1;
        // 返回最长回文子串长度
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            // i在R外:1
            // i在R内:i'=2 * C - i，至少不需要验证的区域就是Math.min(pArr[2 * C - i], R - i)
            pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;
            // i加减pArr[i]至少不用验证的区域，还不越界，就再判断回文半径有没有增加
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            // 更新最右回文右边界
            // 更新最右回文右边界中心位置
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            // 121
            // #1#2#1# max=4,返回3
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        // 转换为带#号的数组后，长度为2n+1
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            // 偶数位置放#
            // 奇数位置放原字符
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
