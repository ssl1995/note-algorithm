package com.ssl.note.leetcode.编号刷题.LC6_Z字形变换;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/09 15:02
 * @Describe:
 */
public class Solution {


    /**
     * LC6 Z字形变换
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     * 输入：s = "PAYPALISHIRING", numRows = 4
     * 输出："PINALSIGYAHRPI"
     * 解释：
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     */
    public String convert(String s, int numRows) {
        if (s == null || numRows < 2) {
            return s;
        }
        List<StringBuilder> resList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            resList.add(new StringBuilder());
        }

        int i = 0;
        int flag = -1;

        for (char c : s.toCharArray()) {
            resList.get(i).append(c);
            // 遇到边界就flag相反数，使得指针Z字形变换遍历
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }

            i += flag;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder row : resList) {
            res.append(row);
        }
        return res.toString();
    }
}
