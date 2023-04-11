package com.ssl.note.zuo.learn.C39_对数器和数据量猜解法;

public class Code02_EatGrass {

    /**
     * 给定一个正整数N，表示有N份青草统一堆放在仓库里
     * 有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草
     * 不管是牛还是羊，每一轮能吃的草量必须是：
     * 1，4，16，64…(4的某次方)
     * 谁最先把草吃完，谁获胜
     * 假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定
     * 根据唯一的参数N，返回谁会赢
     */
    public static String winner1(int n) {
        // 如果n份草，最终后手赢，返回"后手"
        // 如果n份草，最终先手赢，返回"先手"
        // 自己找一个base case，比如前4个数
        if (n < 5) {
            return (n == 0 || n == 2) ? "后手" : "先手";
        }
        int base = 1;
        while (base <= n) {
            // 这一轮的先手 = (n-base)轮的后手
            if (winner1(n - base).equals("后手")) {
                return "先手";
            }
            // 防止base*4之后溢出
            if (base > n / 4) {
                break;
            }
            base *= 4;
        }
        return "后手";
    }

    public static String winner2(int n) {
        // 规律就是每5个数：后 先 后 先 先
        if (n % 5 == 0 || n % 5 == 2) {
            return "后手";
        } else {
            return "先手";
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + " : " + winner1(i));
        }
    }

}
