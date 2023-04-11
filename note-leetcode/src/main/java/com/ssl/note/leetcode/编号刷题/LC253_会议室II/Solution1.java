package com.ssl.note.leetcode.编号刷题.LC253_会议室II;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author SongShengLin
 * @date 2022/2/22 9:32 PM
 * @description
 */
public class Solution1 {
    /**
     * 所需会议室的最多数量
     * 给你一个会议时间安排的数组 intervals ，
     * 每个会议时间都会包括开始和结束的时间 intervals[i] = [start, end] ，
     * 返回 所需能安排的会议室的最多数量。
     * 输入：intervals = [[0,30],[5,10],[15,20]]
     * 输出：2
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // 贪心：按照结束时间排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int lastEnd = 0;
        int res = 0;
        for (int[] interval : intervals) {
            // 上一个会议结束时间早于下一个会议的开始时间，就可以选择
            if (lastEnd <= interval[0]) {
                res++;
                lastEnd = interval[1];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        int[][] nums = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(solution.minMeetingRooms(nums));
    }
}
