package exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Murphy Xu
 * @create 2021-08-25 15:53
 */
public class DistributePapers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> ages = new ArrayList<>();
        while (in.hasNextInt()){
            ages.add(in.nextInt());
        }
        int[] papers = new int[ages.size()];
        Arrays.fill(papers, 1);

        int n = papers.length;
        if (n == 1){
            System.out.println(1);
        }
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                if (ages.get(i) > ages.get(n - 1)){
                    papers[i] = Math.max(papers[n - 1] + 1, papers[i]);
                }
                if (ages.get(i)  > ages.get(i + 1)){
                    papers[i] = Math.max(papers[i + 1] + 1, papers[i]);
                }
            }else if (i == n - 1){
                if (ages.get(i) > ages.get(i + 1)){
                    papers[i] = Math.max(papers[0] + 1, papers[i]);
                }
                if (ages.get(i) > ages.get(i + 1)){
                    papers[i] = Math.max(papers[i - 1], papers[i]);
                }
            }else{
                if (ages.get(i) > ages.get(i - 1)){
                    papers[i] = Math.max(papers[i + 1] + 1, papers[i]);
                }
                if (ages.get(i) > ages.get(i - 1)){
                    papers[i] = Math.max(papers[i - 1] + 1, papers[i]);
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += papers[i];
        }
        System.out.println(sum);
    }
}
