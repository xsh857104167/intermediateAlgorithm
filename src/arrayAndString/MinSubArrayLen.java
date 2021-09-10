package arrayAndString;

import org.junit.Test;

/**
 * 补充Leetcode,209. 长度最小的子数组
 * @author Murphy Xu
 * @create 2021-09-10 9:51
 */
public class MinSubArrayLen {
    /**
     * 双指针，滑动窗口
     * 2ms, 51.61%; 38.3Mb, 79.31%
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        // 避免错误输入导致空指针异常
        if (nums == null || nums.length == 0){
            return 0;
        }
        int length = nums.length;
        int right = -1;
        int left = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE; // 结果

        while (right + 1 < length){
            while (right + 1 < length && sum < target){
                right++;
                sum += nums[right];
            }
            if (sum >= target) {
                res = Math.min(res, right - left + 1);
            }
            while (left <= right && sum >= target){
                sum -= nums[left];
                left++;

                if (sum >= target){
                    res = Math.min(res, right - left + 1);
                }
            }

        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    @Test
    public void test(){
        int[] nums = {2,3,1,1,1,1,1};
        System.out.println(minSubArrayLen(12, nums));
    }
}
