package exam8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Murphy Xu
 * @create 2021-09-25 20:08
 */
public class Q1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String[] strs = in.nextLine().split(",");
            int total = Integer.parseInt(strs[0]);
            String hand = strs[1];
            process(total, hand); // 读入数据并调用函数
        }
    }

    public static void process(int total, String hand) {
        List<Integer> time_left = new ArrayList<>();
        List<Integer> time_right = new ArrayList<>();
        // 第一次拍
        time_left.add(1);
        total--;
        boolean isLeft = false; // 下一次右手拍
        while (total > 0) {
            if (isLeft) { // 左手拍
                int temp = time_left.get(time_left.size() - 1) + time_right.get(time_right.size() - 1);

                if (temp <= total) {
                    time_left.add(temp);
                } else {
                    time_left.add(total);
                }
                total -= temp;
                isLeft = false;
            } else { // 右手拍
                int temp;
                if (time_right.size() == 0) {
                    temp = time_left.get(time_left.size() - 1);
                }else {
                   temp = time_left.get(time_left.size() - 1) + time_right.get(time_right.size() - 1);
                }

                if (temp <= total) {
                    time_right.add(temp);
                } else {
                    time_right.add(total);
                }
                total -= temp;
                isLeft = true;
            }
        }

        System.out.println("now is " + time_left.size() + "times by "+ hand +" hand");
        if (hand.equals("left")){
            for (int i = 0; i < time_left.size(); i++) {
                if (i == time_left.size() - 1) {
                    System.out.print(time_left.get(i));
                } else {
                    System.out.print(time_left.get(i) + " ");
                }
            }
        }else {
            for (int i = 0; i < time_right.size(); i++) {
                if (i == time_right.size() - 1) {
                    System.out.print(time_right.get(i));
                } else {
                    System.out.print(time_right.get(i) + " ");
                }
            }
        }

    }
}
