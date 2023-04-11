package com.ssl.note.leetcode.剑指Offer.第三版.第6章_栈.q37_小行星碰撞;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author SongShengLin
 * @date 2021/10/7 9:11 上午
 * @description
 */
public class Solution {

    /**
     * 小行星碰撞
     * 输入：asteroids = [4,5,-6,4,8,-5]
     * 输出：[-6,4,8]
     */
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new LinkedList<>();
        for (int num : asteroids) {
            // 一直出栈：栈非空 且 栈顶>0 且 栈顶<n的绝对值(n此时必为负数，绝对值为-n)
            while (!stack.isEmpty() && stack.peek() > 0 && num < 0 && stack.peek() < -num) {
                stack.pop();
            }
            // 相同，一起爆炸，也要出栈
            if (!stack.isEmpty() && stack.peek() > 0 && num < 0 && stack.peek() == -num) {
                stack.pop();
            } else if (stack.isEmpty() || stack.peek() < 0 || num > 0) {
                // 上述表达式不能用else省略
                stack.push(num);
            }
        }
        // 保证相对次序不变
        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] asteroids0 = {5, 10, -5};
        int[] asteroids1 = {5, 10, -5};
        System.out.println(Arrays.toString(solution.asteroidCollision(asteroids0)));
//        System.out.println(Arrays.toString(solution.asteroidCollision1(asteroids1)));
    }
}
