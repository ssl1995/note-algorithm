package com.ssl.note.leetcode.编号刷题.LC4_寻找两个正序数组的中位数;

public class Solution {

  /**
   * 寻找两个正序数组中的中位数
   * 使用合并数组法，时间复杂度 O(m+n)
   * 解题步骤：
   * 1. 合并两个有序数组为一个新的有序数组
   * 2. 根据合并后数组的长度计算中位数
   * 示例1：
   * 输入：nums1 = [1,2], nums2 = [3,4]
   * 合并后：[1,2,3,4]
   * 总长度4为偶数，中位数 = (2+3)/2 = 2.5
   * 示例2：
   * 输入：nums1 = [1,3], nums2 = [2]
   * 合并后：[1,2,3]
   * 总长度3为奇数，中位数 = 2
   * 时间复杂度：O(m+n)，需要遍历两个数组
   * 空间复杂度：O(m+n)，需要额外空间存储合并后的数组
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n1 = nums1.length;
    int n2 = nums2.length;
    // 合并成一个nums数组
    int[] nums = new int[n1 + n2];
    int i = 0, j = 0, k = 0;
    while (i < n1 && j < n2) {
      if (nums1[i] < nums2[j]) {
        nums[k++] = nums1[i++];
      } else {
        nums[k++] = nums2[j++];
      }
    }

    while (i < n1) {
      nums[k++] = nums1[i++];
    }

    while (j < n2) {
      nums[k++] = nums2[j++];
    }
    // 找中位数
    int size = n1 + n2;
    boolean isEven = (size & 1) == 0;
    // 偶数
    if (isEven) {
      return (double) (nums[(size - 1) / 2] + nums[size / 2]) / 2;
    }
    // 奇数
    return(double) nums[size / 2];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums1 = {4};
    int[] nums2 = {1, 2, 3, 5};
    System.out.println(solution.findMedianSortedArrays(nums1, nums2));
  }


}
