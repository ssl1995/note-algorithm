package com.ssl.note.leetcode.编号刷题.LC160_相交链表;

import com.ssl.note.leetcode.utils.ListNode;

/**
 * @author SongShengLin
 * @date 2022/6/18 16:29
 * @description
 */
public class Solution1 {

  /**
   * 相交链表
   * 最优解：
   * A走完，走B的；B走完，走A的，
   * 因为总路程相同，相交点相同，没有相交点，都返回null
   */
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return headA == null ? headB : headA;
    }

    ListNode cur1 = headA;
    ListNode cur2 = headB;
    while (cur1 != cur2) {
      cur1 = cur1 == null ? headB : cur1.next;
      cur2 = cur2 == null ? headA : cur2.next;
    }
    return cur1;
  }
}
