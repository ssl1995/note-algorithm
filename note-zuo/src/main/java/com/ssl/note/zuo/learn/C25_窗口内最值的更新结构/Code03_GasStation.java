package com.ssl.note.zuo.learn.C25_窗口内最值的更新结构;

import java.util.LinkedList;

// 测试链接：https://leetcode.com/problems/gas-station
public class Code03_GasStation {

    /**
     * LC134 加油站
     * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。
     * 如果存在解，则 保证 它是 唯一 的。
     * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
     * 输出: 3
     * 解释:
     * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
     * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
     * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
     * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
     * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
     * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
     * 因此，3 可为起始索引。
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        // 这个方法的时间复杂度O(N)，额外空间复杂度O(N)
        boolean[] good = goodArray(gas, cost);
        for (int i = 0; i < gas.length; i++) {
            if (good[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回每个位置，能否作为起点跑完加油站
     */
    public static boolean[] goodArray(int[] g, int[] c) {
        int N = g.length;
        int M = N << 1;
        int[] arr = new int[M];
        // arr是两倍N长度，后半部分存N长度的累加和
        for (int i = 0; i < N; i++) {
            arr[i] = g[i] - c[i];
            // 亮点：后半部门先存同样最坐标的累计和
            arr[i + N] = g[i] - c[i];
        }
        for (int i = 1; i < M; i++) {
            arr[i] += arr[i - 1];
        }
        // 双端队列队头保存N范围内的最小值
        LinkedList<Integer> w = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[i]) {
                w.pollLast();
            }
            w.addLast(i);
        }
        //
        boolean[] ans = new boolean[N];
        for (int offset = 0, i = 0, j = N; j < M; offset = arr[i++], j++) {
            // 当前窗口的最大值 - 偏移量 >=0。说明是一个有效的起点
            if (arr[w.peekFirst()] - offset >= 0) {
                ans[i] = true;
            }
            // 以下：更新滑动窗口
            // 左半部分：[0,N-1]
            // 队头出队
            if (w.peekFirst() == i) {
                w.pollFirst();
            }
            // 右半部分:[N,M]
            // 队尾出队
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[j]) {
                w.pollLast();
            }
            // 队尾进队
            w.addLast(j);
        }
        return ans;
    }

}
