package dp;

/**
 * 补充题目：96. 不同的二叉搜索树
 * @author Murphy Xu
 * @create 2021-08-20 14:06
 */
public class NumTrees {
    /**
     * dp
     * 0ms, 100%; 35MB, 82.35%
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if (n < 3){
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1; // 没意义
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++){
            for (int j = 1; j <= i; j++){
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
