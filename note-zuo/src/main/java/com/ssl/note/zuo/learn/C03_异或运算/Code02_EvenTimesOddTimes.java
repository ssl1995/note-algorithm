package com.ssl.note.zuo.learn.C03_异或运算;

public class Code02_EvenTimesOddTimes {

    /**
     * arr中，只有一种数，出现奇数次；其余数出现偶数次
     */
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    /**
     * arr中，有两种数(两个数不相同)，出现奇数次；其余数出现偶数次
     */
    public static void printOddTimesNum2(int[] arr) {
        // 1.干掉所有偶数，得到eor=a^b
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // 校验：因为a和b不相同，所以a^b !=0,如果等于0就返回
        if (eor == 0) {
            System.out.println(0 + " " + 0);
        }
        // 2.取最右侧的1 = 一个数 & 它的相反数
        // eor最右侧的1，提取出来
        // eor :     00110010110111000
        // ~eor:     11001101001000111
        // ~eor+1:   11001101001001000
        // 最右侧的1:  00000000000001000
        // 一个数num，最右侧的1 = num & ~num+1 = num & -num
        int rightOne = eor & (~eor + 1);

        // 3.将这个1区分arr所有数
        int a = 0; // eor'
        for (int i = 0; i < arr.length; i++) {
            //  arr[1] =  111100011110000
            // rightOne=  000000000010000
            if ((arr[i] & rightOne) != 0) {
                a ^= arr[i];
            }
        }
        // 4.已知eor=a^b 和 a，求b=eor^a
        System.out.println(a + " " + (eor ^ a));
    }


    /**
     * 一个数中二进制1的次数
     */
    public static int bit1counts(int N) {
        int count = 0;
        while (N != 0) {
            //   011011010000
            //  如何能得到最右边的1
            //   000000010000
            // 把最右侧的1提取出来 = a & (~a+1) = a & (-a)
            int rightOne = N & ((~N) + 1);
            count++;
            N ^= rightOne;
            // N -= rightOne
        }
        return count;
    }


    public static void main(String[] args) {
        // 2出现了一次，其余数出现偶数次
        int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1};
        printOddTimesNum1(arr1);

        // 3出现3次，2出现5次，其余数出现偶数次
        int[] arr2 = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2};
        printOddTimesNum2(arr2);

        // 一个数中二进制1的次数
        int num = 3;
        System.out.println(bit1counts(num));

        // 相反数
        System.out.println("num:" + num);
        System.out.println("num的相反数:" + (~num + 1));
        System.out.println("num最右侧的1:" + (num & (~num + 1)));

    }

}
