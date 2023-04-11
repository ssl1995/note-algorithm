package com.ssl.note.leetcode.剑指Offer.第三版.第7章_队列.q42_最近请求次数;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author SongShengLin
 * @date 2021/10/8 9:08 上午
 * @description
 */
public class RecentCounter {

    private Deque<Integer> queue;
    private int size;

    public RecentCounter() {
        queue = new LinkedList<>();
        // 题目要求窗口长度=3000
        size = 3000;
    }

    public int ping(int t) {
        queue.offer(t);
        while (queue.peek() < t - size) {
            queue.poll();
        }
        return queue.size();
    }

    public static void main(String[] args) {
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        // peek=1才是头部
        System.out.println("peek:" + queue.peek());
        System.out.println("peekFirst:" + queue.peekFirst());
        System.out.println("peekLast:" + queue.peekLast());
        System.out.println("-------------");
        RecentCounter recentCounter = new RecentCounter();
        // 先进先出，说明是队列
        System.out.println(recentCounter.ping(1));
        System.out.println(recentCounter.ping(100));
        System.out.println(recentCounter.ping(3001));
        System.out.println(recentCounter.ping(3002));
    }
}
