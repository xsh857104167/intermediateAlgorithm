package dp;

/**
 * 213打家劫舍II
 * @author Murphy Xu
 * @create 2021-09-11 20:32
 */
public class RobII {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1){
            return nums[0];
        }
        if (length == 2){
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));

    }

    private int robRange(int[] nums, int start, int end) {
        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }

        return second;
    }
}
