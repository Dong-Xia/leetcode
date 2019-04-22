package xia.ncut;

import xia.ncut.entity.ListNode;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class SortList148 {
    /**
     * 归并排序： 使用快慢指针
     *
     * 看题目有两个要求：1）时间复杂度为O（nlogn）;2）空间复杂度为常数，即不能增设额外的空间。
     * 满足这样要求的排序算法，我们首先想到快排，合并排序和堆排序。我们来分析下几种排序算法对时间和空间复杂度的要求，堆排序实现上过于繁琐，我们不做考虑。快排的最坏的时间复杂度是O(n^2)，平均复杂度为O(nlgn)，如果按照题目的严格要求显然快排是不满足的，而且快排的实现引入了递归操作，递归调用的栈空间严格意义上说也是额外空间。另外值得注意的一点是：链表不像数组一样，可以随机访问元素，链表必须顺序访问，所以一般的递归操作很难实现，虽然也可以实现哈，见该文：递归实现链表排序。对于归并排序，我们知道需要O(n)的空间复杂度，即需要一个临时数组来存放排好序的元素，显然也合理，但那是针对的是数组，对于链表，归并排序的空间复杂度为in-place sort，即不需要额外空间就可以完成。另外，归并排序还有一个比较好的优势是其稳定性。所以，对于本题的解法，我们首选归并排序。
     *
     * 归并排序有多种方式，总的来说有三种，1）递归；2）非递归；3）自然合并；详见本文：归并排序的三种实现方法。对于链表，采用非递归的方式更为高效，用以下的一幅图来说明非递归的方式：
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode pre = null, slow = head, fast = head;
        //使用快慢指针
        while(fast!=null && fast.next!=null)
        {
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        // 分段
        pre.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(slow);
        return merge(left,right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummyHead = new ListNode(-1);
        ListNode p = dummyHead;
        while(left!=null && right!=null)
        {
            if(left.val < right.val)
            {
                p.next = new ListNode(left.val);
                p = p.next;
                left = left.next;
            }
            else
            {
                p.next = new ListNode(right.val);
                p = p.next;
                right = right.next;
            }
        }
        if(left!=null)
        {
            p.next = left;
        }
        if(right!=null)
        {
            p.next = right;
        }


        return dummyHead.next;
    }
}
