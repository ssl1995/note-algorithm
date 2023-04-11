package com.ssl.note.zuo.learn.C19_暴力递归到动态规划1.纸牌问题;

public class Code02_CardsInLine {

    /*
     * 纸牌问题：
     * 给定一个数组，玩家A和玩家B一次只能从最左或最右拿牌，
     * 返回最后获胜的分数(最大值)
     * 方法1：自然尝试
     */
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f1(arr, 0, arr.length - 1);
        int second = g1(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    // arr[L..R]，先手获得的最好分数返回
    public static int f1(int[] arr, int L, int R) {
        // 先手只剩一张牌
        if (L == R) {
            return arr[L];
        }
        int p1 = arr[L] + g1(arr, L + 1, R);
        int p2 = arr[R] + g1(arr, L, R - 1);
        // 先手，等后手选完取最大
        return Math.max(p1, p2);
    }

    // // arr[L..R]，后手获得的最好分数返回
    public static int g1(int[] arr, int L, int R) {
        // 后手只剩一张牌
        if (L == R) {
            return 0;
        }
        // 对手拿走了L位置的数
        int p1 = f1(arr, L + 1, R);
        // 对手拿走了R位置的数
        int p2 = f1(arr, L, R - 1);
        // 后手：等先手做决定完毕后，选择最小值
        return Math.min(p1, p2);
    }

    /*
     * 纸牌问题：
     * 给定一个数组，玩家A和玩家B一次只能从最左或最右拿牌，
     * 返回最后获胜的分数(最大值)
     * 方法2：方法1发现有重复算的值，所以使用中间缓存
     */
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // 一个先手Map，一个后手Map保存
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        // 每个位置都初始为-1，表示没算过
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }
        int first = f2(arr, 0, arr.length - 1, fmap, gmap);
        int second = g2(arr, 0, arr.length - 1, fmap, gmap);
        return Math.max(first, second);
    }

    // arr[L..R]，先手获得的最好分数返回
    public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        // 算过了，直接返回
        if (fmap[L][R] != -1) {
            return fmap[L][R];
        }
        int ans = 0;
        if (L == R) {
            ans = arr[L];
        } else {
            int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
            int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap);
            ans = Math.max(p1, p2);
        }
        // 保存算过的值
        fmap[L][R] = ans;
        return ans;
    }

    // // arr[L..R]，后手获得的最好分数返回
    public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (gmap[L][R] != -1) {
            return gmap[L][R];
        }
        int ans = 0;
        if (L != R) {
            // 对手拿走了L位置的数
            int p1 = f2(arr, L + 1, R, fmap, gmap);
            // 对手拿走了R位置的数
            int p2 = f2(arr, L, R - 1, fmap, gmap);
            ans = Math.min(p1, p2);
        }
        gmap[L][R] = ans;
        return ans;
    }

    /*
     * 纸牌问题：
     * 给定一个数组，玩家A和玩家B一次只能从最左或最右拿牌，
     * 返回最后获胜的分数(最大值)
     * 方法3：动态规划
     */
    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            fmap[i][i] = arr[i];
        }
        // 从开始的第二条对角线开始
        for (int startCol = 1; startCol < N; startCol++) {
            int R = startCol;
            int L = 0;
            // 循环条件，行或者列<N即可
            while (R < N) {
                fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);
                gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
                // 行++
                R++;
                // 列++
                L++;
            }
        }
        // 返回两张表最末尾的最大值
        return Math.max(fmap[0][N - 1], gmap[0][N - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));

    }

}
