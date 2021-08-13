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

    /**
     * 中心扩展算法，遍历所有长度为1或者2的字串
     * 24ms, 88.37%; 38.2MB, 94.63%
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1){
            return "";
        }
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start){
                start = i -(len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    public int expandAroundCenter(String s, int left, int right){
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            --left;
            ++right;
        }
        return right - left - 1;
    }

    /**
     * 动态规划
     * 长度大于2的回文串其首尾都去掉后仍然是一个回文串。
     * 看完之后发现思想是类似的
     * 192ms, 42.61%; 42.8MB, 21.31%
     * @param s
     * @return
     */
    public String longestPalindrome3(String s) {
        int len = s.length();
        if (len < 2){
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示s[i .. j]是回文串
        boolean[][] dp = new boolean[len][len];

        // 将长度为1的串都初始化为回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();

        // 枚举字串长度
        for (int L = 2; L <= len; L++) {
            for (int i = 0; i < len; i++) { // i表示左边界
                int j = L + i - 1;
                if (j >= len)
                    break;

                if (charArray[i] != charArray[j]){
                    dp[i][j] = false;
                }else {
                    if (j - i < 3){
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                // 只要dp[i][L] == true, 就表示字串s[i .. L]是回文， 此时记录回文长度和起始位置
                if (dp[i][j] && j - i +1 > maxLen){
                    maxLen = j -i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * Manacher算法，O(n)时间复杂度，比较复杂
     * @param s
     * @return
     */
    public String longestPalindrome4(String s) {

        return null;
    }

    @Test
    public void test(){
        System.out.println(longestPalindrome("cbbd"));
    }
}
