package exam6;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 美团
 * @author Murphy Xu
 * @create 2021-09-18 17:21
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = Integer.parseInt(in.nextLine());
        String[] datas = new String[t];
        for (int i = 0; i < t; i++) {
            datas[i] = in.nextLine();
        }
        for (int i = 0; i < t; i++) {
            System.out.println(check(datas[i]));
        }
    }

    public static String check(String data){
        if (data.length() == 1){
            return "NO";
        }

        int len = data.length();
        char pre = data.charAt(0);
        for (int i = 1; i < len; i++) {
            if (data.charAt(i) != pre){
                return "YES";
            }
        }
        return "NO";
//        Map<Character, Integer> map = new HashMap<>();
//
//        for (int i = 0; i < len; i++) {
//            map.put(data.charAt(i), map.getOrDefault(data.charAt(i), 0) + 1);
//        }
//        int count = 0; // 奇数的个数
//        for (int i = 0; i < 26; i++) {
//            if (map.getOrDefault((char)('a' + i), 0) % 2 == 1){
//                count++;
//            }
//        }

    }
}
