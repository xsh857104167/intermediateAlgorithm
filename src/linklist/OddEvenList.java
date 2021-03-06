package linklist;

import java.util.List;

/**
 * @author Murphy Xu
 * @create 2021-08-11 21:57
 */
public class OddEvenList {

    /**
     * 给自己都搞晕了，这也能过
     * 13ms, 5.96%; 38.1MB, 53.96%
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode odd = head;  // 奇数要插入odd.next
        ListNode cur = head;
        int count = 1;
        while (cur != null){
            if (count == 1){
                cur = cur.next;
                count ++;
                continue;
            }
            if (count % 2 == 1){
                ListNode tmp = odd.next;
                odd.next = cur;
                odd = odd.next;

                ListNode tmp2 = cur.next;
                cur.next = tmp;


                while (tmp.next != cur){
                    tmp = tmp.next;
                }
                tmp.next = tmp2;

                cur = tmp2;
                count ++;
            }else{
                cur = cur.next;
                count ++;
            }
        }

        return head;
    }

    /**
     * 分离节点后合并
     * 0ms, 100%; 38.1MB, 57.79%
     * @param head
     * @return
     */
    public ListNode oddEvenList2(ListNode head) {
        if (head == null){
            return head;
        }

        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
