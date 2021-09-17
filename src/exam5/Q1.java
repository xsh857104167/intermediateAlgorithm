package exam5;

import java.util.Scanner;

/**
 * 9.17 阿里
 * @author Murphy Xu
 * @create 2021-09-17 18:52
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = Integer.parseInt(in.nextLine());

        for (int l = 0; l < t; l++) {
            String[] strs = in.nextLine().split(" ");
            int n = Integer.parseInt(strs[0]);
            int k = Integer.parseInt(strs[1]);

            System.out.println(countNum(n, k));
        }

    }

//    public static int countNum(int n, int k) {
//        if (k >= n) {
//            return 0;
//        }
//        int count = n - k;
//
//        for (int i = k + 1; i <= n; i++) {
//            for (int j = 2; j <= n; j++) {
//                if (i != j && j + k == i) {
//                    count += (n - i) / k;
//                }
//            }
//        }

//        return count;
//    }

    public static int countNum(int n, int k) {
        if (n == 1 || k >= n) {
            return 0;
        }
        int count = 0;
        if (k == 0){

            for (int i = 2; i <= n; i++) {
                count += n / (i - 1);
            }
            return count;
        }

        for (int i = 2; i <= n; i++) {
            if ( i == k){   // 思路是一样的被取余数小于k时就不用看了，直接加n - k
                count += n - i;
            }else if (i > k){
                int num = n / i;
                count += num;
                if (num * i + k > n){
                    count -= 1;
                }
            }
        }
        return count;
    }
}
