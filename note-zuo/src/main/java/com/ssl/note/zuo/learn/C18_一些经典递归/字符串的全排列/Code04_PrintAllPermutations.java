package com.ssl.note.zuo.learn.C18_一些经典递归.字符串的全排列;

import java.util.ArrayList;
import java.util.List;

public class Code04_PrintAllPermutations {

    /**
     * 字符串的全排列
     * 输入：abc
     * 输出：
     * abc
     * acb
     * bac
     * bca
     * cab
     * cba
     * 方法1：记录中间过程
     */
    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        // rest：还剩下多少个集合没有选择
        List<Character> rest = new ArrayList<>();
        for (char cha : str) {
            rest.add(cha);
        }
        String path = "";
        process1(rest, path, ans);
        return ans;
    }

    public static void process1(List<Character> rest, String path, List<String> ans) {
        // base case：rest为空，说明未选择集合已经为空，加入到当前结果集
        if (rest.isEmpty()) {
            ans.add(path);
        } else {
            int N = rest.size();
            // 对于剩下的未选择结果集中，选择要或者不要
            for (int i = 0; i < N; i++) {
                // 每到一个rest未选择结点，我都默认选择要，rest就移除，并且加入到中间结果中
                char cur = rest.get(i);
                rest.remove(i);
                process1(rest, path + cur, ans);
                // 回溯：上一个递归结束后，需要将当前选择的中间值放回rest中，供下一个递归使用
                rest.add(i, cur);
            }
        }
    }

    /**
     * 字符串的全排列
     * 输入：abc
     * 输出：
     * abc
     * acb
     * bac
     * bca
     * cab
     * cba
     * 方法2：利用原cs记录选择中间的过程
     */
    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process2(str, 0, ans);
        return ans;
    }

    public static void process2(char[] str, int index, List<String> ans) {
        // base case：index超过最后一个元素后，代表都选择过了，加入到结果集
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            for (int i = index; i < str.length; i++) {
                swap(str, index, i);
                process2(str, index + 1, ans);
                // 回溯：换回来
                swap(str, index, i);
            }
        }
    }

    /**
     * 字符串的去重全排列
     * 输入：acc
     * 输出：
     * 未去重
     * acc
     * acc
     * cac
     * cca
     * cca
     * cac
     * =======
     * 去重
     * acc
     * cac
     * cca
     * 方法2：利用原cs记录选择中间的过程
     */
    public static List<String> permutation3(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        process3(str, 0, ans);
        return ans;
    }

    public static void process3(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            // 减枝：提前杀死可能性
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if (!visited[str[i]]) {
                    // jiain'zh
                    visited[str[i]] = true;
                    swap(str, index, i);
                    process3(str, index + 1, ans);
                    swap(str, index, i);
                }
            }
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static void main(String[] args) {
        String s = "acc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans2 = permutation2(s);
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans3 = permutation3(s);
        for (String str : ans3) {
            System.out.println(str);
        }

    }

}
