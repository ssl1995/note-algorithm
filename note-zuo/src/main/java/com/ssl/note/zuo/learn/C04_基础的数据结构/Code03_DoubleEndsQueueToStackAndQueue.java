package com.ssl.note.zuo.learn.C04_基础的数据结构;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code03_DoubleEndsQueueToStackAndQueue {

    /**
     * 栈和队列常见题
     * 1.用栈实现队列：LC232_用栈实现队列;自定义双向队列来实现这两个题
     * 2.用队列实现栈：LC225_队列实现栈;自定义双向队列来实现这两个题
     */

    // LC232：栈实现队列
    static class MyQueue1 {

        private LinkedList<Integer> stack1;
        private LinkedList<Integer> stack2;


        public MyQueue1() {
            stack1 = new LinkedList<>();
            stack2 = new LinkedList<>();
        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            if (empty()) {
                return -1;
            }
            if (stack2.isEmpty()) {
                pushToStack2();
            }
            return stack2.pop();
        }

        public int peek() {
            if (empty()) {
                return -1;
            }
            if (stack2.isEmpty()) {
                pushToStack2();
            }
            return stack2.peek();
        }

        public boolean empty() {
            return stack2.isEmpty() && stack1.isEmpty();
        }

        private void pushToStack2() {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }

    // LC225:队列实现栈
    class MyStack1 {
        private Deque<Integer> deque;

        public MyStack1() {
            deque = new LinkedList<>();
        }

        public void push(int x) {
            int n = deque.size();
            deque.offer(x);
            while (n > 0) {
                deque.offer(deque.poll());
                n--;
            }
        }

        public int pop() {
            return deque.poll();
        }

        public int top() {
            return deque.peek();
        }

        public boolean empty() {
            return deque.isEmpty();
        }
    }

    /**
     * Your MyStack object will be instantiated and called as such:
     * MyStack obj = new MyStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * boolean param_4 = obj.empty();
     */

    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T data) {
            value = data;
        }
    }

    /**
     * 双向队列：来实现一个栈和队列
     */
    public static class DoubleEndsQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        public void addFromHead(T value) {
            Node<T> cur = new Node<T>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
        }

        public void addFromBottom(T value) {
            Node<T> cur = new Node<T>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.last = tail;
                tail.next = cur;
                tail = cur;
            }
        }

        public T popFromHead() {
            if (head == null) {
                return null;
            }
            Node<T> cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                cur.next = null;
                head.last = null;
            }
            return cur.value;
        }

        public T popFromBottom() {
            if (head == null) {
                return null;
            }
            Node<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
                cur.last = null;
            }
            return cur.value;
        }

        public boolean isEmpty() {
            return head == null;
        }

    }

    public static class MyStack<T> {
        private DoubleEndsQueue<T> queue;

        public MyStack() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

    public static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T poll() {
            return queue.popFromBottom();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

}
