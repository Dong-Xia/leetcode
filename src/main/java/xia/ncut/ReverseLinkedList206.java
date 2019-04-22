package xia.ncut;

import xia.ncut.entity.ListNode;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class ReverseLinkedList206 {
    /**
     * 思路：链表一般都是按照循环移动指针，迭代实现
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        //定义一个移动的指针（遍历到最后，指向最后一个节点）
        ListNode reverse = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = reverse;
            reverse = head;
            head = temp;
        }
        return reverse;
    }

    /**
     * 递归实现：也就是将循环变为递归
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        if(head == null ||head.next == null) {
            return head;
        }
        ListNode prev = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return prev;
    }

    public static void main(String[] args) {
        ListNode aa = new ListNode(1);
        ListNode aa1 = new ListNode(2);
        ListNode aa2 = new ListNode(3);
        ListNode aa3 = new ListNode(4);
        ListNode aa4 = new ListNode(5);
        aa.next = aa1;
        aa1.next = aa2;
        aa2.next = aa3;
        aa3.next = aa4;
        new ReverseLinkedList206().ReverseList(aa);
    }
}
