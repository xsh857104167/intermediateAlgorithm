package treeAndGraph;

import org.junit.Test;

import java.util.*;

/**
 * @author Murphy Xu
 * @create 2021-08-16 15:51
 */
public class BuildTree {
    /**
     * 递归
     * 14ms, 6.77%; 88.5 MB, 5.03%
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null){
            return null;
        }
        if (preorder.length == 1){
            return new TreeNode(preorder[0]);
        }

        TreeNode node = new TreeNode(preorder[0]);
        int index = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0])
                index = i;
        }
        node.left = buildTree(helper(preorder, 1, index + 1),
                helper(inorder, 0, index));
        node.right = buildTree(helper(preorder, index + 1, preorder.length),
                helper(inorder, index + 1, inorder.length));
        return node;
    }

    /**
     * 截取数组
     * @param original
     * @param from
     * @param to
     */
    private int[] helper(int[] original, int from, int to){
        if (from >= to){
            return null;
        }
        if (from >= original.length){
            return null;
        }
        if (to <= 0){
            return null;
        }
        int newLength = to - from;
        int[] copy = new int[newLength];
        System.arraycopy(original, from, copy, 0, newLength);
        System.out.println(Arrays.toString(copy));
        return copy;
    }

    private Map<Integer, Integer> indexMap;
    /**
     * 答案的递归
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造hashMap，帮助快速定位
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n-1, 0, n-1);
    }
    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left,
                                int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right){
            return null;
        }
        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder_root);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归构造左子树，并连接到根节点
        root.left = myBuildTree(preorder, inorder, preorder_left + 1,
                preorder_left + size_left_subtree, inorder_left, inorder_root -1);
        // 递归构造右子树，并连接到根节点
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1,
                preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    /**
     * 迭代，巧妙
     * 1ms, 99.60%; 38.2 MB, 89.73%
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]){
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]){
                    node = stack.pop();
                    inorderIndex ++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

    @Test
    public void test(){
        int[] pre = {3,9,20,15,7};
        int[] in = {9,3,15,20,7};
        buildTree(pre, in);
    }
}
