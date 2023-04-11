package com.ssl.note.zuo.learn.C16_并查集;

// 本题为leetcode原题
// 测试链接：
// 可以直接通过
public class Code01_FriendCircles {
    /**
     * LC547：省份数量
     */
    public static int findCircleNum(int[][] M) {
        int N = M.length;
        // 初始化：{0} {1} {2} 直到{N-1}
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            // 只遍历矩阵右上半区
            for (int j = i + 1; j < N; j++) {
                // i和j互相认识，就合并
                if (M[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets();
    }

    /**
     * 手写一个并查集：
     * int实现
     */
    public static class UnionFind {
        // parent[i] = k ： i的父亲是k
        private int[] parent;
        // size[i] = k ： 如果i是代表节点，size[i]才有意义，否则无意义
        // 意思是i所在的集合大小是多少
        private int[] size;
        // 辅助结构，使用int做栈操作
        private int[] stack;
        // 一共有多少个集合
        private int sets;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            stack = new int[N];
            // 初试化每个数字都是单独的省份
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // 从i开始一直往上，往上到不能再往上，代表节点，返回
        // 这个过程要做路径压缩
        private int find(int i) {
            int hi = 0;
            // 往上找父亲
            while (i != parent[i]) {
                // 沿途经过的路径进栈
                stack[hi++] = i;
                // 找父亲
                i = parent[i];
            }
            // 出栈，路径压缩
            for (hi--; hi >= 0; hi--) {
                parent[stack[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                // 小挂大
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                // f1!=f2，每次都是独立的一个合并，sets--
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }

    public static void main(String[] args) {
        int[][] nums = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}};

        // parent:[0,0,2]
        // size:[2,1,1]
        // help:[0,0,0]
        // sets:2
        System.out.println(findCircleNum(nums));
    }

}
