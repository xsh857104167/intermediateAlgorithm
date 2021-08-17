package backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Murphy Xu
 * @create 2021-08-17 13:43
 */
public class Permute {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    /**
     * 0ms,100%； 38.6 MB, 61.66%
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        for (int num: nums) {
            path.add(num);
        }
        int n = nums.length;
        backtracking(n, 0);
        return res;
    }

    public void backtracking(int n, int first){
        if (first == n){
            res.add(new ArrayList<>(path));
        }
        for (int i = first; i < n; i++) {
            Collections.swap(path, first, i);
            backtracking(n, first + 1);
            Collections.swap(path, first, i);
        }
    }


    @Test
    public void test(){
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }
}
