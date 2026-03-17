package com.ssl.note.leetcode.编号刷题.LC295_数据流中位数;

import java.util.PriorityQueue;

public class MedianFinder {

  /**
   * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
   * 例如 arr = [2,3,4] 的中位数是 3 。
   * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
   * 实现 MedianFinder 类:
   * MedianFinder() 初始化 MedianFinder 对象。
   * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
   * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
   * 示例 1：
   * 输入
   * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
   * [[], [1], [2], [], [3], []]
   * 输出
   * [null, null, null, 1.5, null, 2.0]
   * 解释
   * MedianFinder medianFinder = new MedianFinder();
   * medianFinder.addNum(1);    // arr = [1]
   * medianFinder.addNum(2);    // arr = [1, 2]
   * medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
   * medianFinder.addNum(3);    // arr[1, 2, 3]
   * medianFinder.findMedian(); // return 2.0
   */
  private PriorityQueue<Integer> maxHeap;
  private PriorityQueue<Integer> minHeap;

  public MedianFinder() {
    maxHeap = new PriorityQueue<>((a, b) -> b - a);
    minHeap = new PriorityQueue<>();
  }

  public void addNum(int num) {
    // 入堆
    if (maxHeap.isEmpty() || num < maxHeap.peek()) {
      maxHeap.offer(num);
    } else {
      minHeap.offer(num);
    }
    // 平衡堆
    // maxHeap.size() 要么等于 minHeap.size()，要么比 minHeap.size() 大1
    if (maxHeap.size() > minHeap.size() + 1) {
      minHeap.offer(maxHeap.poll());
    } else if (minHeap.size() > maxHeap.size()) {
      maxHeap.offer(minHeap.poll());
    }
  }

  public double findMedian() {
    int num1 = maxHeap.isEmpty() ? 0 : maxHeap.peek();
    int num2 = minHeap.isEmpty() ? 0 : minHeap.peek();
    int size = minHeap.size() + maxHeap.size();
    // 是否是偶数
    boolean isEven = (size & 1) == 0;
    if (isEven) {
      return (double) (num1 + num2) / 2;
    }
    return (double) num1;
  }
}
