package linklist;

/**
 * @author Murphy Xu
 * @create 2021-08-11 21:57
 */
public class OddEvenList {
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
}
