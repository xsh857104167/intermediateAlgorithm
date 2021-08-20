package dp;

/**
 * 补充题目：343.整数拆分
 * @author Murphy Xu
 * @create 2021-08-19 22:11
 */
public class IntegerBreak {
    /**
     * dp
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        if(n == 2){
            return 1;
        }
        if (n == 3)
            return 2;

        int[] dp = new  int[n+1];
        dp[2] = 1;
        dp[3] = 2;

        for(int i = 4; i <= n; i++){
            for (int j = 2; j <= i/2; j++){
                dp[i] = Math.max(dp[i-j] * dp[j], dp[i]);
                dp[i] = Math.max((i-j) * dp[j], dp[i]);
                dp[i] = Math.max((i-j) * j, dp[i]);
                dp[i] = Math.max(dp[i-j] * j, dp[i]);

            }
        }
        return dp[n];
    }
}
