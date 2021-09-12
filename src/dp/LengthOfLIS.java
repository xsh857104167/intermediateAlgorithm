package dp;

import org.junit.Test;

/**
 * @author Murphy Xu
 * @create 2021-08-11 20:23
 */
public class LengthOfLIS {
    /**
     * 动态规划
     * 68ms,50.56%; 38.3MB, 6.44%
     * 定义 dp[i] 为考虑前i个元素，以第i个数字结尾的最长上升子序列的长度.
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        int max = dp[0];
        for (int i = 1; i < len; i++) {
            int tmp = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {

                    if (dp[j] > tmp) {
                        tmp = dp[j];
                    }
                }
            }
            dp[i] = tmp + 1;
            max = Math.max(dp[i], max);
        }

        return max;
    }

    /**
     * 贪心 + 二分查找
     * 2ms, 99.94%; 38.2MB, 26.38%
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0;// 如果找不到说明所有的数都比nums[i]大，此时要更新d[l],所以这里将pos设为0
                while (l <= r){
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]){
                        pos = mid;
                        l = mid + 1;
                    }else{
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

    @Test
    public void test() {
        int[] nums = {7, 7, 7, 7, 7, 7, 7};
        System.out.println(lengthOfLIS2(nums));
    }

}
