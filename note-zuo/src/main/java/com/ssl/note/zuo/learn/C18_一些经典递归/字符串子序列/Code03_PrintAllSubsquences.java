package com.ssl.note.zuo.learn.C18_一些经典递归.字符串子序列;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code03_PrintAllSubsquences {

    /**
     * 字符串的全部子序列
     * 子序列：不需要连续，联想一个二叉树要or不要
     * 输入：s=abc
     * 输出：
     * c
     * b
     * a
     * bc
     * ac
     * ab
     * abc
     */
    public static List<String> subs(String s) {
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(str, 0, ans, path);
        return ans;
    }

    // str 固定参数
    // 来到了str[index]字符，index是位置
    // str[0..index-1]已经走过了！之前的决定，都在path上
    // 之前的决定已经不能改变了，就是path
    // str[index....]还能决定，之前已经确定，而后面还能自由选择的话，
    // 把所有生成的子序列，放入到ans里去
    public static void process1(char[] str, int index, List<String> ans, String path) {
        // base case：结束时，当前递归经历的中间过程path
        // 联想一个二叉树，叶子节点经过的所有结点 = path，放进结果集ans中
        if (index == str.length) {
            ans.add(path);
            return;
        }
        // 没有要index位置的字符
        process1(str, index + 1, ans, path);
        // 要了index位置的字符
        process1(str, index + 1, ans, path + str[index]);
    }

    /**
     * 字符串全部去重子序列
     */
    public static List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process2(str, 0, set, path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }

    public static void process2(char[] str, int index, HashSet<String> set, String path) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        process2(str, index + 1, set, path);
        process2(str, index + 1, set, path + str[index]);
    }

    public static void main(String[] args) {
        String test = "red";
        List<String> ans1 = subs(test);
        List<String> ans2 = subsNoRepeat(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");

    }

}
