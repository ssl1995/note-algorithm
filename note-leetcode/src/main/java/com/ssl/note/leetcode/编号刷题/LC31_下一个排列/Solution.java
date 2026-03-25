package com.ssl.note.leetcode.编号刷题.LC31_下一个排列;

import java.util.Arrays;

public class Solution {

  /**
   * 下一个排列
   * 算法核心：找到比当前排列大的最小排列
   * 步骤解析：
   * 1. 找下降点：从右往左找第一个i，使得nums[i] < nums[i+1]
   *    - 意义：i是第一个可以调整的位置，右边的元素已降序，无法再增大
   * 2. 交换大值：从右往左找第一个j，使得nums[j] > nums[i]，交换两者
   *    - 意义：找到最小的可能的更大数来替换i位置，保证是"刚好大一点"
   * 3. 反转剩余：反转i+1到末尾的元素
   *    - 意义：交换后右边仍为降序，反转后变为升序，得到最小的可能排列
   * 示例：[4,5,2,6,3,1]
   * 1. 找下降点：i=2（2 < 6）
   * 2. 找交换点：j=4（3 > 2），交换后 [4,5,3,6,2,1]
   * 3. 反转剩余：反转[6,2,1]得到[1,2,6]，最终结果 [4,5,3,1,2,6]
   * 边界情况：
   * - 数组长度 < 2：直接返回
   * - 无下降点（如[3,2,1]）：反转整个数组得到最小排列
   */
  public void nextPermutation(int[] nums) {
    // 边界情况：数组长度小于2，无需处理
    if (nums.length < 2) {
      return;
    }
    
    // 步骤1：从右往左找下降点i
    // 下降点定义：第一个满足nums[i] < nums[i+1]的位置
    // 从倒数第二个元素开始往左找
    int i = nums.length - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
      // 当前元素大于等于下一个元素，继续往左找
      i--;
    }
    
    // 步骤2：如果找到下降点i，从右往左找第一个比nums[i]大的元素j
    if (i >= 0) {
      int j = nums.length - 1;
      while (j >= 0 && nums[j] <= nums[i]) {
        // 当前元素小于等于nums[i]，继续往左找
        j--;
      }
      // 交换nums[i]和nums[j]，增大i位置的值
      swap(nums, i, j);
    }
    
    // 步骤3：反转i+1到末尾的元素
    // 因为交换后，i+1到末尾仍为降序，反转后变为升序
    // 这样可以得到最小的可能的更大排列
    reverse(nums, i + 1, nums.length - 1);
  }

  /**
   * 交换数组中两个位置的元素
   */
  private void swap(int[] nums, int i, int j) {
    if (i == j) {
      return;
    }
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  /**
   * 反转数组中从start到end的元素
   */
  private void reverse(int[] nums, int start, int end) {
    int left = start;
    int right = end;
    while (left < right) {
      // 交换左右指针的元素
      swap(nums, left, right);
      // 左指针右移，右指针左移
      left++;
      right--;
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    
    // 测试用例1：正常情况
    int[] nums1 = {4, 5, 2, 6, 3, 1};
    solution.nextPermutation(nums1);
    System.out.println("Test 1: " + Arrays.toString(nums1)); // 期望：[4,5,3,1,2,6]
    
    // 测试用例2：最大排列
    int[] nums2 = {3, 2, 1};
    solution.nextPermutation(nums2);
    System.out.println("Test 2: " + Arrays.toString(nums2)); // 期望：[1,2,3]
    
    // 测试用例3：重复元素
    int[] nums3 = {1, 1, 5};
    solution.nextPermutation(nums3);
    System.out.println("Test 3: " + Arrays.toString(nums3)); // 期望：[1,5,1]
    
    // 测试用例4：最小排列
    int[] nums4 = {1, 2, 3};
    solution.nextPermutation(nums4);
    System.out.println("Test 4: " + Arrays.toString(nums4)); // 期望：[1,3,2]
  }
}
