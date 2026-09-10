package com.ssl.note.leetcode.编号刷题.LC84_柱状图中最大矩形;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author SongShengLin
 * @date 2022/1/29 3:29 PM
 * @description
 */
public class Solution {

  /**
   * 柱状图中最大矩形
   * 输入：heights = [2,1,5,6,2,3]
   * 输出：10
   * 解释：最大的矩形为图中红色区域，面积为 10
   * <p>
   * 【核心思路】
   * 对于每根柱子 heights[i]，以它为高度能扩展出的最大矩形面积 = heights[i] × (右边界 - 左边界 - 1)
   * - 左边界：左边第一根比它矮的柱子的位置
   * - 右边界：右边第一根比它矮的柱子的位置
   * <p>
   * 【为什么用单调递增栈？】
   * 单调递增栈能在 O(n) 时间内，帮每根柱子同时找到左右边界！
   * - 栈中保存的是柱子的索引，且对应的高度从栈底到栈顶单调递增
   * - 当遇到一根更矮的柱子时，栈顶柱子的【右边界】就确定了（就是当前位置）
   * - 栈顶柱子弹出后，新的栈顶就是它的【左边界】
   * <p>
   * 【示例演示】heights = [2, 1, 5, 6, 2, 3]
   * 索引:   0   1   2   3   4   5
   * 高度:   2   1   5   6   2   3
   * <p>
   * 步骤 | right | 操作                    | 栈(底→顶) | 弹出计算
   * -----|-------|------------------------|-----------|---------------------------
   * 1   |   0   | 直接入栈               | [0]       | -
   * 2   |   1   | 1<2, 弹出0             | []        | cur=0,left=-1,right=1
   * |       |                        |           | 面积=(1-(-1)-1)×2=2
   * 3   |   1   | 入栈1                  | [1]       | -
   * 4   |   2   | 5>1, 直接入栈          | [1,2]     | -
   * 5   |   3   | 6>5, 直接入栈          | [1,2,3]   | -
   * 6   |   4   | 2<6, 弹出3             | [1,2]     | cur=3,left=2,right=4
   * |       |                        |           | 面积=(4-2-1)×6=6
   * 7   |   4   | 2<5, 弹出2             | [1]       | cur=2,left=1,right=4
   * |       |                        |           | 面积=(4-1-1)×5=10 ✅最大
   * 8   |   4   | 2>1, 入栈              | [1,4]     | -
   * 9   |   5   | 3>2, 直接入栈          | [1,4,5]   | -
   * 10  | 结束  | 弹出5                  | [1,4]     | cur=5,left=4,right=6
   * |       |                        |           | 面积=(6-4-1)×3=3
   * 11  | 结束  | 弹出4                  | [1]       | cur=4,left=1,right=6
   * |       |                        |           | 面积=(6-1-1)×2=8
   * 12  | 结束  | 弹出1                  | []        | cur=1,left=-1,right=6
   * |       |                        |           | 面积=(6-(-1)-1)×1=6
   * <p>
   * 最大面积 = 10（索引2和3组成的矩形：高度5，宽度2）
   */
  public int largestRectangleArea(int[] heights) {
    if (heights == null || heights.length == 0) {
      return 0;
    }
    int maxArea = 0;
    Deque<Integer> stack = new ArrayDeque<>();
    int n = heights.length;
    // 对于每根柱子，以它为高的最大面积，是找到左边第一个比它矮的和右边第一个比它矮的
    // 面积= (r-l+1-2) * h[cur]
    // 所以思考每个柱子前面的数和它自己，是单调递增的，所以是单调递增栈
    for (int i = 0; i < n; i++) {
      // < 也行，最后会到外边的while处理
      while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
        int cur = stack.pop();
        int l = stack.isEmpty() ? -1 : stack.peek();
        int r = i;
        int curArea = (r - l - 1) * heights[cur];

        maxArea = Math.max(maxArea, curArea);
      }
      stack.push(i);
    }
    // 处理栈中剩余元素：这些元素的右边没有比它们更矮的柱子了
    // 此时右边界看成数组长度n
    while (!stack.isEmpty()) {
      int cur = stack.pop();
      int l = stack.isEmpty() ? -1 : stack.peek();
      int r = n;
      int curArea = (r - l - 1) * heights[cur];

      maxArea = Math.max(maxArea, curArea);
    }
    return maxArea;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] heights = {1, 2, 3};
    System.out.println(solution.largestRectangleArea(heights));
  }
}
