package com.ssl.note.leetcode.编号刷题.LC93_复原IP地址;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2023/3/5 10:08
 * @description
 */
public class Solution1 {

    public List<String> restoreIpAddresses(String s) {
        int n = s.length();
        List<String> res = new ArrayList<>();
        Deque<String> deque = new ArrayDeque<>(4);

        if (n > 12 || n < 4) {
            return res;
        }

        dfs(s, n, 0, 4, deque, res);
        return res;
    }

    private void dfs(String s, int len, int begin, int residue, Deque<String> deque, List<String> res) {
        // 1.递归结束条件
        if (begin == len) {
            if (residue == 0) {
                res.add(String.join(".", deque));
            }
            return;
        }
        for (int i = begin; i < begin + 3; i++) {
            if (i >= len) {
                break;
            }
            if (len - i > residue * 3) {
                continue;
            }
            if (checkNum(s, begin, i)) {
                deque.addLast(s.substring(begin, i + 1));

                dfs(s, len, i + 1, residue - 1, deque, res);

                deque.removeLast();
            }

        }
    }

    private boolean checkNum(String s, int begin, int right) {
        int n = right - begin + 1;
        if (n > 1 && s.charAt(begin) == '0') {
            return false;
        }

        int res = 0;
        for (int i = begin; i < right + 1; i++) {
            res = res * 10 + (s.charAt(i) - '0');
        }

        return res >= 0 && res <= 255;
    }
}
