package com.ssl.note.zuo.learn.C15_贪心算法下;

import java.util.HashSet;

public class Code01_Light {

    /**
     * 亮灯问题
     * 给定一个字符串，只能由x和.两种字符组成
     * X：表示墙，不能放灯，也不需要点亮
     * .：表示居名点，可以放灯，需要点亮
     * 如果灯放在i位置,可以让i-1,i和i+1三个位置点亮
     * 返回如果电量str中所有需要点亮的位置，至少需要几盏灯？
     */
    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    public static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) { // 结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') { // 当前位置是点的话
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else { // str还没结束
            // i X .
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    /**
     * 方法2：贪心
     */
    public static int minLight2(String road) {
        char[] str = road.toCharArray();
        int i = 0;
        int light = 0;
        while (i < str.length) {
            // 遇到x，就到下一个位置
            if (str[i] == 'X') {
                i++;
            } else {
                // 遇到i位置是.,判断i+1,i+2位置情况
                // 先亮灯，判断移动i位置
                light++;
                // 无i位置
                if (i + 1 == str.length) {
                    break;
                } else {
                    // 有i位置
                    // i+ 1是X，i的.必须亮灯，到i+2讨论
                    if (str[i + 1] == 'X') {
                        i = i + 2;
                    } else {
                        // i+1是.，i+2是x或.，都让i+1亮灯，到i+3讨论
                        i = i + 3;
                    }
                }
            }
        }
        return light;
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}
