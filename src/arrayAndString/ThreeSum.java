package arrayAndString;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Murphy Xu
 * @create 2021-08-06 20:19
 */
public class ThreeSum {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    /**
     * 可不可以用回溯
     * 可以用回溯，315/318个用例超时，剪枝之后也超
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3 || nums == null)
            return res;

        backtracking(nums, 3, 0);

        return res;
    }

    public void backtracking(int[] nums, int k, int cur){
        if (path.size() == k){
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += path.get(i);
            }
            if (sum == 0){
                List<Integer> tmp = new ArrayList<>(path);
                Collections.sort(tmp);
                if (!res.contains(tmp)){
                    res.add(tmp);
                }

            }
            return;
        }

        for (int i = cur; i < nums.length - (k - path.size()) + 1; i++) {  // 可以剪枝
            path.add(nums[i]);
            backtracking(nums, k, i + 1);
            path.remove(path.size() -1);
        }

    }

    @Test
    public void test(){
        int[] nums = {-1,0,1,2, -1, -4};
        System.out.println(threeSum(nums));
    }
}
