package com.ssl.note.leetcode.编号刷题.LC234_回文链表;

import com.ssl.note.leetcode.utils.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/6/19 10:30
 * @description
 */
public class Solution1 {
  /**
   * 回文链表
   * 示例：
   * 输入：head = [1,2,2,1]
   * 输出：true
   * 最优解：时间复杂度 = O（n），空间复杂度=O（1）
   */
  public boolean isPalindrome(ListNode head) {
    if (head == null) {
      return true;
    }
    // 1、快慢指针，找中间节点
    ListNode slow = head;
    ListNode fast = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    // 奇数节点：slow指向正中间
    // 偶数节点：slow指向中间前一个数
    // -> slow的next就是后半部分的头节点
    ListNode midHead = slow.next;
    // 2、翻转头节点
    midHead = reverse(midHead);
    // 3、前后部分的链表进行比较
    ListNode cur1 = head;
    ListNode cur2 = midHead;
    boolean res = true;
    while (cur1 != null && cur2 != null) {
      if (cur1.val != cur2.val) {
        res = false;
        break;
      }
      cur1 = cur1.next;
      cur2 = cur2.next;
    }

    // (备选)如果不为了改变原链表，可以再翻转回去
//    midHead = reverse(midHead);
//    slow.next = midHead;

    return res;
  }

  private ListNode reverse(ListNode head) {
    if (head == null) {
      return head;
    }
    ListNode pre = null;
    ListNode cur = head;
    while (cur != null) {
      ListNode next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }
    return pre;
  }
}
