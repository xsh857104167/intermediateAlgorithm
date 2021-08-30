package exam2;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Murphy Xu
 * @create 2021-08-25 19:59
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String[] str = in.nextLine().split(" ");
            int m = Integer.parseInt(str[0]);
            int n = Integer.parseInt(str[1]);
            int[][] matrix = new int[m][n];

            for (int i = 0; i < m; i++) {
                String[] str2 = in.nextLine().split(" ");
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Integer.parseInt(str2[j]);
                }
            }
            System.out.println(maxSubMatrix(matrix));
        }
    }
    public static int maxSubArray(int[] array){
        if (array.length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int[] maxSub = new int[array.length];
        maxSub[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            maxSub[i] = maxSub[i - 1] > 0 ? maxSub[i - 1] + array[i] : array[i];
            if (max < maxSub[i]){
                max = maxSub[i];
            }
        }
        return max;
//        for (int i = 0; i < n; i++) {
//            if (b > 0){
//                b += array[i];
//            } else {
//               b = array[i];
//
//            }
//            if (b > sum){
//                sum = b;
//            }
//        }
    }
/*    public static int maxSubMatrix(int m, int n, int[][] matrix){
        int i, j, k, max = 0;
        int sum = Integer.MIN_VALUE;
        int[] b = new int[n];

        for (i = 0; i < m; i++) {
            for (k = 0; k < n; k++) {
                b[k] = 0;
            }
            for (j = i; j < m; j++) {
                for (k = 0; k < n; k++) {
                    b[k] += matrix[j][k];
                }
                max = maxSubArray(b, k);
                if (max > sum){
                    sum = max;
                }
            }
        }
        return sum;
    }*/

    public static int maxSubMatrix(int[][] matrix){
        int[][] total = matrix;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                total[i][j] += total[i-1][j];
            }
        }
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                int[] result = new int[matrix[0].length];
                for (int f = 0; f < matrix[0].length; f++) {
                    if (i == 0){
                        result[f] = total[j][f];
                    }else {
                        result[f] = total[j][f] - total[i - 1][f];
                    }
                }
                int maximal = maxSubArray(result);
                if (maximal > max){
                    max = maximal;
                }
            }
        }
        return max;
    }
}

