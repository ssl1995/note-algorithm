package com.ssl.note.practice.lc_top_100;

import com.ssl.note.common.utils.ListNode;

public class Practice {
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null) {
      return null;
    }
    if (lists.length == 0) {
      return null;
    }
    return mergeAll(lists, 0, lists.length - 1);
  }

  private ListNode mergeAll(ListNode[] lists, int l, int r) {
    if (l == r) {
      return lists[l];
    }
    if (l > r) {
      return null;
    }
    int mid = l + (r - l) / 2;
    ListNode left = mergeAll(lists, 0, mid);
    ListNode right = mergeAll(lists, mid + 1, r);

    return merge1(left, right);
  }

  private ListNode merge(ListNode left, ListNode right) {
    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;
    while (left != null && right != null) {
      if (left.val < right.val) {
        cur.next = left;
        left = left.next;
      } else {
        cur.next = right;
        right = right.next;
      }
      cur = cur.next;
    }
    cur.next = left == null ? right : left;
    return dummy.next;
  }

  private ListNode merge1(ListNode head1, ListNode head2) {
    if (head1 == null || head2 == null) {
      return head1 == null ? head2 : head1;
    }
    // 以下LC21_合并两个有序链表之和代码相同
    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;
    while (head1 != null && head2 != null) {
      if (head1.val < head2.val) {
        cur.next = head1;
        head1 = head1.next;
      } else {
        cur.next = head2;
        head2 = head2.next;
      }
      cur = cur.next;
    }
    cur.next = head1 == null ? head2 : head1;
    return dummy.next;
  }

  public static void main(String[] args) {
    Practice practice = new Practice();
    String s = "3[a]2[bc]";
    // aaabcbc
  }

}
