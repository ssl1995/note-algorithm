package com.ssl.note.zuo.learn.C20_暴力递归到动态规划2;

public class Code01_Knapsack {

    /**
     * 背包问题：为了方便，其中没有负数
     * 方法1：自然尝试，暴力递归
     *
     * @param w   重量
     * @param v   价值
     * @param bag 背包重量
     * @return 返回选择背包的能获得最大价值
     */
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        return process(w, v, 0, bag);
    }

    /**
     * 自然尝试
     *
     * @param index 从0-> n-1
     * @param rest  从 0-bag
     */
    public static int process(int[] w, int[] v, int index, int rest) {
        // 背包的容量为0，不能返回0，以为当rest<w[index]时，返回0无法判断是否有效
        if (rest < 0) {
            return -1;
        }
        // 没有货啦，返回0
        if (index >= w.length) {
            return 0;
        }
        // 不要当前货物
        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;
        // 要当前货物
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }

    /**
     * 背包问题：为了方便，其中没有负数
     * 方法2：动态规划
     *
     * @param w   重量
     * @param v   价值
     * @param bag 背包重量
     * @return 返回选择背包的能获得最大价值
     */
    public static int maxValue1(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        // index:0->n，(可以到达n位置，返回-1)
        // rest:1->bag
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(maxValue1(weights, values, bag));
    }

}
