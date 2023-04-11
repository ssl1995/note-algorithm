package com.ssl.note.zuo.learn.C04_基础的数据结构;

public class Code04_RingArray {

    // 循环数组实现队列
    public static class MyQueue {
        private int[] arr;
        private int begin;// begin，拿数的位置
        private int end;// end，放数的位置
        private int size; // size和limit控制追赶
        private final int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            end = 0;
            begin = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了，不能再加了");
            }
            size++;
            arr[end] = value;
            end = nextIndex(end);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("队列空了，不能再拿了");
            }
            size--;
            int ans = arr[begin];
            begin = nextIndex(begin);
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 如果现在的下标是i，返回下一个位置
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }

    }

}
