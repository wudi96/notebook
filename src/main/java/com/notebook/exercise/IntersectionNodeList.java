package com.notebook.exercise;

/**
 * 相交链表 编写一个程序，找到两个单链表相交的起始节点
 *
 * @author luorigong
 */
public class IntersectionNodeList {

  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }
    ListNode pA = headA;
    ListNode pB = headB;
    while (pA != pB) {
      pA = pA.next;
      pB = pB.next;
      if (pA == null && pB == null) {
        return null;
      }
      if (pA == null) {
        pA = headB;
      }
      if (pB == null) {
        pB = headA;
      }
    }
    return pA;
  }
}
