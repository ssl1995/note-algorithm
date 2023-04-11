package com.ssl.note.leetcode.编号刷题.LC4_寻找两个正序数组的中位数;

/**
 * @author SongShengLin
 * @date 2022/1/19 8:39 AM
 * @description
 */
public class Solution {
    /**
     * 寻找两个正序数组中的中位数
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) == 0;
        if (nums1.length != 0 && nums2.length != 0) {
            if (even) {
                return (double) ((findKthNum(nums1, nums2, size / 2)) + (findKthNum(nums1, nums2, size / 2 + 1))) / 2d;
            } else {
                return findKthNum(nums1, nums2, size / 2 + 1);
            }
        } else if (nums1.length != 0) {
            if (even) {
                return (double) (nums1[(size - 1) / 2] + nums1[size / 2]) / 2;
            } else {
                return nums1[size / 2];
            }
        } else if (nums2.length != 0) {
            if (even) {
                return (double) (nums2[(size - 1) / 2] + nums2[size / 2]) / 2;
            } else {
                return nums2[size / 2];
            }
        } else {
            return 0;
        }
    }

    /**
     * 求2个有序数组合并后的第k个数
     *
     * @param arr1 有序数组,两个数组不一定等长
     * @param arr2 有序数组
     * @param k    合并后的第k个数，从1开始
     */
    private int findKthNum(int[] arr1, int[] arr2, int k) {
        // short:10个数
        // long:17个数
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;
        // 1<=k<=短数组长度s
        // 1<= k=5 <=10
        if (k <= s) {
            // 第k小的数在短数组内
            return getUpMedian(shorts, 0, k - 1, longs, 0, k - 1);
        }
        // 长数组长度l <k<=两个数组之和
        // 17 < k=23 < 27
        if (k > l) {
            // 由于剩下不可能的位置加起来只有k-1个,需要手动扣掉可能性位置中的2个
            // 扣掉：短数组可能性中的第一个和常数组末尾比较
            if (shorts[k - l - 1] >= longs[l - 1]) {
                return shorts[k - l - 1];
            }
            // 扣掉：长数组可能性中的第一个和断数组末尾比较
            if (longs[k - s - 1] >= shorts[s - 1]) {
                return longs[k - s - 1];
            }
            return getUpMedian(shorts, k - l, s - 1, longs, k - s, l - 1);
        }
        // 短数组长度<=k<=长数组长度
        // 短数组都有可能
        // 长数组中的可能大于短数组可能，需要手动扣掉一个数
        // 扣掉：长数组可能性第一个与短数组末尾比较
        if (shorts[s - 1] <= longs[k - s - 1]) {
            return longs[k - s - 1];
        }
        return getUpMedian(shorts, 0, s - 1, longs, k - s, k - 1);
    }


    /**
     * 两个有序、长度相同的数组中，求合并后的上中位数
     * 输入参数保证s1、e1和s2、e2长度相同
     */
    private int getUpMedian(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        // while模拟递归过程
        while (s1 < e1) {
            mid1 = s1 + (e1 - s1) / 2;
            mid2 = s2 + (e2 - s2) / 2;
            // 两个有序数组，中间值如果相同，上中位数就是它本身
            if (nums1[mid1] == nums2[mid2]) {
                return nums1[mid1];
            }
            if (((e1 - s1 + 1) & 1) == 0) {
                // 偶数长度
                // 第一个数组的中位数 > 第二个数组的中位数
                // nums1：1, 2 ,3 ,4
                // nums2：1',2',3',4'
                // 假设假设2>2',那么不可能的数就是1',2',3,4
                // 可能是中位数的位置:1,2,3',4'
                if (nums1[mid1] > nums2[mid2]) {
                    e1 = mid1;
                    s2 = mid2 + 1;
                } else {
                    e2 = mid2;
                    s1 = mid1 + 1;
                }
            } else {
                // 奇数长度
                // 第一个数组的中位数 > 第二个数组的中位数
                // nums1：1, 2 ,3 ,4, 5
                // nums2：1',2',3',4',5'
                // 假设假设3>3',那么不可能的数就是3,4,5,1',2',可能位置就是其他位置
                // 但是这时候递归调用数组不等长
                if (nums1[mid1] > nums2[mid2]) {
                    // 为了解决数组长度不等长，不能递归，手动验证3'和2的大小
                    if (nums2[mid2] >= nums1[mid1 - 1]) {
                        return nums2[mid2];
                    }
                    e1 = mid1 - 1;
                    // 如果没有返回，多淘汰一个mid2，数组就变等长了
                    s2 = mid2 + 1;
                } else {
                    // 手动验证
                    if (nums1[mid1] >= nums2[mid2 - 1]) {
                        return nums1[mid1];
                    }
                    e2 = mid2 - 1;
                    s1 = mid1 + 1;
                }
            }
        }
        // while结束，得到2个位置
        // 如果是偶数返回上中位数,奇数两者都行
        // 综合取min即可
        return Math.min(nums1[s1], nums2[s2]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {4};
        int[] nums2 = {1, 2, 3, 5};
        System.out.println(solution.findMedianSortedArrays(nums1, nums2));
    }


}
