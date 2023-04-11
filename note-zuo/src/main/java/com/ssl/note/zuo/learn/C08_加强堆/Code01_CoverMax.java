package com.ssl.note.zuo.learn.C08_加强堆;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Code01_CoverMax {

    /**
     * 最大线段重合问题：
     * 给定很多线段，每个线段都有两个数[start,end]
     * 规定：1.线段开始和结束位置一定是整数值，2.线段重合区域的长度>=1
     * 返回：线段最多重合区域中，包含了几条线段？
     */
    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // 1.找出所有线段中，开始时间和结束时间的最大值
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        // 2.以0.5位区间长度，区间长度>=1一定包含它
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            // 3.0.5的端点，在每个线段中出现的次数，取最大值
            // = 线段最多重合区域中，包含的最多线段
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        // 时间复杂度:O((max-min)*N),如果数据量<10^8就可以笔试过
        return cover;
    }

    /**
     * 方法2和方法3：利用堆
     */
    public static int maxCover2(int[][] m) {
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        Arrays.sort(m, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(lines, new StartComparator());
        // 小根堆，每一条线段的结尾数值，使用默认的
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            // lines[i] -> cur  在黑盒中，把<=cur.start 东西都弹出
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i].end);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    public static int maxCover3(int[][] m) {
        if (m == null) {
            return 0;
        }
        Arrays.sort(m, Comparator.comparing(a -> a[0]));

        Queue<Integer> minHeap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < m.length; i++) {
            int start = m[i][0];
            int end = m[i][1];
            // start <= 堆顶 说明有重复，保留
            // start >= 堆顶 说明无重复，出堆
            while (!minHeap.isEmpty() && minHeap.peek() <= start) {
                minHeap.poll();
            }
            minHeap.offer(end);
            max = Math.max(max, minHeap.size());
        }
        return max;
    }

    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static class EndComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }

    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static class StartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }

    }

    public static void main(String[] args) {

        Line l1 = new Line(4, 9);
        Line l2 = new Line(1, 4);
        Line l3 = new Line(7, 15);
        Line l4 = new Line(2, 4);
        Line l5 = new Line(4, 6);
        Line l6 = new Line(3, 7);

        // 底层堆结构，heap
        PriorityQueue<Line> heap = new PriorityQueue<>(new StartComparator());
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);

        while (!heap.isEmpty()) {
            Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            int ans3 = maxCover3(lines);
            if (ans1 != ans2) {
                System.out.println("Oops1!");
            }
            if (ans2 != ans3) {
                System.out.println("Oops2!");
            }
        }
        System.out.println("test end");
    }

}
