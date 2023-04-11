package com.ssl.note.leetcode.剑指Offer.第三版.第6章_栈.q36_后缀表达式;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author SongShengLin
 * @date 2021/10/6 8:40 下午
 * @description
 */
public class Solution {

    /**
     * 后缀表达值求值
     * 输入：tokens = ["4","13","5","/","+"]
     * 输出：6
     * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            if ("+-*/".contains(token)) {
                Integer b = stack.pop();
                Integer a = stack.pop();
                stack.push(cal(a, b, token));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.isEmpty() ? 0 : stack.peek();
    }

    private Integer cal(Integer a, Integer b, String op) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }
        return 0;
    }
}
