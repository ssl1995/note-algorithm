package com.ssl.note.leetcode.剑指Offer.第三版.第1章_整数的基础知识.q4_只出现一次的数字;

/**
 * @author SongShengLin
 * @date 2021/9/9
 */
public class Solution {

    // Q:一个数出现1次，其余数出现3次，找出这个数
    // 举一反三：一个数出现m次，其余数出现n次，m不能被n整除
    public int singleNumber(int[] nums) {
        // int是32位整数
        // bitSums:保存nums中每个元素二进制形式中第i个数位总和
        // 1.记录每个数的位1的和
        int N = 32;
        int[] bitSums = new int[N];
        for (int num : nums) {
            for (int i = 0; i < N; i++) {
                // 从高位到低位统计每个num二进制位出现的次数
                bitSums[i] += (num >> (N - 1 - i)) & 1;
            }
        }
        // 2.从低位往高位还原这个数
        int res = 0;
        for (int i = 0; i < N; i++) {
            // bitSums%3==0，说明是出现三次的数，不用统计
            // bitSums%3==1，说明是出现一次的数，该二进制位为1
            // bitSums%3==2，不存在，因为限定了1个数出现1次
            // 举一反三：3改成n即可
            // 这里需要加一个(res << 1)
            res = (res << 1) + bitSums[i] % 3;
        }
        return res;
    }
}
