package additional;

/**
 * @author Murphy Xu
 * @create 2021-08-25 15:14
 */
public class Candy {
    /**
     * 两次遍历；贪心
     * 3ms, 54.49%; 39.5MB, 47.86%
     * @param ratings
     * @return
     */
    public int candy(int[] ratings){
        int n = ratings.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0 && ratings[i] > ratings[i-1]){
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int right = 0, ret = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]){
                right++;
            } else {
                right = 1;
            }
            ret += Math.max(left[i], right);
        }
        return ret;
    }

    /**
     * 一次遍历
     * 2ms, 100%; 39.5MB, 52.31%
     * @param ratings
     * @return
     */
    public int candy2(int[] ratings){
        int n = ratings.length;
        int ret = 1;
        // 当前递减序列的长度dec，最近的递增序列长度inc
        // 和前一个同学分得的糖果数量pre
        int inc = 1, dec = 0, pre = 1;

        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i-1]){
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                dec++;
                if (dec == inc){
                    dec++;
                }
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }
}
