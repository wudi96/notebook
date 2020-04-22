package com.notebook.exercise;

/**
 * 环形链表 II 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回null
 *
 * @author luorigong
 */
public class CircleNodeListTwo {

  private ListNode getIntersect(ListNode head) {
    ListNode tortoise = head;
    ListNode hare = head;

    //一个快指针会在一个循环中遇到慢指针，或者在一个非循环链表的末尾到达“null”。
    while (hare != null && hare.next != null) {
      tortoise = tortoise.next;
      hare = hare.next.next;
      if (tortoise == hare) {
        return tortoise;
      }
    }

    return null;
  }

  public ListNode detectCycle(ListNode head) {
    if (head == null) {
      return null;
    }

    // 如果有一个循环，快/慢指针会相交于某个点，否则，就没有循环
    ListNode intersect = getIntersect(head);
    if (intersect == null) {
      return null;
    }

    //两个指针以同样的速度遍历——一个从前面来，另一个从前面来，找到交点
    ListNode ptr1 = head;
    ListNode ptr2 = intersect;
    while (ptr1 != ptr2) {
      ptr1 = ptr1.next;
      ptr2 = ptr2.next;
    }

    return ptr1;
  }
}
