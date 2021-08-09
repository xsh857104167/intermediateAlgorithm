package arrayAndString;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
     * 其实回溯也就是暴力法
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3)
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

    /**
     * 排序 + 双指针
     * 24ms, 56.81%; 42.5MB, 34.78%
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3)
            return res;

        Arrays.sort(nums); // 排序

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                break;
            if (i > 0 && (nums[i] == nums[i-1]))
                continue;

            int left = i + 1;
            int right = nums.length-1;


            while(left < right){
                if (nums[i] + nums[left] + nums[right] > 0){
                    --right;
                }else if (nums[i] + nums[left] + nums[right] < 0){
                    ++left;
                }else{
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);

                    --right;
                    ++left;

                    // 跳过重复的
                    while(left < right && nums[left] == nums[left-1]){
                        left++;
                    }
                    while(left < right && nums[right+1] == nums[right]){
                        right--;
                    }

                }
            }

        }

        return res;

    }

    /**
     * 官方答案
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum3(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        // 枚举a
        for (int first = 0; first < n; first++) {
            // 需要和上次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1])
                continue;
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举b
            for (int second = first + 1; second < n; ++second){
                // 需要和上次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second -1])
                    continue;
                // 需要保证b的指针在c的指针的左侧
                while (second < third && nums[second] + nums[third] > target){
                    --third;
                }
                // 如果指针重合，随着b后续的增加
                // 就不会有满足，可以退出循环
                if (second == third){
                    break;
                }
                if (nums[second] + nums[third] == target){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    @Test
    public void test(){
        int[] nums = {-1,0,1,2, -1, -4};
        System.out.println(threeSum2(nums));
    }
}
