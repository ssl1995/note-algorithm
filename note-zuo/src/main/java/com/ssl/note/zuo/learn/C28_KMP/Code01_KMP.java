package com.ssl.note.zuo.learn.C28_KMP;

public class Code01_KMP {

    /**
     * kmp：判断s2是否是s1的子串，返回s2在s1中的起始坐标
     */
    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int x = 0;
        int y = 0;
        // 1.求next数组
        // O(M) m <= n
        int[] next = getNextArray(str2);
        // 2.根据next数组快速匹配
        // O(N)
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) { // y == 0
                x++;
            } else {
                y = next[y];
            }
        }
        // 返回s2在s1中的起始坐标
        return y == str2.length ? x - y : -1;
    }

    public static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        // 目前在哪个位置上求next数组的值
        int i = 2;
        // 当前是哪个位置的值再和i-1位置的字符比较
        // cn就是i-1位置的最长前后缀长度 = 要和i-1位置的数比较的位置
        int cn = 0;
        while (i < next.length) {
            if (str2[i - 1] == str2[cn]) { // 配成功的时候
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
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
//        int possibilities = 5;
//        int strSize = 20;
//        int matchSize = 5;
//        int testTimes = 5000000;
//        System.out.println("test begin");
//        for (int i = 0; i < testTimes; i++) {
//            String str = getRandomString(possibilities, strSize);
//            String match = getRandomString(possibilities, matchSize);
//            if (getIndexOf(str, match) != str.indexOf(match)) {
//                System.out.println("Oops!");
//            }
//        }
//        System.out.println("test finish");
        String s1 = "abababab";
        String s2 = "ababab";
        System.out.println(getIndexOf(s1, s2));
    }

    public int kmp (String S, String T) {
        // write code here
        int[] next = getNext(S);
        char[] str =T.toCharArray();
        char[] t = S.toCharArray();
        int si =0;
        int ti =0;

        int ans =0;
        while(si < str.length){
            if(ti==-1 || str[si] == t[ti]){
                si++;
                ti++;
            }else{
                ti = next[ti];
            }
            if(ti == t.length){  //改造2
                ans++;
                ti=next[ti];
            }
        }
        return ans;
    }

    public int[] getNext(String S){
        char[] str= S.toCharArray();
        int[] next=  new int[str.length+1]; //改造1
        next[0]=-1;
        next[1] = 0;
        int cur=2;
        int x = next[cur-1];
        while(cur<=str.length){
            if(x==-1 || str[cur-1]==str[x]){
                next[cur++] = ++x;
            }else{
                x=next[x];
            }
        }
        return next;
    }

}
