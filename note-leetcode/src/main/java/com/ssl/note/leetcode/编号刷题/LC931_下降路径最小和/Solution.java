package com.ssl.note.leetcode.编号刷题.LC931_下降路径最小和;

/**
 * @author SongShengLin
 * @date 2022/10/7 15:45
 * @description
 */
public class Solution {
    /**
     * 下降路径最小和
     */
    public int minFallingPathSum(int[][] matrix) {
        int N = matrix.length;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = matrix[i - 1][j];
                if (j > 0) {
                    sum = Math.min(sum, matrix[i - 1][j - 1]);
                }
                if (j < N - 1) {
                    sum = Math.min(sum, matrix[i - 1][j + 1]);
                }
                matrix[i][j] += sum;
            }
        }

        int res = Integer.MAX_VALUE;
        for (int num : matrix[N - 1]) {
            res = Math.min(res, num);
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] m = {{1, 1, 1,}, {5, 3, 1}, {2, 3, 4}};
        System.out.println(solution.minFallingPathSum(m));
    }

}
