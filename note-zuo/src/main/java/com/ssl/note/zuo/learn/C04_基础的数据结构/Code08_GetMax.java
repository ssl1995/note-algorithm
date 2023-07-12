package com.ssl.note.zuo.learn.C04_基础的数据结构;

public class Code08_GetMax {

    /**
     * 学习递归:求arr中的最大值
     * 递归时间复杂度：master公式
     * T(N) = a * T(N/b) + O(N^d)(其中的a、b、d都是常数)
     * a: 子过程的计算次数
     * N/b: 子过程的样本量
     * O(N^d): 子结果合并的时间复杂度
     * 满足上述，可以直接通过Master公式来确定时间复杂度
     * 如果 log(b,a) < d，复杂度为O(N^d)
     * 如果 log(b,a) > d，复杂度为O(N^log(b,a))
     * 如果 log(b,a) == d，复杂度为O(N^d * log(b,a) )
     */
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        // a=2:a=2,同一个递归，拆成2次递归
        // N/2:b=2,每次递归，样本量减半
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        // O(1):d=0,每次合并代码，只是比较最大值 = O(N^0)
        return Math.max(leftMax, rightMax);
    }
}
