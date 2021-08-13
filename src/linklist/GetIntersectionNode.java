package linklist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Murphy Xu
 * @create 2021-08-13 16:43
 */
public class GetIntersectionNode {
    /**
     * 最简单的思路，时间复杂度O(n^2), 空间复杂度O(n)
     * 如果把List改成Set时间复杂度就是 O(n + m)了
     * 870ms, 5.02%; 41.3MB, 31.40%
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode p = headA;
        List<ListNode> list = new ArrayList<>();
        while (p != null){
            list.add(p);
            p = p.next;
        }
        p = headB;
        while (p != null){
            if (list.contains(p)){
                return p;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * 双指针
     * 这个双指针的原理根本在于两条链虽然不一样长，但是两条链的和一样长
     * 1ms, 100%; 40.9MB, 85.78%
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null){
            return null;
        }
        ListNode pA = headA, pB = headB;
         while (pA != pB){
             pA = pA == null ? headB : pA.next;
             pB = pB == null ? headA : pB.next;
         }
         return pA;
    }
}
