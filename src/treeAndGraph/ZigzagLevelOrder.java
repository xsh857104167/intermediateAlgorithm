package treeAndGraph;

import java.util.*;

/**
 * @author Murphy Xu
 * @create 2021-08-13 17:37
 */
public class ZigzagLevelOrder {
    /**
     * 先按照广度优先放到结果集中，然后再把偶数的逆序
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        TreeNode p = root;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        while(!queue.isEmpty()){
//            queue
        }

        return res;
    }
}
