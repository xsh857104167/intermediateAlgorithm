package dp;

/**
 * @author Murphy Xu
 * @create 2021-08-20 16:52
 */
public class LastStoneWeightII {
    public int lastStoneWeightII(int[] stones) {
        int len = stones.length;
        if (len == 1) {
            return stones[0];
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            count += stones[i];
        }
        int before = count / 2;

        int[] dp = new int[before + 1];

        for (int i = 0; i < len; i++){
            for (int j = before; j >= stones[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        int j;
        for (j = before; j >= 0; j--){
            if (dp[j] == j){
                break;
            }
        }

        return count - dp[j] - dp[j];
    }
}
