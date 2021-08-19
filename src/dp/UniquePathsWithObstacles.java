package dp;

/**
 * 补充题：63.不同的路径II
 * @author Murphy Xu
 * @create 2021-08-19 18:26
 */
public class UniquePathsWithObstacles {
    /**
     * dp
     * 1ms,13.73%; 37.8MB, 25.36%
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1)
            return 0;
        int nr = obstacleGrid.length;
        int nc = obstacleGrid[0].length;
        int[][] dp = new int[nr][nc];

        int i = 0;
        for (; i < nc; i++) {
            if (obstacleGrid[0][i] == 0){
                dp[0][i] = 1;
            } else {
                dp[0][i] = 0;
                break;
            }
        }

        for (; i < nc; i++) {
            dp[0][i] = 0;
        }

        for (i = 1; i < nr; i++){
            if (obstacleGrid[i][0] == 0){
                dp[i][0] = 1;
            } else {
                dp[i][0] = 0;
                break;
            }
        }
        for (; i < nr; i++) {
            dp[i][0] = 0;
        }

        for (int k = 1; k < nr; k++) {
            for (int j = 1; j < nc; j++) {
                dp[k][j] = obstacleGrid[k][j-1] == 0 ? dp[k][j-1] + dp[k][j] : dp[k][j];
                dp[k][j] = obstacleGrid[k-1][j] == 0 ? dp[k-1][j] + dp[k][j] : dp[k][j];
            }
        }
        return obstacleGrid[nr-1][nc-1] == 0? dp[nr-1][nc-1] : 0;
    }

}
