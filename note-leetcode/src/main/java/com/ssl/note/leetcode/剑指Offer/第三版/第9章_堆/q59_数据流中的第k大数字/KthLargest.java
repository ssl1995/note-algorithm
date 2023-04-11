package com.ssl.note.leetcode.剑指Offer.第三版.第9章_堆.q59_数据流中的第k大数字;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author SongShengLin
 * @date 2021/10/15 8:51 上午
 * @description
 */
public class KthLargest {

    private Queue<Integer> minHeap;

    private int size;

    public KthLargest(int k, int[] nums) {
        // 默认是小根堆
        minHeap = new PriorityQueue<>();
        size = k;
        // offer、poll、peek是不报错的
        // add、remove、element是报错的
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
    }

    public int add(int val) {
        if (minHeap.size() < size) {
            minHeap.offer(val);
        } else if (val > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(val);
        }
        return minHeap.peek();
    }
}
