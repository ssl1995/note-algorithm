package com.ssl.note.zuo.learn.C15_贪心算法下;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_IPO {

    /**
     * 每做完一个项目，马上获得收益，可以支持你去做下一个项目。不能并行的做项目
     * 返回获得的最大资金
     *
     * @param K       表示串行的最多做k个项目
     * @param M       初始资金
     * @param profits profits[i]表示i号项目在扣除话费之后还能挣到的钱(利润)
     * @param costs   costs[i]表示i号项目的花费
     * @return 获得的最大资金
     */
    public static int findMaximizedCapital(int K, int M, int[] profits, int[] costs) {
        // 花费：小根堆
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(Comparator.comparingInt(p -> p.c));
//        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
        // 利润：大根堆
//        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>((p1, p2) -> p2.p - p1.p);
        for (int i = 0; i < profits.length; i++) {
            minCostQ.add(new Program(profits[i], costs[i]));
        }
        // 遍历k次
        for (int i = 0; i < K; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= M) {
                maxProfitQ.add(minCostQ.poll());
            }
            // 当前资金无法解锁花费更多的项目，提前返回
            if (maxProfitQ.isEmpty()) {
                return M;
            }
            // 出堆几次就是做了几个项目
            M += maxProfitQ.poll().p;
        }
        return M;
    }

    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }

    }

    public static class MaxProfitComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }

    }

}
