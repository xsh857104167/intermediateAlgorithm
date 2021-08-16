package treeAndGraph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Murphy Xu
 * @create 2021-08-16 20:36
 */
public class FillNext {
    /**
     * 广度优先 O(N), O(N)
     * 3ms,41.57%;38.8Mb, 23.75%
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null)
            return null;
        Deque<Node> queue = new LinkedList<>();
        queue.offer(root);
        Node p = null;
        while (!queue.isEmpty()){
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                p = queue.poll();
                if (i != len-1){
                    p.next = queue.peek();
                }
                if (p.left != null){
                    queue.offer(p.left);
                }
                if (p.right != null){
                    queue.offer(p.right);
                }
            }
            p.next = null;
        }

        return root;
    }

    /**
     * 使用已建立的next指针
     * O(N), O(1)
     * 0ms,100%; 38.7MB,36.32%
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if (root == null){
            return null;
        }
        // 从根节点开始
        Node leftmost = root;
        while (leftmost.left != null){
            // 遍历这一层节点组成的链表，为下一层的节点更新next指针
            Node head = leftmost;

            while (head != null){
                head.left.next = head.right;

                if (head.next != null){
                    head.right.next = head.next.left;
                }

                // 指针后移
                head = head.next;
            }
            // 去下一层的最左侧节点
            leftmost = leftmost.left;
        }
        return root;
    }
}
