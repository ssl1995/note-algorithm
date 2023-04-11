package com.ssl.note.leetcode.编号刷题.LC17_电话号码组合;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2022/1/20 9:10 AM
 * @description
 */
public class Solution {
    /**
     * 电话号码组合
     * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     */
    public List<String> letterCombinations(String digits) {
        if (digits.length() < 1) {
            return new ArrayList<>();
        }
        // 题目：只给2-9的数字键盘，map只用存储2-9数字对应的字母表
        Map<Character, String> map = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        List<String> res = new ArrayList<>();
        // 回溯
        backtrack(map, digits, 0, new StringBuilder(), res);
        return res;
    }

    /**
     * 递归和回溯的基本题
     * 获得数字电话号码组合
     *
     * @param map    数字对应字母的map
     * @param digits 传过来的参数：数字 "23"
     * @param index  遍历数字的坐标：遍历"23"的坐标
     * @param sb     临时字符串，保存当前组成的字母组合
     * @param res    返回电话号码形成的所有组合
     */
    private void backtrack(Map<Character, String> map, String digits, int index, StringBuilder sb, List<String> res) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        char number = digits.charAt(index);
        String letters = map.get(number);
        // 递归
        for (int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i));
            backtrack(map, digits, index + 1, sb, res);
            // 回溯：sb删除这一轮的数字对应的所有字母，index刚好对应
            // 注意，这里不是i
            sb.deleteCharAt(index);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String digits = "23";
        System.out.println(solution.letterCombinations(digits));
        System.out.println("---");
        System.out.println("".length());
    }

}
