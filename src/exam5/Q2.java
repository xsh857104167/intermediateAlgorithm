package exam5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 9.17阿里
 * 用例：5 2
 * 1 2 3 4 2
 * 输出：12
 *
 * @author Murphy Xu
 * @create 2021-09-17 19:22
 */
public class Q2 {

    static List<Integer> path = new ArrayList<>();
    static int count;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        count = n;
        int k = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        for (int i = 2; i <= k; i++) {
            backtracking(nums, i, 0);
        }
        System.out.println(count);
    }

    public static void backtracking(int[] nums, int l, int cur) {
        if (path.size() == l) {
            if (isInc(path)) {
                count++;
            }
            return;
        }
        for (int i = cur; i < nums.length; i++) {
            path.add(nums[i]);
            backtracking(nums, l, i + 1);
            path.remove(path.size() - 1);
        }
    }

    private static boolean isInc(List<Integer> path) {
        int flag = Integer.MIN_VALUE;
        for (int pa : path) {
            if (pa <= flag) {
                return false;
            }
            flag = pa;
        }
        return true;
    }
}
