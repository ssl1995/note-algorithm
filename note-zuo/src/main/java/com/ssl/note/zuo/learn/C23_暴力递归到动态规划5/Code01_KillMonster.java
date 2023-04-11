package com.ssl.note.zuo.learn.C23_暴力递归到动态规划5;

public class Code01_KillMonster {

    /**
     * 怪兽有N滴血，英雄每次大家，都会让怪兽流式[0..M]滴血，
     * 每一次等概率在[0..M]上等概率获得一个值让怪兽流血，
     * 求K次打击后，英雄把怪兽砍死的概率？
     */
    public static double right(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        // 被k次砍死的概率
        long kill = process(K, M, N);
        // 总的可能性，每一次都是0...M的选择
        long all = (long) Math.pow(M + 1, K);
        return (double) ((double) kill / (double) all);
    }

    // 怪兽还剩hp点血
    // 每次的伤害在[0~M]范围上
    // 还有times次可以砍
    // 返回砍死的情况数！
    public static long process(int hp, int M, int times) {
        if (times == 0) {
            return hp <= 0 ? 1 : 0;
        }
        // times!=0,但是怪兽的血量还有，说明还需要(M+1)^剩余的time次
        if (hp <= 0) {
            return (long) Math.pow(M + 1, times);
        }
        long ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(hp - i, M, times - 1);
        }
        return ways;
    }

    public static double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        // 为了好画图，让k作为行
        long[][] dp = new long[K + 1][N + 1];
        // 剩下0次，还剩0滴血，等于1次有效
        dp[0][0] = 1;
        // 普遍情况
        // times:还剩多少次，可以砍；hp：怪兽还剩多少血
        for (int times = 1; times <= K; times++) {
            // times!=0,但是怪兽的血量还有，说明还需要(M+1)^剩余的time次
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                long ways = 0;
                // 枚举M次，思考是否可以优化：临近位置是否可以代替
                for (int i = 0; i <= M; i++) {
                    // 没有越界
                    if (hp - i >= 0) {
                        ways += dp[times - 1][hp - i];
                    } else {
                        // 已经越界
                        ways += (long) Math.pow(M + 1, times - 1);
                    }
                }
                dp[times][hp] = ways;
            }
        }
        long kill = dp[K][N];
        return (double) ((double) kill / (double) all);
    }

    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                // 假设:M=7,dp[5][10]依赖dp[4][3...10]
                // 假设:M=7,dp[5][11]依赖dp[4][4...11]
                // 枚举值优化：dp[5][11] =dp[5][10]+dp[4][11]-dp[4][3]
                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];// dp[5][11] =dp[5][10]+dp[4][11]
                if (hp - 1 - M >= 0) {
                    dp[times][hp] -= dp[times - 1][hp - 1 - M];
                } else {
                    // 公式决定：还需要减去负数的情况，因为已经dp[times-1]已经计算过了，就必须减去
                    dp[times][hp] -= Math.pow(M + 1, times - 1);
                }
            }
        }
        long kill = dp[K][N];
        return (double) ((double) kill / (double) all);
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
