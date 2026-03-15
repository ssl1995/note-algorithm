package com.ssl.note.leetcode.编号刷题.LC155_最小栈;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;


public class MinStack {

  private Deque<Integer> dataStack;
  private Deque<Integer> minStack;

  public MinStack() {
    dataStack = new ArrayDeque<>();
    minStack = new ArrayDeque<>();
  }

  public void push(int val) {
    dataStack.push(val);
    if (minStack.isEmpty() || val < minStack.peek()) {
      minStack.push(val);
    } else {
      minStack.push(minStack.peek());
    }
  }

  public void pop() {
    if (!dataStack.isEmpty()) {
      dataStack.pop();
    }
    if (!minStack.isEmpty()) {
      minStack.pop();
    }
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
