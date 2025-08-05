package com.ssl.note.leetcode.代码随想录.A01.数组;

public class LC27 {

  public int removeElement(int[] nums, int val) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    // 暴力法是2层for遍历，所以思考使用快慢指针解决第二层for遍历的成本
    int fast = 0;// 快指针，找
    int low = 0;// 慢指针：1、记录变化数；2、交换
    while (fast <= nums.length - 1) {
      // 不等于，移动慢指针并且赋值
      if (nums[fast] != val) {
        nums[low] = nums[fast];
        low++;
      }
      fast++;
    }
    return low;
  }

  public static void main(String[] args) {
    int[] nums = new int[]{0, 1, 2, 3, 3, 0, 4, 2};
    int res = new LC27().removeElement(nums, 2);
    System.out.println(res);
  }
}
