package com.ssl.note.zuo.learn.C07_堆和堆排序;

import java.util.Arrays;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/03 10:08
 * @Describe:
 */
public class MinHeapSort {

    // 从小到大排序
    public static void heapSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        // 1.大根堆，堆顶保持最大值
        for (int parent = (arr.length - 2) / 2; parent >= 0; parent--) {
            heapify(arr, parent, arr.length);
        }
        // 2.将堆顶最大值依次放回数组末尾 = 从小到大排序
        // 从最后一个位置开始交换值，因为0位置是固定每次都要交换的，所以可以从末尾开始遍历 = 写法简单
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
        // 也可以从0位置开始交换值
//        for (int i = 0; i <= arr.length - 1; i++) {
//            swap(arr, 0, arr.length - 1 - i);
//            heapify1(arr, 0, arr.length - 1 - i);
//        }
    }


    private static void heapify(int[] arr, int parent, int n) {
        while (2 * parent + 1 < n) {
            int left = 2 * parent + 1;
            if (left + 1 < n && arr[left + 1] >= arr[left]) {
                left++;
            }
            if (arr[parent] >= arr[left]) {
                break;
            }
            swap(arr, parent, left);
            parent = left;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 6, 6};
        int[] arr1 = {4, 5, 1, 6, 6};
        heapSort(arr);
        heapSort1(arr1);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
    }

    // 练习
    public static void heapSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // arr[0]就是数组最大值
        for (int i = (arr.length - 1 - 1) / 2; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        for (int i = 0; i <= arr.length - 1; i++) {
            swap(arr, 0, arr.length - 1 - i);
            heapify1(arr, 0, arr.length - 1 - i);
        }
    }

    public static void heapify1(int[] arr, int parent, int n) {
        if (arr == null || arr.length < 2) {
            return;
        }
        while (2 * parent + 1 < n) {
            int left = 2 * parent + 1;
            if (left + 1 < n && arr[left + 1] >= arr[left]) {
                left++;
            }
            if (arr[parent] >= arr[left]) {
                break;
            }
            swap(arr, parent, left);
            parent = left;
        }

    }
}
