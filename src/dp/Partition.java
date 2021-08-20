package dp;

/**
 * 补充：分割等和子集
 * @author Murphy Xu
 * @create 2021-08-20 16:46
 */
public class Partition {
    /**
     * dp
     * 19ms, 88.76%; 37.9MB, 63.29%
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        if (len < 2){
            return false;
        }
        int count = 0;
        for (int i = 0; i < len; i++){
            count += nums[i];
        }
        if (count % 2 == 1){
            return false;
        }
        count /= 2;
        int[] dp = new int[count + 1];
        for (int i = 0; i < len; i++){
            for (int j = count; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        if (dp[count] == count){
            return true;
        }

        return false;
    }
}
