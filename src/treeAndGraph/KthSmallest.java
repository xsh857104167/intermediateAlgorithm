package treeAndGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Murphy Xu
 * @create 2021-08-16 21:10
 */
public class KthSmallest {
    /**
     * 非递归深度优先
     * 0ms, 100%; 37.9MB, 94.27%
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        TreeNode p = root;
        int count = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()){
            while (p != null){
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            count ++;
            if (count == k){
                break;
            }
            p = p.right;
        }
        return p.val;
    }

    /**
     * 递归
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest2(TreeNode root, int k) {
        ArrayList<Integer> nums = inorder(root, new ArrayList<>());
        return nums.get(k-1);
    }
    public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr){
        if (root == null)
            return arr;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
        return arr;
    }
}
