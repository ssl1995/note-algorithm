package com.ssl.note.leetcode.编号刷题.LC155_最小栈;

import java.util.ArrayDeque;
import java.util.Deque;


public class MinStack1 {

  // 当前数,最小值
  private Deque<int[]> dataStack;

  public MinStack1() {
    dataStack = new ArrayDeque<>();
  }

  public void push(int val) {
    if (dataStack.isEmpty()) {
      dataStack.push(new int[]{val, val});
      return;
    }

    int[] peek = dataStack.peek();
    int[] push = new int[]{val, Math.min(peek[1],val)};
    dataStack.push(push);
  }

  public void pop() {
    if (dataStack.isEmpty()) {
      return;
    }
    dataStack.pop();
  }

  public int top() {
    if (dataStack.isEmpty()) {
      return -1;
    }
    return dataStack.peek()[0];
  }

  public int getMin() {
    if (dataStack.isEmpty()) {
      return -1;
    }
    return dataStack.peek()[1];
  }

}
