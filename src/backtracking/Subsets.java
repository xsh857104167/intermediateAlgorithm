package backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy Xu
 * @create 2021-08-17 15:14
 */
public class Subsets {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    /**
     * 0ms, 100%; 38.9 MB, 10.10%
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        res.add(path);
        for (int k = 1; k <= nums.length; k++) {
            backtracking(nums, k, 0);
        }
        return res;
    }

    public void backtracking(int[] nums, int k, int cur) {
        if (path.size() == k){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = cur; i < nums.length; i++) {
            path.add(nums[i]);
            backtracking(nums, k, i + 1);
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void test(){
        int[] nums = {1};
        System.out.println(subsets(nums));
    }

}
