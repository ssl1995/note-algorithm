package com.ssl.note.zuo.learn.C33_Index和AC自动机;

public class Code01_IndexTree {

    // 下标从1开始！
    public static class IndexTree {

        private int[] tree;
        private int N;

        // 0位置弃而不用！
        public IndexTree(int size) {
            N = size;
            tree = new int[N + 1];
        }

        /**
         * 1~index 累加和是多少？
         * 时间复杂度：O(logN)
         */
        public int sum(int index) {
            int ret = 0;
            while (index > 0) {
                ret += tree[index];
                index -= index & -index;
            }
            return ret;
        }

        /**
         * IndexTree单点更新：在index位置，增加d
         * 时间复杂度：O(logN)
         * 比前缀树来说，IndexTree推出二维，就特别简单。
         */
        public void add(int index, int d) {
            // 后续所有受影响的位置
            while (index <= N) {
                tree[index] += d;
                // index最右侧的1：index & -index = index & (~index+1)
                index += index & -index;
            }
        }
    }

    public static class Right {
        private int[] nums;
        private int N;

        public Right(int size) {
            N = size + 1;
            nums = new int[N + 1];
        }

        public int sum(int index) {
            int ret = 0;
            for (int i = 1; i <= index; i++) {
                ret += nums[i];
            }
            return ret;
        }

        public void add(int index, int d) {
            nums[index] += d;
        }

    }

    public static void main(String[] args) {
        int N = 100;
        int V = 100;
        int testTime = 2000000;
        IndexTree tree = new IndexTree(N);
        Right test = new Right(N);
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int index = (int) (Math.random() * N) + 1;
            if (Math.random() <= 0.5) {
                int add = (int) (Math.random() * V);
                tree.add(index, add);
                test.add(index, add);
            } else {
                if (tree.sum(index) != test.sum(index)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test finish");
    }

}
