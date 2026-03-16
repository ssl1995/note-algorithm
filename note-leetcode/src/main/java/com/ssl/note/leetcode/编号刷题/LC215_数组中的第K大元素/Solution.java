package com.ssl.note.leetcode.编号刷题.LC215_数组中的第K大元素;

import java.util.Random;

public class Solution {

  /**
   * 数组中第k大的数
   * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
   * 输出: 4
   */
  public int findKthLargest(int[] nums, int k) {
    // 计算目标位置：第k大元素在排序数组中的索引
    int target = nums.length - k;
    // 调用三路快速选择函数
    return quickSelect(nums, 0, nums.length - 1, target);
  }

  private int quickSelect(int[] nums, int left, int right, int target) {
    // 当区间只包含一个元素时，直接返回
    if (left == right) {
      return nums[left];
    }

    // 随机选择pivot，避免最坏情况
    int random = new Random().nextInt(right - left + 1) + left;
    swap(nums, random, right);

    // 三路分割的核心逻辑
    int pivot = nums[right];  // 选择最右元素作为pivot
    int lt = left - 1;        // 小于pivot的右边界（初始为left-1）
    int gt = right;           // 大于pivot的左边界（初始为right）
    int i = left;             // 当前遍历位置（从left开始）

    // 遍历过程：将数组分为三部分
    while (i < gt) {
      if (nums[i] < pivot) {
        // 当前元素小于pivot，放到左侧
        lt++;
        swap(nums, lt, i);
        i++;  // 继续下一个元素
      } else if (nums[i] > pivot) {
        // 当前元素大于pivot，放到右侧
        gt--;
        swap(nums, gt, i);
        // 注意：这里i不增加，因为交换过来的元素还没检查
      } else {
        // 当前元素等于pivot，保持在中间
        i++;
      }
    }
    // 将pivot放到正确的位置（大于pivot区间的最左侧）
    swap(nums, gt, right);

    // 根据target的位置决定搜索方向
    if (target <= lt) {
      // target在小于pivot的区间，在左半部分继续搜索
      return quickSelect(nums, left, lt, target);
    } else if (target >= gt) {
      // target在大于pivot的区间，在右半部分继续搜索
      return quickSelect(nums, gt, right, target);
    } else {
      // target在等于pivot的区间，直接返回该位置的元素
      return nums[target];
    }
  }

  private void swap(int[] nums, int i, int j) {
    // 避免自我交换
    if (i == j) {
      return;
    }
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    int[] nums = {3,2,3,1,2,4,5,5,6};
    int k = 4;
    Solution solution = new Solution();
    // 4
    System.out.println(solution.findKthLargest(nums, k));
  }
}
