package dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Murphy Xu
 * @create 2021-08-11 17:23
 */
public class UniquePaths {
    /**
     * 数学公式，排列组合
     * 0ms, 100%; 35.1MB,71.09%
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        long sum = 1;
        for (int i = 0; i < n-1; i++) {
            sum = sum * (m + i) / (i+1);
        }
        return (int)sum;
    }

    /**
     * 动态规划
     * 0ms, 100ms; 35.3 MB,23.13%
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][ n];
        Arrays.fill(dp[0], 1);
        for (int j = 1; j < m; j++) {
            dp[j][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }
        return dp[m-1][n-1];
    }

    @Test
    public void test(){
        System.out.println(uniquePaths2(3, 3));
    }
}
