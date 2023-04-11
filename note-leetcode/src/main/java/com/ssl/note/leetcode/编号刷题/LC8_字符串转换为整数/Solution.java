package com.ssl.note.leetcode.编号刷题.LC8_字符串转换为整数;

/**
 * @Author: SongShengLin
 * @Date: 2022/09/05 12:44
 * @Describe:
 */
public class Solution {
    /**
     * 字符串转换为整数
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     */
    public int myAtoi(String s) {
        // 0."   "需要trim
        if (s == null || s.trim().length() == 0) {
            return 0;
        }
        char[] cs = s.trim().toCharArray();
        // 1.处理正负号
        // 默认正号
        int sign = 1;
        int index = 0;
        if (cs[0] == '-') {
            sign = -1;
            index = 1;
        } else if (cs[0] == '+') {
            index = 1;
        }
        // 2.遍历
        int num = 0;
        for (int i = index; i < cs.length; i++) {
            if (cs[i] < '0' || cs[i] > '9') {
                break;
            }
            // 3.是否越界，超过最大整型
            int temp = cs[i] - '0';
            // 回过头来，判断越界
            if (num > Integer.MAX_VALUE / 10 || temp > Integer.MAX_VALUE - num * 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            // 写到这一步，就要思考是否会越界
            num = num * 10 + temp;
        }

        return sign * num;
    }
}
