package com.ssl.note.common.utils;

import java.util.Arrays;
import java.util.List;

public class RandomQuestion {


  public static void main(String[] args) {

    // 题池
    List<Integer> nums = Arrays.asList(
        1, 49, 128, 283, 11, 15, 42, 3, 438, 76, 560, 239, 53, 56, 189, 238, 41, 73, 54, 48,
        240, 74, 79, 160, 206, 234, 141, 142, 21, 2, 19, 24, 25, 138, 148, 23, 146, 94, 104,
        226, 101, 543, 102, 108, 98, 230, 199, 114
    );

    int k = 2; // 想随机抽取k个（不重复）
    List<Integer> pick = RandomUtil.pickKByReservoir(nums, k);

    System.out.println("poolSize=" + nums.size());
    System.out.println("k=" + k);
    System.out.println("pick=" + pick);
  }
}
