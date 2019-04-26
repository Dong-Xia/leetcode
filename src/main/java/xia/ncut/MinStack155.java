package xia.ncut;

/**
 * 最小栈：
 *
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
public class MinStack155 {
        // 解题思路：使用链表存储结构，并用一个变量记录最小值
        private ListNode head = null;      // 存储结构
        private int min = 0;            // 最小元素

        /** initialize your data structure here. */
        public MinStack155() {

        }

        public void push(int x) {
            ListNode node = new ListNode(x);
            if (head == null) {
                head = node;
                min = head.val;
            }else{
                // 栈为先进后出
                node.next = head;
                head = node;
                if (head.val < min) {
                    min = head.val;
                }
            }
        }

        public void pop() {
            // 需判断弹出的是否为最小的那个元素
            if (head != null) {
                int temp = head.val;
                head = head.next;
                if (temp == min) {
                    // 不能直接使用head, 会影响指针的指向
                    ListNode node = head;
                    // 如果弹出的元素为最小的那个，则需要重新遍历剩下的元素得到最小值
                    if (node != null) {
                        min = node.val;
                        node = node.next;
                        while (node != null) {
                            if (node.val < min) {
                                min = node.val;
                            }
                            node = node.next;
                        }
                    } else {
                        min = 0;
                    }
                }
            }
        }

        public int top() {
            return head == null ? -1 : head.val;
        }

        public int getMin() {
            return min;
        }
}
