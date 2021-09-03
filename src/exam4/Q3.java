package exam4;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Murphy Xu
 * @create 2021-09-03 18:44
 */
public class Q3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int goodsNum = in.nextInt();
            int chanceNum = goodsNum;
            int k = in.nextInt();
            int m = in.nextInt();
            int[] goods = new int[goodsNum];
            for (int i = 0; i < goodsNum; i++) {
                goods[i] = in.nextInt();
            }

            // 结果
            int res = 0;

            // 先排序
            Arrays.sort(goods);
            // 找到严格大于m
            int index = 0;
            for (; index < goodsNum; index++) {
                if (goods[index] > m){
                    break;
                }
            }

            int left = index - 1;// 左指针表示从小于m的数里面选的指针
            int right = goodsNum - 1; // 大于m的数的指针

            while(chanceNum > 0){

                int count = 0;
                int flag = 0;
                if (chanceNum <= k + 1){
                    flag = 1;
                }

                for (int i = 0; i < k+1; i++){
                    if ((left - i) < 0){
                        break;
                    }
                    count += goods[left -i];

                }
                if (flag == 0){
                    if (count >= goods[right]){
                        res += count;
                        left -= k+1;
                    }else{
                        res += goods[right];
                        right--;
//                        chanceNum--;
                    }
                    chanceNum -= k + 1;
                }else{
                    res += goods[right];
                    right--;
                    chanceNum--;
                    for (int i = 0; i < chanceNum; i++) {
                        res += goods[left - i];
                    }
                    chanceNum = 0;
                }

            }
            System.out.println(res);
        }
    }
}
