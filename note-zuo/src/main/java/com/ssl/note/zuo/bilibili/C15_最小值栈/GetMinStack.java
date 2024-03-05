package com.ssl.note.zuo.bilibili.C15_最小值栈;

import java.util.LinkedList;

// 最小栈
// 测试链接 : https://leetcode.cn/problems/min-stack/
public class GetMinStack {

    /**
     * LC155 最小值栈
     * https://leetcode.cn/problems/min-stack/
     */
    class MinStack {

        private LinkedList<Integer> dataStack;
        private LinkedList<Integer> minStack;


        public MinStack() {
            dataStack = new LinkedList<>();
            minStack = new LinkedList<>();
        }

        public void push(int val) {
            if (minStack.isEmpty()) {
                minStack.push(val);
                dataStack.push(val);
                return;
            }
            // 最小值栈之前的最小值：minStack.peek()
            if (minStack.peek() <= val) {
                minStack.push(minStack.peek());
            } else {
                minStack.push(val);
            }

            dataStack.push(val);
        }

        public void pop() {
            dataStack.pop();
            minStack.pop();
        }

        public int top() {
            if (dataStack.isEmpty()) {
                return -1;
            }
            return dataStack.peek();
        }

        public int getMin() {
            if (minStack.isEmpty()) {
                return -1;
            }
            return minStack.peek();
        }
    }

    // 提交时把类名、构造方法改成MinStack
    class MinStack2 {

        // leetcode的数据在测试时，同时在栈里的数据不超过这个值
        // 这是几次提交实验出来的，哈哈
        // 如果leetcode补测试数据了，超过这个量导致出错，就调大
        public final int MAXN = 8001;

        public int[] data;
        public int[] min;
        int size;

        public MinStack2() {
            data = new int[MAXN];
            min = new int[MAXN];
            size = 0;
        }

        public void push(int val) {
            data[size] = val;
            if (size == 0 || val <= min[size - 1]) {
                min[size] = val;
            } else {
                min[size] = min[size - 1];
            }
            size++;
        }

        public void pop() {
            size--;
        }

        public int top() {
            return data[size - 1];
        }

        public int getMin() {
            return min[size - 1];
        }
    }

}
