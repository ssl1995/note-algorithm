package com.ssl.note.zuo.learn.C18_一些经典递归.递归逆序一个栈;

import java.util.Stack;

public class Code05_ReverseStackUsingRecursive {

    /**
     * 使用递归逆序一个栈
     */
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        // 拿到栈底元素，不改变原有结构
        int i = f(stack);
        // 整体再次递归
        reverse(stack);
        // 原先的栈底放入栈
        stack.push(i);
    }

    // 栈底元素移除掉
    // 上面的元素盖下来
    // 返回移除掉的栈底元素
    // 传入： 123 。返回：123->12 返回3
    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }

}
