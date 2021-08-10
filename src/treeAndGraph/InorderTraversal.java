package treeAndGraph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Murphy Xu
 * @create 2021-08-10 17:21
 */
public class InorderTraversal {

    /**
     * 非递归
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }

        return res;
    }
}
