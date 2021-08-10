package linklist;

import java.util.List;

/**
 * @author Murphy Xu
 * @create 2021-08-10 15:41
 */
public class AddTwoNumbers {
    /**
     * 这种重复代码太多，不如不要一个加到另一个上，而是新开辟一个链
     * 2ms, 98.02%; 38.9MB, 22.93%
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int flag = 0; // 标记进位
        ListNode p1 = l1;
        ListNode p2 = l2;

        // 往l1上加
        while (p1 != null && p2 != null) {

            p1.val = p1.val + p2.val + flag;

            flag = 0;

            if (p1.val > 9) {
                flag = 1;
                p1.val = p1.val % 10;
            }

            l2 = p1;
            p1 = p1.next;
            p2 = p2.next;
        }
        if (p1 == null && p2 == null) {
            if (flag > 0) {
                l2.next = new ListNode(flag);
            }

        } else if (p1 == null) {
            l2.next = p2;
            while (p2 != null && flag != 0) {
                p2.val += flag;
                flag = 0;
                if (p2.val > 9) {
                    flag = 1;
                    p2.val = p2.val % 10;
                }
                l2 = p2;
                p2 = p2.next;
            }

            if (flag != 0) {
                l2.next = new ListNode(flag);
            }

        } else {

            while (flag != 0 && p1 != null) {
                p1.val += flag;
                flag = 0;
                if (p1.val > 9) {
                    flag = 1;
                    p1.val = p1.val % 10;
                }
                l2 = p1;
                p1 = p1.next;
            }
            if (flag != 0) {
                l2.next = new ListNode(flag);
            }
        }

        return l1;
    }

    /**
     * 稍作改进
     * 2ms, 98.02%; 38.9MB, 19.93%
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        ListNode cur = res;
        ListNode p1 = l1, p2 = l2;
        int flag = 0;

        while (p1 != null || p2 != null) {
            int v1, v2, vt;
            if (p1 == null) {    //  这个地方可以换成三位运算符
                v1 = 0;
            } else {
                v1 = p1.val;
            }

            if (p2 == null) {
                v2 = 0;
            } else {
                v2 = p2.val;
            }

            vt = v1 + v2 + flag;
            flag = 0;

            if (vt > 9) {
                flag = 1;
                vt %= 10;
            }

            cur.next = new ListNode(vt);
            cur = cur.next;
            if (p1 != null)
                p1 = p1.next;
            if (p2 != null)
                p2 = p2.next;
        }

        if (flag != 0) {
            cur.next = new ListNode(flag);
        }

        return res.next;
    }

    /**
     * 官方答案
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null){
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;

            int sum = n1 + n2 + carry;

            if (head == null) {
                head = tail = new ListNode(sum % 10);
            }else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null){
                l1 = l1.next;
            }
            if (l2 != null){
                l2 = l2.next;
            }


        }
        if (carry > 0){
            tail.next = new ListNode(carry);
        }
        return head;
    }
}
