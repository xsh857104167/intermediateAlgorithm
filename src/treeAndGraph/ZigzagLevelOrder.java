package treeAndGraph;

import java.util.*;

/**
 * @author Murphy Xu
 * @create 2021-08-13 17:37
 */
public class ZigzagLevelOrder {
    /**
     *
     * 1ms,96.53%;38.4 MB, 68.83%
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
        boolean flag = false;  // 表示正向
        while(!queue.isEmpty()){
            int length = queue.size();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                if(flag){  // 逆向
                    p = queue.pollLast();
                    tmp.add(p.val);
                    if (p.right != null){
                        queue.offerFirst(p.right);
                    }
                    if (p.left != null){
                        queue.offerFirst(p.left);
                    }

                }else{
                    p = queue.pollFirst();
                    tmp.add(p.val);
                    if (p.left != null){
                        queue.addLast(p.left);
                    }
                    if (p.right != null){
                        queue.addLast(p.right);
                    }
                }
            }
            res.add(tmp);
            flag = !flag;
        }
        return res;
    }
}
