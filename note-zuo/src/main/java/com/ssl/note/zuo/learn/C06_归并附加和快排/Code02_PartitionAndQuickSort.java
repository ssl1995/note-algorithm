package com.ssl.note.zuo.learn.C06_归并附加和快排;

import java.util.Arrays;

public class Code02_PartitionAndQuickSort {

    /**
     * 荷兰国旗问题1: <=arr[R]放左边，>arr[R]放右边
     */
    public static int netherlandsFlag1(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int less = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                swap(arr, index, ++less);
            }
            index++;
        }
        swap(arr, ++less, R);
        return less;
    }

    /**
     * 荷兰国旗问题2: < arr[R]放左边，=放中间，>放右边
     * 返回：=区间的坐标
     */
    public static int[] netherlandsFlag2(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        int more = R;
        int index = L;
        // 将<arr[R]放左边，=放中间，>放右边
        while (index < more) {
            if (arr[index] == arr[R]) {
                // =，移动遍历指针
                index++;
            } else if (arr[index] < arr[R]) {
                // <,遍历指针移动
                swap(arr, index++, ++less);
            } else {
                // >时，不移动遍历指针index，因为less指针还要遍历该位置
                swap(arr, index, --more);
            }
        }
        // 将arr[R]归位：与>的第一个数交换
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    /**
     * 快排1.0：<=arr[R]放左边，>arr[R]放右边,返回>区域第一个数坐标
     */
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // L..R partition arr[R] [ <=arr[R] arr[R] >arr[R] ]
        int M = netherlandsFlag1(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }

    /**
     * 快排2.0：< arr[R]放左边，=放中间，>放右边
     */
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    // arr[L...R] 排有序，快排2.0方式
    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // [ equalArea[0]  ,  equalArea[0]]
        int[] equalArea = netherlandsFlag2(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }


    /**
     * 快排3.0：< arr[R]放左边，=放中间，>放右边 且 随机快排
     */
    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // 随机快排：在2.0版本上，加一个随机交换R位置的数
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag2(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int[] arr = {12, 3, 4, 52, 12};
        int L = 0;
        int R = arr.length - 1;
        int i = netherlandsFlag1(arr, L, R);
        System.out.println(Arrays.toString(arr));
        System.out.println("i=" + i);
    }

}
