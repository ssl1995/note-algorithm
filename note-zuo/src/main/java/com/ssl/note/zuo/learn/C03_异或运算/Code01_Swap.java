package com.ssl.note.zuo.learn.C03_异或运算;

public class Code01_Swap {

    /**
     * 异或：相同为0，不同为1 -> 记成无进位相加 = 不会忘
     * 同或：相同为1，不同为0
     */
    public static void main(String[] args) {

        int[] arr = {1, 2, 3};
        // 不同位置正常交换，没有问题
        swap1(arr, 0, 1);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println("-----");

        arr = new int[]{1, 2, 3};
        // 相同位置交换，会变成0
        swap1(arr, 0, 0);
        System.out.println(arr[0]);// 0位置变成0
        System.out.println(arr[1]);// 2位置没变
        System.out.println("-----");

        arr = new int[]{1, 2, 3};
        // 加i==j，就能防止这种情况
        swap2(arr, 0, 0);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }

    public static void swap1(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    public static void swap2(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


}
