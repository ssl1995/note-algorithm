package com.ssl.note.leetcode.编号刷题.LC20_有效的括号;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2022/1/20 11:29 PM
 * @description
 */
public class Solution {

    /**
     * 有效的括号
     * 输入：s = "{[]}"
     * 输出：true
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Deque<Character> stack = new LinkedList<>();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c1 = cs[i];
            if (map.containsKey(c1)) {
                stack.push(c1);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char c2 = stack.pop();
                if (map.get(c2) != c1) {
                    return false;
                }
            }
        }
        // 最后判断是否为空
        return stack.isEmpty();
    }
}
