package com.ssl.note.zuo.learn.C24_暴力递归到动态规划6;

public class Code03_NQueens {

    /**
     * N皇后问题
     */
    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        // 用1维数组记录皇后存放的信息
        // 0行皇后放在record[0]的值上
        // n行皇后放在record[n]的值上
        int[] record = new int[n];
        return process1(0, record, n);
    }

    // 当前来到i行，一共是0~N-1行
    // 在i行上放皇后，所有列都尝试
    // 必须要保证跟之前所有的皇后不打架
    // int[] record record[x] = y 之前的第x行的皇后，放在了y列上
    // 返回：不关心i以上发生了什么，i.... 后续有多少合法的方法数
    public static int process1(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        // i行的皇后，放哪一列呢？j列
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                // 每一个皇后，枚举所有的j可能性，如果有效，才记录信息
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record, int i, int j) {
        // 0..i-1上的信息找 不共列，不共斜线
        // 不共行：由于是从上往下填皇后，保证了每一行只会填1个皇后，不会重复所以不用校验
        for (int k = 0; k < i; k++) {
            // 不共列:j != record[0..i]
            // 不共斜线:斜率不相同
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    // 请不要超过32皇后问题
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // n皇后，int整型右边有n个1
        // 如果你是13皇后问题，limit 最右13个1，其他都是0
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    // 7皇后问题
    // limit : 0....0 1 1 1 1 1 1 1
    // 之前皇后的列影响：colLim
    // 之前皇后的左下对角线影响：leftDiaLim
    // 之前皇后的右下对角线影响：rightDiaLim
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        // 列限制 = limit，就是一种有效方法
        if (colLim == limit) {
            return 1;
        }
        // pos中所有是1的位置，是你可以去尝试皇后的位置
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        // pos为0时循环结束
        while (pos != 0) {
            // 当前皇后放在最右边的1上
            mostRightOne = pos & (~pos + 1);
            // pos减去当前皇后的位置可能性
            pos = pos - mostRightOne;
            res += process2(limit,
                    colLim | mostRightOne,// 列的可能性 = | = 把下方的1都补上
                    (leftDiaLim | mostRightOne) << 1, // 左小的可能性
                    (rightDiaLim | mostRightOne) >>> 1);// 右下的可能性
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 15;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
