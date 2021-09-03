package exam4;

import java.util.Scanner;

/**
 * @author Murphy Xu
 * @create 2021-09-03 18:24
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] n = new int[num];
        String[] data = new String[num];

        for (int i = 0; i < num; i++) {
            n[i] = in.nextInt();
            data[i] = in.next();

        }

        for (int i = 0; i < num; i++) {
            System.out.println(helper(n[i], data[i]));
        }

    }

    public static int helper(int n, String data){
        int a = 0;
        int b = 0;
        char[] chars = data.toCharArray();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '('){
                a++;
            }else{
                a--;
            }
            if (a < 0){
                b++;
                a=0;
            }
        }
        return b;
    }
}
