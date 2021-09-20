package exam6;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 美团，没做对
 * @author Murphy Xu
 * @create 2021-09-18 16:18
 */
public class Q4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = Integer.parseInt(in.nextLine());
        int[] lens = new int[t];
        String[] datas = new String[t];
        for (int i = 0; i < t; i++) {
            lens[i] = Integer.parseInt(in.nextLine());
            datas[i] = in.nextLine();
        }
        for (int i = 0; i < t; i++) {
            System.out.println(parse(datas[i], lens[i]));
        }
    }

    public static String parse(String data, int len){
        if (len == 0){
            return "";
        }
        Map<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});


        StringBuffer sb = new StringBuffer();
        int cur = 0;
        int flag = 0; // 表示停顿的秒数
        char pre = '#'; // 表示前一个字符，开始为 #
        char count = 0;
        while (cur < len){
            if (data.charAt(cur) == '0'){

                if (pre != '#'){
                    sb.append(map.get(pre)[count - 1 % map.get(pre).length]);
                }

                sb.append('-');
                flag = 0;
                count = 0;
                pre = '#';
                cur++;

            } else if (data.charAt(cur) == '-'){
                while (cur < len && data.charAt(cur) == '-'){
                    flag++;
                    cur++;
                }
                if (flag >= 2){
                    if (pre != '#'){
                        sb.append(map.get(pre)[count - 1 % map.get(pre).length]);
                    }
                    flag = 0;
                    count = 0;
                }

            }else{  // 2 - 9
                char temp = data.charAt(cur);
                if (flag == 1){
                    if (temp == pre){
                        count++;
                        flag = 0;
                    }else{
                        if (pre != '#'){
                            sb.append(map.get(pre)[count - 1 % map.get(pre).length]);
                        }
                        pre = temp;
                        count = 1;
                    }
                }else{   // flag == 0 且与之前不相等的情况
                    count++;
                    if (pre != '#'){
                        sb.append(map.get(pre)[count - 1 % map.get(pre).length]);
                    }
                    pre = temp;
                    count = 1;
                }
                cur++;
                while (cur < len && data.charAt(cur) == temp){
                    count++;
                    cur++;
                }
            }

        }
        if (pre != '#'){
            sb.append(map.get(pre)[count - 1 % map.get(pre).length]);
        }

        return sb.toString();
    }
}
