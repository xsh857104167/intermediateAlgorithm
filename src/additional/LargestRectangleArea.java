package additional;

import org.junit.Test;

import java.util.Stack;

/**
 * @author Murphy Xu
 * @create 2021-09-12 19:48
 */
public class LargestRectangleArea {
    /**
     * 单调栈
     * 46ms，16.96%；47.1 MB, 92.12%
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> mono_stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]){
                mono_stack.pop();
            }
            left[i] = mono_stack.isEmpty() ? -1 : mono_stack.peek();
            mono_stack.push(i);
        }

        mono_stack.clear();
        for (int i = n - 1; i >= 0; i--){
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]){
                mono_stack.pop();
            }
            right[i] = mono_stack.isEmpty() ? n : mono_stack.peek();
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    @Test
    public void test(){
        int[] heights = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights));
    }
}
