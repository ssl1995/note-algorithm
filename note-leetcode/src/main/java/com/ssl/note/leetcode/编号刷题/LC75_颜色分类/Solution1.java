package com.ssl.note.leetcode.编号刷题.LC75_颜色分类;

import java.util.Arrays;

public class Solution1 {

  /**
   * 颜色分类：0-红，1-白，2-蓝色
   * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列
   * 输入：nums = [2,0,2,1,1,0]
   * 输出：[0,0,1,1,2,2]
   * 方法：计数排序
   */
  public void sortColors(int[] nums) {
    if (nums == null) {
      return;
    }
    int left = 0;
    int cur = 0;
    int right = nums.length - 1;

    while (cur <= right) {
      if (nums[cur] == 0) {
        // 交换到0区
        swap(nums, cur, left);
        left++;
        cur++;
      } else if (nums[cur] == 1) {
        // 中间是1区，不交换
        cur++;
      } else if (nums[cur] == 2) {
        // 交换到2区
        swap(nums, cur, right);
        right--;
      }
    }
  }

  private void swap(int[] nums, int i, int j) {
    if (i == j) {
      return;
    }
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {2, 0, 2, 1, 1, 0};
    solution.sortColors(nums);
    System.out.println(Arrays.toString(nums));
  }
}
