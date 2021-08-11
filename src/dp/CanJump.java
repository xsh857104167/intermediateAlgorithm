package dp;

import org.junit.Test;

/**
 * @author Murphy Xu
 * @create 2021-08-11 15:22
 */
public class CanJump {
    /**
     * 动态规划，超时 166/166
     * 稍微加了一下break就不会超时了
     * 108ms, 13.81%; 39.5MB, 80.04%
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;  // 初始值
        for (int i = 1; i <nums.length ; i++) {
            for (int j = i-1; j >=0 ; j--) {
                boolean tmp = dp[j] && (nums[j]>= i-j);
                if (!dp[i]){
                    dp[i] = dp[i] || tmp;
                }else{
                    break;
                }
            }
        }

        return dp[nums.length-1];
    }

    /**
     * 同一思路的另一种写法
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums){
        int len = nums.length;
        if (len <= 1)
            return true;

        boolean[] dp = new boolean[len];

        dp[0] = true;

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && j + nums[j] >= i){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len -1];
    }

    /**
     * 贪心算法
     * 2ms, 95.58%; 39.9MB, 46.11%
     * @param nums
     * @return
     */
    public boolean canJump3(int[] nums){
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; i++) {
            if (i <= rightmost){
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n-1){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 换一种动态规划思路
     * dp[i]表示在下标为i处，可以最多往后跳几步。
     * 3ms, 51.50%; 39.4MB, 88.88%
     * @param nums
     * @return
     */
    public boolean canJump4(int[] nums){
        if (nums.length == 1){
            return true;
        }
        if (nums[0] == 0){
            return false;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1] -1, nums[i]);

            if (dp[i] + i + 1 >= nums.length)
                return true;
            else if (dp[i] == 0)
                return false;
        }
        return false;
    }

    boolean flag = false;
    /**
     * dfs
     * 好惨，超时 151/166
     * @param nums
     * @return
     */
    public boolean canJump5(int[] nums){
        int len = nums.length;
        if (len <= 1)
            return true;
        boolean[] visited = new boolean[len];
        dfs(nums, 0, len, visited);
        return flag;
    }

    public void dfs(int[] nums, int k, int len, boolean[] visited){
        if (k >= len - 1){
            flag = true;
            return;
        }
        if (visited[k] || nums[k] == 0)
            return;
        visited[k] = true;
        for (int i = 1; i <= nums[k]; i++) {
            dfs(nums, i + k, len, visited);
        }
    }

    @Test
    public void test(){
        int[] nums = {3, 2, 1, 0, 4};
        System.out.println(canJump(nums));
    }
}
