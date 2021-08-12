package dp;

import java.util.Scanner;

/**
 * @author Murphy Xu
 * @create 2021-08-12 19:49
 */
public class Chorus {
    /**
     * 求最长合唱队
     * @param nums
     * @param len
     * @return
     */
    public int chorus(int[] nums, int len){
        int[][] dp = new int[len][2];
        dp[0][0] = 1;  // 表示以索引为0结尾（包含）最长合唱队形的人数
        dp[0][1] = 0;  // 0 表示升序， 1 表示降序
        int max = dp[0][0];

        for (int i = 1; i < len; i++) {
            int tmp_max = 0;
            int flag = 0;  // 升还是降
            for (int j = 0; j < i; j++) {
                if (dp[j][1] == 0){  // 上升情况
                    if ((nums[i] < nums[j]) && (dp[j][0] > tmp_max)){ // 由上升转为下降
                        tmp_max = dp[j][0];
                        flag = 1;
                    }else if ((nums[i] > nums[j]) && (dp[j][0] > tmp_max)){ // 继续上升
                        tmp_max = dp[j][0];
                        flag = 0;
                    }
                }else{  // 下降情况
                    if (nums[i] < nums[j] && (dp[j][0] > tmp_max)){
                        tmp_max = dp[j][0];
                        flag = 1;  // 继续下降
                    }
                }
                dp[i][0] = tmp_max + 1;
                dp[i][1] = flag;
                max = Math.max(dp[i][0], max);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        while (in.hasNext()) {
            int len = in.nextInt();
            int[] nums = new int[len];
            for (int i = 0; i < len; i++) {
                nums[i] = in.nextInt();
            }
            Chorus test = new Chorus();
            System.out.println(len - test.chorus(nums, len));
        }

    }
}
