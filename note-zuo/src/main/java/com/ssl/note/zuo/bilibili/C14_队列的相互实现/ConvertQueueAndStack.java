package com.ssl.note.zuo.bilibili.C14_队列的相互实现;


import java.util.LinkedList;
import java.util.Queue;


public class ConvertQueueAndStack {

    /**
     * LC232实现队列
     * https://leetcode.cn/problems/implement-queue-using-stacks/
     */
    class MyQueue {

        public LinkedList<Integer> in;
        public LinkedList<Integer> out;

        public MyQueue() {
            in = new LinkedList<>();
            out = new LinkedList<>();
        }

        // 倒数据
        // 从in栈，把数据倒入out栈
        // 1) out空了，才能倒数据
        // 2) 如果倒数据，in必须倒完
        // 时间复杂度：O(1)，均摊时间复杂度，每个数只出现4次
        private void inToOut() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
        }

        public void push(int x) {
            in.push(x);
            inToOut();
        }

        public int pop() {
            inToOut();
            return out.pop();
        }

        public int peek() {
            inToOut();
            return out.peek();
        }

        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }

    }

    /**
     * LC225 用队列实现栈
     * https://leetcode.cn/problems/implement-stack-using-queues/description/
     */
    class MyStack {
        private Queue<Integer> queue;

        public MyStack() {
            queue = new LinkedList<>();
        }

        // 时间复杂度：O(n)，因为每进来一个数，都要循环加前面的数
        public void push(int x) {
            if (queue.isEmpty()) {
                queue.offer(x);
                return;
            }
            // 记录存队列的个数，然后重复放入队列中，保证栈特性
            int size = queue.size();
            queue.offer(x);
            while (size != 0) {
                queue.offer(queue.poll());
                size--;
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
