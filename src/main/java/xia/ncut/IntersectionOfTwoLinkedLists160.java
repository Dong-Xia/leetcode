package xia.ncut;

/**
 * 编写一个程序，找到两个单链表相交的起始节点。
 */
public class IntersectionOfTwoLinkedLists160 {
    /**
     * 将长的链表移动长度差的距离(类似于将两个链表进行等长处理），然后同时移动两个链表，找到第一个相等的节点
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA==null||headB==null) return null;
        int lengthA=0,lengthB=0;
        ListNode pA=headA,pB=headB;
        // 分别求两个链表的长度
        while (pA!=null){
            lengthA++;
            pA=pA.next;
        }
        while (pB!=null){
            lengthB++;
            pB= pB.next;
        }
        // 抹平两个链表的长度差距（headA和headB作为起点后面的链表长度相同）
        for (int i=0;i<Math.abs(lengthA-lengthB);i++){
            if (lengthA>lengthB){
                headA=headA.next;
            }else {
                headB=headB.next;
            }
        }
        while (headA!=null){
            if (headA==headB) return headA;
            headA=headA.next;
            headB=headB.next;
        }
        return null;
    }
}
