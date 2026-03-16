package com.ssl.note.leetcode.编号刷题.LC215_数组中的第K大元素;

import java.util.Random;

/**
 * @author SongShengLin
 * @date 2022/6/18 18:35
 * @description
 */
public class Solution {

  /**
   * 数组中第k大的数
   * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
   * 输出: 4
   */
  public int findKthLargest(int[] nums, int k) {
    int t = nums.length - k;
    int left = 0;
    int right = nums.length - 1;

    int pivot = partition(nums, left, right);
    // 快速排序 => 修改为类似二分的思想去快速选择
    while (pivot != t) {
      if (pivot < t) {
        left = pivot + 1;
      } else {
        right = pivot - 1;
      }

      pivot = partition(nums, left, right);
    }

    return nums[pivot];
  }

  // 快速排序，<pivot的放左边，>=放右边
  private int partition(int[] nums, int left, int right) {
    // [left,right]找一个随机值
    // 防止顺序/倒叙数组造成的极端情况，每次都随机交换pivot和最后一位的元素
    int random = new Random().nextInt(right - left + 1) + left;
    swap(nums, random, right);

    // p1 始终指向最后一个 小于pivot 的元素的位置
    int p1 = left - 1;
    // p2 遍历指针
    int p2 = left;

    while (p2 <= right) {
      if (nums[p2] < nums[right]) {
        p1++;
        swap(nums, p1, p2);
      }
      p2++;
    }

    // p1+1 是pivot的正确位置,交换返回
    p1++;
    swap(nums, p1, right);
    return p1;
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
//    int[] nums = {3, 2, 1, 5, 6, 4};
//    int k = 2;
//    Solution solution = new Solution();
//    System.out.println(solution.findKthLargest(nums, k));
    System.out.println(new Random().nextInt(10 - 1 + 1));
    System.out.println(new Random().nextInt(10 - 1 + 1) + 1);

  }
}
