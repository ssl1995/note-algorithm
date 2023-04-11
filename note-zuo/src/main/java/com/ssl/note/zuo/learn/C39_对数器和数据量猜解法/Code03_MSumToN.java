package com.ssl.note.zuo.learn.C39_对数器和数据量猜解法;

public class Code03_MSumToN {

    /**
     * 定义一种数：可以表示成若干（数量>1）连续正数和的数
     * 比如:
     * 5 = 2+3，5就是这样的数
     * 12 = 3+4+5，1 2就是这样的数
     * 1不是这样的数，因为要求数量大于1个、连续正数和
     * 2 = 1 + 1，2也不是，因为等号右边不是连续正数
     * 给定一个参数N，返回是不是可以表示成若干连续正数和的数
     */
    public static boolean isMSum1(int num) {
        // 暴力：从1、2、3+..求sum>num，下一个数<num
        // 再从2、3+..求sum>num，下一个数<num，最暴力
        for (int i = 1; i <= num; i++) {
            int sum = i;
            for (int j = i + 1; j <= num; j++) {
                if (sum + j > num) {
                    break;
                }
                if (sum + j == num) {
                    return true;
                }
                sum += j;
            }
        }
        return false;
    }

    /**
     * 通过暴力解，来得到最优解，就是判断num是否是2的某次方
     */
    public static boolean isMSum2(int num) {
        // 不是2^n:true
        // 2^n:false
//        boolean b1 = num == (num & (~num + 1));
//        boolean b2 = num == (num & (-num));
        return (num & (num - 1)) != 0;
    }

    public static void main(String[] args) {
        // 暴力解，一打印就发现,2^n = num时候，就返回false
        for (int num = 1; num < 200; num++) {
            System.out.println(num + " : " + isMSum1(num));
        }

        // 通过暴力解，来得到最优解，就是判断num是否是2的某次方

//        System.out.println("test begin");
//        for (int num = 1; num < 5000; num++) {
//            if (isMSum1(num) != isMSum2(num)) {
//                System.out.println("Oops!");
//            }
//        }
//        System.out.println("test end");

    }
}
