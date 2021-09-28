package exam8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * 9.25荣耀第二题
 * @author Murphy Xu
 * @create 2021-09-25 19:18
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        char[] c = s.toCharArray();
        int count = 0;
        for (char cc : c) {
            if (cc == '\"'){
                count++;
            }
        }
        if (count % 2 == 1){
            System.out.println("ERROR");
            return;
        }
        List<String> list = new ArrayList<>(); // 这里可以定义成vector
        StringBuilder sb = new StringBuilder(); // 这是java 中的可变长字符串，可以实现字符串拼接
        boolean flag = false;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\"' && !flag){
                flag = true;
            }
            if (c[i] == ',' && !flag){           // 这些if都是一样的
                String temp = sb.length() == 0 ? "--" : sb.toString();
                list.add(temp);   // 这个相当于push_back
                sb = new StringBuilder();  // 这里重新建了一个可变长字符串
            }else if (c[i] == '\"' && flag && (i + 1 == c.length || c[i + 1] == ',')){
                sb.deleteCharAt(0);  // 删除了vector中的第一个元素

                // 三位运算符应该是一样的，toString是得到可变长字符串的值
                String temp = sb.length() == 0 ? "--" : sb.toString();
                list.add(temp);
                sb = new StringBuilder();
                i++;
            }else if (c[i] == '\"' && flag && (i + 1 == c.length || c[i + 1] == '\"')){
                sb.append('\"');  // 向可边长字符串最后中插入一个引号
                i++;
            }else {
                sb.append(c[i]);
            }
        }
        System.out.println(list.size()); // prinft(vector的长度)
        for (String ss : list) {        // 循环输出vector中的值
            System.out.println(ss);
        }
    }
}
