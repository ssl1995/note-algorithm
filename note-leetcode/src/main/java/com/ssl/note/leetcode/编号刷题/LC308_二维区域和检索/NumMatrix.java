package com.ssl.note.leetcode.编号刷题.LC308_二维区域和检索;

/**
 * @author SongShengLin
 * @date 2022/11/12 21:24
 * @description
 */
public class NumMatrix {

    private int[][] indexTree;
    private int[][] nums;
    private int M;
    private int N;

    /**
     * 使用indexTree，时间复杂度O(logN * logN)
     */
    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        M = matrix.length;
        N = matrix[0].length;

        nums = new int[M][N];
        indexTree = new int[M + 1][N + 1];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        if (M == 0 || N == 0) {
            return;
        }

        int add = val - nums[row][col];
        nums[row][col] = val;

        add(row, col, add);
    }

    private void add(int row, int col, int add) {
        for (int i = row + 1; i <= M; i += i & (-i)) {
            for (int j = col + 1; j <= N; j += j & (-j)) {
                indexTree[i][j] += add;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (M == 0 || N == 0) {
            return 0;
        }
        return sum(row2, col2) + sum(row1 - 1, col1 - 1)
                - sum(row1 - 1, col2) - sum(row2, col1 - 1);
    }

    private int sum(int row, int col) {
        int sum = 0;
        for (int i = row + 1; i >= 1; i -= i & (-i)) {
            for (int j = col + 1; j >= 1; j -= j & (-j)) {
                sum += indexTree[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] nums = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(nums);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        numMatrix.update(3, 2, 2);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
    }
}
