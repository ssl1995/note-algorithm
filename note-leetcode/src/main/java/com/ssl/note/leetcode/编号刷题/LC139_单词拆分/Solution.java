package com.ssl.note.leetcode.编号刷题.LC139_单词拆分;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author SongShengLin
 * @date 2022/2/21 11:11 PM
 * @description
 */
public class Solution {
  /**
   * 单词拆分
   * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
   * 输出: true
   * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
   * 注意，你可以重复使用字典中的单词。
   * 1 <= s.length <= 300
   * 1 <= wordDict.length <= 1000
   * 1 <= wordDict[i].length <= 20
   * s 和 wordDict[i] 仅由小写英文字母组成
   * wordDict 中的所有字符串 互不相同
   */
  public boolean wordBreak(String s, List<String> wordDict) {
    int n = s.length();
    boolean[] dp = new boolean[n + 1];
    dp[0] = true;

    // 优化查询效率
    Set<String> set = new HashSet<>(wordDict);

    for (int i = 1; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        boolean isTrue = dp[j] && isContains(set, s.substring(j, i));
        // 前i个字符串是否包含在字典中，一旦有一个满足条件就要跳出内层循环
        // 如果不跳过，后续的字符串可能会覆盖前面的结果
        if (isTrue) {
          dp[i] = true;
          break;
        }
      }
    }

    return dp[n];
  }

  private boolean isContains(Set<String> set, String subStr) {
    return set.contains(subStr);
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String s = "leetcode";
    List<String> wordDict = new ArrayList<>();
    wordDict.add("leet");
    wordDict.add("code");

    System.out.println(wordDict.contains("leet"));

    System.out.println(solution.wordBreak(s, wordDict));

  }
}
