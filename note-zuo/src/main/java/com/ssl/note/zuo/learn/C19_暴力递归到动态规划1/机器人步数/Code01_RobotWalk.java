package com.ssl.note.zuo.learn.C19_暴力递归到动态规划1.机器人步数;

public class Code01_RobotWalk {

    /**
     * 机器人路径问题
     * N：总有1-N个位置
     * M：机器人初始位置
     * K：机器人必须走K步
     * P：最终需要到达的位置
     * 返回：所有到P位置的次数
     * 方法1：暴力尝试自己想到的可能性
     */
    public static int ways1(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || P < 1 || P > N || K < 1) {
            return -1;
        }
        return process1(M, K, P, N);
    }

    // 机器人当前来到的位置是cur，
    // 机器人还有rest步需要去走，
    // 最终的目标是aim，
    // 有哪些位置？1~N
    // 返回：机器人从cur出发，走过rest步之后，最终停在aim的方法数，是多少？
    public static int process1(int cur, int rest, int P, int N) {
        if (rest == 0) { // 如果已经不需要走了，走完了！
            return cur == P ? 1 : 0;
        }
        // (cur, rest)
        if (cur == 1) { // 1 -> 2
            return process1(2, rest - 1, P, N);
        }
        // (cur, rest)
        if (cur == N) { // N-1 <- N
            return process1(N - 1, rest - 1, P, N);
        }
        // (cur, rest)，这里就会产生重复值
        return process1(cur - 1, rest - 1, P, N) + process1(cur + 1, rest - 1, P, N);
    }

    /**
     * 机器人路径问题
     * 方法2：中间表缓存
     */
    public static int ways2(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || P < 1 || P > N || K < 1) {
            return -1;
        }
        // cur范围：[1,N]
        // rest范围：[0,K]
        // 生成一个中间表做一个缓存
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        // dp就是缓存表
        // dp[cur][rest] == -1 -> process1(cur, rest)之前没算过！
        // dp[cur][rest] != -1 -> process1(cur, rest)之前算过！返回值，dp[cur][rest]
        // N+1 * K+1
        return process2(M, K, P, N, dp);
    }

    // cur 范: 1 ~ N
    // rest 范：0 ~ K
    public static int process2(int cur, int rest, int P, int N, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        // 之前没算过！
        int ans = 0;
        if (rest == 0) {
            ans = cur == P ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(2, rest - 1, P, N, dp);
        } else if (cur == N) {
            ans = process2(N - 1, rest - 1, P, N, dp);
        } else {
            ans = process2(cur - 1, rest - 1, P, N, dp) + process2(cur + 1, rest - 1, P, N, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }


    /**
     * 机器人路径问题
     * 方法3：动态规划
     */
    public static int ways3(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || P < 1 || P > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        // 目标结果位置，一旦来到就是1
        dp[P][0] = 1;
        // 第0行代表cur来到0位置，不存在
        for (int rest = 1; rest <= K; rest++) {
            // 第1行位置的可能性
            dp[1][rest] = dp[2][rest - 1];
            // 中间位置的可能性
            for (int cur = 2; cur < N; cur++) {
                // 动态转移方程确定了：只能从第1列开始从左往右遍历，而不是从行开始遍历
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            // 第N行位置的可能性
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[M][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 6, 4));
        System.out.println(ways2(5, 2, 6, 4));
        System.out.println(ways3(5, 2, 6, 4));
    }

}
