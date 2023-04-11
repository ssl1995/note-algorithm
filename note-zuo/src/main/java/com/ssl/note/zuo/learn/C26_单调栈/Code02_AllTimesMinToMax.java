package com.ssl.note.zuo.learn.C26_单调栈;

import java.util.Stack;

public class Code02_AllTimesMinToMax {

    /**
     * arr中都是正数，任何一个子数组sub，都可以算出sub累计和*sub中的最小值
     * 所有子数组中，这个最小值最大是？
     * 方法1：暴力破解
     */
    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    /**
     * arr中都是正数，任何一个子数组sub，都可以算出sub累计和*sub中的最小值
     * 所有子数组中，这个最小值最大是？
     * 方法2：每个数都作为子数组的最小值，并且获得所有这个最小值的子数组
     */
    public static int max2(int[] arr) {
        int size = arr.length;
        // 1.前缀和，便于获得[i..j]的累加和
        int[] sums = new int[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        // 2.假设每个数为sub最小值，找出所有它的子数组
        int max = Integer.MIN_VALUE;
        // 没有用List保存，是因为相同数的最右边是可以算正确答案的，之前错误的计算不影响
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                // 栈底到栈顶：从小到大，保证x的左边比它小的取不到，右边比它小的取不到
                // i-1是栈顶此时pop元素上一次遍历到的位置
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
        }
        return max;
    }

    public static int[] gerenateRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenateRondomArray();
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }

}
