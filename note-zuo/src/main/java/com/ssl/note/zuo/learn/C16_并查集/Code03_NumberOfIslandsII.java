package com.ssl.note.zuo.learn.C16_并查集;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/number-of-islands-ii/
// 所有方法都可以直接通过
public class Code03_NumberOfIslandsII {

    /**
     * LC302:岛屿数量II
     * 时间复杂度：O(m*n)+O(k)
     */
    public static List<Integer> numIslands21(int m, int n, int[][] positions) {
        UnionFind1 uf = new UnionFind1(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    public static class UnionFind1 {
        private int[] parent;
        private int[] size;
        private int[] help;
        private final int row;
        private final int col;
        private int sets;

        public UnionFind1(int m, int n) {
            row = m;
            col = n;
            sets = 0;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }

        private int index(int r, int c) {
            return r * col + c;
        }

        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        private void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                return;
            }
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            // 只要有1个没有初始化，就不连接
            if (size[i1] == 0 || size[i2] == 0) {
                return;
            }
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int connect(int r, int c) {
            int index = index(r, c);
            // index没有初始化，就
            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sets++;
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
            }
            return sets;
        }

    }

    // 课上讲的如果m*n比较大，会经历很重的初始化，而k比较小，怎么优化的方法
    public static List<Integer> numIslands22(int m, int n, int[][] positions) {
        UnionFind2 uf = new UnionFind2();
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    public static class UnionFind2 {
        private HashMap<String, String> parent;
        private HashMap<String, Integer> size;
        private ArrayList<String> stack;
        private int sets;

        public UnionFind2() {
            parent = new HashMap<>();
            size = new HashMap<>();
            stack = new ArrayList<>();
            sets = 0;
        }

        private String find(String cur) {
            while (!cur.equals(parent.get(cur))) {
                stack.add(cur);
                cur = parent.get(cur);
            }
            for (String str : stack) {
                parent.put(str, cur);
            }
            stack.clear();
            return cur;
        }

        private void union(String s1, String s2) {
            if (parent.containsKey(s1) && parent.containsKey(s2)) {
                String f1 = find(s1);
                String f2 = find(s2);
                if (!f1.equals(f2)) {
                    int size1 = size.get(f1);
                    int size2 = size.get(f2);
                    String big = size1 >= size2 ? f1 : f2;
                    String small = Objects.equals(big, f1) ? f2 : f1;
                    parent.put(small, big);
                    size.put(big, size1 + size2);
                    sets--;
                }
            }
        }

        public int connect(int r, int c) {
            // key:0_1表示0和1连接过
            String key = r + "_" + c;
            if (!parent.containsKey(key)) {
                parent.put(key, key);
                size.put(key, 1);
                sets++;
                String up = r - 1 + "_" + c;
                String down = r + 1 + "_" + c;
                String left = r + "_" + (c - 1);
                String right = r + "_" + (c + 1);
                union(up, key);
                union(down, key);
                union(left, key);
                union(right, key);
            }
            return sets;
        }

    }

}
