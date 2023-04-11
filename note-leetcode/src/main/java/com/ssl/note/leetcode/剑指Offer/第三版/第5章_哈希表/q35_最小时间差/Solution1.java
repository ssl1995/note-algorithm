package com.ssl.note.leetcode.剑指Offer.第三版.第5章_哈希表.q35_最小时间差;

import java.util.List;

/**
 * @Author: SongShengLin
 * @Date: 2022/10/30 21:45
 * @Describe:
 */
public class Solution1 {

    public int findMinDifference(List<String> timePoints) {
        if (timePoints == null) {
            return 0;
        }
        if (timePoints.size() > 24 * 60) {
            return 0;
        }
        boolean[] times = new boolean[24 * 60];
        for (String timePoint : timePoints) {
            String[] hourMin = timePoint.split(":");
            String hour = hourMin[0];
            String min = hourMin[1];
            int time = Integer.parseInt(hour) * 60 + Integer.parseInt(min);
            if (times[time]) {
                return 0;
            }
            times[time] = true;
        }
        return getMinLen(times);
    }

    private int getMinLen(boolean[] times) {
        int minLen = Integer.MAX_VALUE;
        int preI = -1;
        int fistTime = Integer.MAX_VALUE;
        int lastTime = Integer.MIN_VALUE;
        for (int i = 0; i < times.length; i++) {
            if (times[i]) {
                if (preI != -1) {
                    minLen = Math.min(minLen, i - preI);
                }
                preI = i;
                fistTime = Math.min(fistTime, i);
                lastTime = Math.max(lastTime, i);
            }
        }
        // 排序后，第一个时间可以当做第二天的起始时间 - 排序后的最后一个时间 作为一种特殊情况
        minLen = Math.min(minLen, fistTime + times.length - lastTime);
        return minLen;
    }
}
