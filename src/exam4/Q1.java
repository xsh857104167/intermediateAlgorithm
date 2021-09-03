package exam4;

import java.util.Scanner;

/**
 * @author Murphy Xu
 * @create 2021-09-03 18:08
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(in.hasNextInt()){
            int num = in.nextInt();
            int[] data = new int[num];
            for (int i = 0; i < num; i++) {
                data[i] = in.nextInt();
            }
            int[] res = new int[num];
            for (int i = 0; i < num; i++) {
                res[i] = cal(data[i]);
                System.out.println(res[i]);
            }
        }
    }

    public static int cal(int n){
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 1;
        }
        // 只需要
        if (n % 2 == 0){
            return n /2;
        }
        if (n % 2 != 0){
            return 1 + (n - 1)/2;
        }
        return 1;
    }
}
