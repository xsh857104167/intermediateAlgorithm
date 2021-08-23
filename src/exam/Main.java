package exam;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 网易821笔试
 * @author Murphy Xu
 * @create 2021-08-21 15:05
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String input = in.nextLine();
            String[] strs = input.split(" ");
            int[] nums = new int[strs.length];
            for (int i = 0; i < strs.length; i++) {
                nums[i] = Integer.parseInt(strs[i]);
            }
        }
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 返回Sn的第k位字符
     * @param n int整型 Sn的n
     * @param k int整型 需要返回的字符下标位
     * @return char字符型
     */
    public char findKthBit (int n, int k) {
        // write code here
        char[] words = new char[27];
        for (int i = 1; i <= 26; i++) {
            words[i] = (char) ('a' + i - 1);
        }

        String[] s = new String[n + 1];
        s[1] = "a";

        for (int i = 2; i <= n; i++) {
            StringBuffer sb = new StringBuffer(s[i-1]);
            sb.append(words[i]);
            StringBuffer sb2 = new StringBuffer(invert(s[i-1]));
            sb2.reverse();
            sb.append(sb2);
            s[i] = sb.toString();
        }


        return s[n].charAt(k-1);
    }

    public String invert(String str){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            sb.append((char) ('z' - str.charAt(i) + 'a'));
        }
        return sb.toString();
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 计算最小航行费用
     * @param input int整型二维数组 二维网格
     * @return int整型
     */
    public int minSailCost (int[][] input) {
        // write code here
        if (input ==  null && input[0] == null){
            return -1;
        }
        int nr = input.length;
        int nc = input[0].length;
//        if(input[0][0] == 2){
//            return -1;
//        }
        int[][] dp = new int[nr][nc];
        for (int i = 1; i < nc; i++) {
            if (input[0][i-1] == 2 || dp[0][i-1] == -1){
                dp[0][i] = -1;
            }else {
                if (input[0][i-1] == 1){
                    dp[0][i] = dp[0][i-1] + 1; // 1
                }else {
                    dp[0][i] = dp[0][i-1] + 2; // 0
                }
            }
            System.out.println(dp[0][i]);
        }
        System.out.println();
        for (int i = 1; i < nr; i++) {
            if (input[i-1][0] == 2 || dp[i-1][0] == -1){
                dp[i][0] = -1;
            }else {
                if (input[i-1][0] == 1){
                    dp[i][0] = dp[i-1][0] + 1; // 1
                }else {
                    dp[i][0] = dp[i-1][0] + 2; // 0
                }
            }
            System.out.println(dp[i][0]);
        }
        System.out.println();
//        for (int i = 1; i < nr; i++) {
//            for (int j = 1; j < nc; j++) {
//                if (dp[i-1][j] == -1 && dp[i][j-1] != -1){
//                    dp[i][j] = dp[i][j-1] + input[i][j-1];
//                }else if (dp[i-1][j] != -1 && dp[i][j-1] == -1){
//                    dp[i][j] = dp[i-1][j] + input[i-1][j];
//                }else if (dp[i-1][j] != -1 && dp[i][j-1] != -1){
//                    dp[i][j] = Math.min(dp[i-1][j] + input[i-1][j], dp[i][j-1] + input[i][j-1]) ;
//                }else{
//                    dp[i][j] = -1;
//                }
//            }
//        }

        for (int i = 1; i < nr; i++) {
            for (int j = 1; j < nc; j++) {
                if (dp[i-1][j] != -1 && dp[i][j-1] != -1){
                    if (input[i-1][j] == 2 && input[i][j-1] != 2){
                        dp[i][j] = dp[i][j-1] + input[i][j-1];
                    }else if (input[i][j-1] == 2 && input[i-1][j] != 2){
                        dp[i][j] = dp[i-1][j] + input[i-1][j];
                    }else if (input[i-1][j] != 2 && input[i][j-1] != 2){
                        dp[i][j] = Math.min(dp[i-1][j] + input[i-1][j], dp[i][j-1] + input[i][j-1]) ;
                    }else{
                        dp[i][j] = -1;
                    }
                }else if (dp[i-1][j] == -1 && dp[i][j-1] != -1){


                    dp[i][j] = -1;
                }
            }
        }
        return dp[nr-1][nc-1];
    }


    public int minSailCost2 (int[][] input) {
        int[][] block = new int[input.length][input[0].length];
        return getMin(input, block, 0, 0);
    }

    public int getMin(int[][] grid, int[][] block, int x, int y){
        int result = Integer.MAX_VALUE;
        if (y < 0 || y >= grid[0].length){
            return Integer.MAX_VALUE;
        }
        if (x < 0 || x >= grid.length){
            return Integer.MAX_VALUE;
        }
        if (block[x][y] == 1){
            return Integer.MAX_VALUE;
        }
        int weight;
        if (grid[x][y] == 0){
            weight = 2;
        }else if (grid[x][y] == 1){
            weight = 1;
        }else{
            return Integer.MAX_VALUE;
        }

        if (x == 0 && y == 0){
            weight = 0;
        }
        if (x == grid.length -1 && y == grid[0].length-1){
            return weight;
        }
        block[x][y] = 1;
        result = Math.min(result, getMin(grid, block, x + 1, y));
        result = Math.min(result, getMin(grid, block, x - 1, y));
        result = Math.min(result, getMin(grid, block, x, y - 1));
        result = Math.min(result, getMin(grid, block, x, y + 1));
        block[x][y] = 0;
        if (x==0 &&y == 0 && result == Integer.MAX_VALUE){
            return -1;
        }
        return result == Integer.MAX_VALUE ? Integer.MAX_VALUE : result + weight;
    }

    @Test
    public void test(){
        int[][] test = {{1, 1, 1 ,1, 0},{0,1,0,1,0},{1,1,2,1,1},{0,2,0,0,1}};
        System.out.println(minSailCost2(test));
    }
}
