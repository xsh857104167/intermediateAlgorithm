package arrayAndString;

import org.junit.Test;

/**
 * @author Murphy Xu
 * @create 2021-08-13 15:53
 */
public class IncreasingTriplet {

    /**
     * 暴力法，O(n^3) 超时75/76
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        if (len < 3)
            return false;

        for (int i = 0; i < len -2; i++) {
            for (int j = i+1; j < len-1; j++) {
                if (nums[i] < nums[j]){
                    for (int k = j+1; k < len; k++) {
                        if (nums[j] < nums[k])
                            return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 我们考虑下，是不是递减的数组必定为false，
     * 最长递增子序列长度大于等于3一定为true
     * O(n^2) 还是超时 75/76
     * @param nums
     * @return
     */
    public boolean increasingTriplet2(int[] nums) {
        int len = nums.length;
        if (len < 3)
            return false;
        int[] dp = new int[len];
        dp[0] = 1;
        int max = dp[0];
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        if (max < 3){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 贪心O(n)
     * 2ms, 82.07%; 78.9MB, 65.09%
     * @param nums
     * @return
     */
    public boolean increasingTriplet3(int[] nums) {
       int min = Integer.MAX_VALUE;
       int sec = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= min){
                min = nums[i];
            }else if (nums[i] <= sec){
                sec = nums[i];
            }else{
                return true;
            }
        }
        return false;
    }

    @Test
    public void test(){
        int[] nums = {2,1,5,0,4,6};
        System.out.println(increasingTriplet2(nums));
    }
}
