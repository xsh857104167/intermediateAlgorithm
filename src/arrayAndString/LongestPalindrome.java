package arrayAndString;

import org.junit.Test;

/**
 * @author Murphy Xu
 * @create 2021-08-12 15:54
 */
public class LongestPalindrome {
    /**
     * 瞎搞动态规划，dp[i][j]表示从i到j字串是不是回文
     * 184ms,44.46%; 42.2MB, 39.67%
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (j == i-1){
                    dp[j][i] = s.charAt(j) == s.charAt(i);
                    continue;
                }
                if (dp[j+1][i-1]){
                    dp[j][i] = s.charAt(j) == s.charAt(i);
                }else{
                    dp[j][i] = false;
                }
            }
        }

        int l = 0, r = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (dp[i][j] && (j-i) > (r-l)){
                    r = j;
                    l = i;
                }
            }
        }
        return s.substring(l, r+1);
    }

    @Test
    public void test(){
        System.out.println(longestPalindrome("cbbd"));
    }
}
