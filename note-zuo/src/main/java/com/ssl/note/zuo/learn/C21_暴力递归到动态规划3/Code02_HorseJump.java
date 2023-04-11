package com.ssl.note.zuo.learn.C21_暴力递归到动态规划3;

public class Code02_HorseJump {

    /**
     * 马跳日问题
     * (a,b)是目标位置，k是马需要跳的步数，正好跳到(a,b)需要多少步数？
     * 限制：棋盘的大小是10*9
     * 方法1：暴力尝试
     */
    public static int jump(int a, int b, int k) {
        return process(0, 0, k, a, b);
    }

    public static int process(int x, int y, int rest, int a, int b) {
        // 棋盘大小限制：10*9
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        // 成功的base case
        if (rest == 0) {
            return (x == a && y == b) ? 1 : 0;
        }
        // 8种可能性
        int ways = process(x + 2, y + 1, rest - 1, a, b);
        ways += process(x + 1, y + 2, rest - 1, a, b);
        ways += process(x - 1, y + 2, rest - 1, a, b);
        ways += process(x - 2, y + 1, rest - 1, a, b);
        ways += process(x - 2, y - 1, rest - 1, a, b);
        ways += process(x - 1, y - 2, rest - 1, a, b);
        ways += process(x + 1, y - 2, rest - 1, a, b);
        ways += process(x + 2, y - 1, rest - 1, a, b);
        return ways;
    }

    /**
     * 方法2：将方法1改成动态规划
     */
    public static int dp(int a, int b, int k) {
        int[][][] dp = new int[10][9][k + 1];
        // 成功的base case
        dp[a][b][0] = 1;
        // 上面的层次依赖下面的层
        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    // 防止越界，使用pick
                    int ways = pick(dp, x + 2, y + 1, rest - 1);
                    ways += pick(dp, x + 1, y + 2, rest - 1);
                    ways += pick(dp, x - 1, y + 2, rest - 1);
                    ways += pick(dp, x - 2, y + 1, rest - 1);
                    ways += pick(dp, x - 2, y - 1, rest - 1);
                    ways += pick(dp, x - 1, y - 2, rest - 1);
                    ways += pick(dp, x + 1, y - 2, rest - 1);
                    ways += pick(dp, x + 2, y - 1, rest - 1);
                    dp[x][y][rest] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int x, int y, int rest) {
        // 如果越界了，返回0
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][rest];
    }

    public static int ways(int a, int b, int step) {
        return f(0, 0, step, a, b);
    }

    public static int f(int i, int j, int step, int a, int b) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        if (step == 0) {
            return (i == a && j == b) ? 1 : 0;
        }
        return f(i - 2, j + 1, step - 1, a, b) + f(i - 1, j + 2, step - 1, a, b) + f(i + 1, j + 2, step - 1, a, b)
                + f(i + 2, j + 1, step - 1, a, b) + f(i + 2, j - 1, step - 1, a, b) + f(i + 1, j - 2, step - 1, a, b)
                + f(i - 1, j - 2, step - 1, a, b) + f(i - 2, j - 1, step - 1, a, b);

    }

    public static int waysdp(int a, int b, int s) {
        int[][][] dp = new int[10][9][s + 1];
        dp[a][b][0] = 1;
        for (int step = 1; step <= s; step++) { // 按层来
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][step] = getValue(dp, i - 2, j + 1, step - 1) + getValue(dp, i - 1, j + 2, step - 1)
                            + getValue(dp, i + 1, j + 2, step - 1) + getValue(dp, i + 2, j + 1, step - 1)
                            + getValue(dp, i + 2, j - 1, step - 1) + getValue(dp, i + 1, j - 2, step - 1)
                            + getValue(dp, i - 1, j - 2, step - 1) + getValue(dp, i - 2, j - 1, step - 1);
                }
            }
        }
        return dp[0][0][s];
    }

    // 在dp表中，得到dp[i][j][step]的值，但如果(i，j)位置越界的话，返回0；
    public static int getValue(int[][][] dp, int i, int j, int step) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        return dp[i][j][step];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(ways(x, y, step));
        System.out.println(dp(x, y, step));

        System.out.println(jump(x, y, step));
    }
}
