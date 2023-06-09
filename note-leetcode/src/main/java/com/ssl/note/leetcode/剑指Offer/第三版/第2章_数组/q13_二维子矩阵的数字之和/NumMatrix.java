package com.ssl.note.leetcode.剑指Offer.第三版.第2章_数组.q13_二维子矩阵的数字之和;

public class NumMatrix {

    private int[][] sums;

    /**
     * 二维子矩阵的数字之和
     */
    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        // 采用预处理的方式，转换为sums二维数组
        // 防止r-1,c-1在0位置越界，sums数组初始化多1行1列
        sums = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            int curRowSum = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                curRowSum += matrix[i][j];
                // sums = 上一行此列的sums + 本行目前元素值的和
                // 原公式 sums[i][j]=sums[i-1][j]+原数组中第i行中0到第j列元素和
                sums[i + 1][j + 1] = sums[i][j + 1] + curRowSum;
            }
        }
    }

    /**
     * 返回两个坐标之间区域的数字之和
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // 结果 = [r2][c2]-[r1-1][c2]-[r2][c1-1]+[r1-1][c1-1]，多减了一个左上角的区域，最后要加上来
        // 转换成加了一行一列的sums，需要全部+1
        return sums[row2 + 1][col2 + 1] - sums[row1][col2 + 1] - sums[row2 + 1][col1] + sums[row1][col1];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5},
        };
        /*
            sums = {
                0，0，0，0，0，0
                0，3，3，4，8，10
                0，8，14，18，24，27
                0，9，17，21，28，36
                0，13，22，26，34，49
                0，14，23，30，38，58
            }
         */
        NumMatrix numMatrix = new NumMatrix(matrix);
        // 计算左上角(2,1),右下角(4,3)矩阵元素的和
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        // 计算左上角(1,1),右下角(2,2)矩阵元素的和
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        // 计算左上角(1,2),右下角(2,4)矩阵元素的和
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }
}
