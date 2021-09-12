package arrayAndString;

import org.junit.Test;

/**
 * @author Murphy Xu
 * @create 2021-09-11 20:52
 */
public class MaxWater {
    /**
     * max water
     * AC了
     * @param arr int整型一维数组 the array
     * @return long长整型
     */
    public long maxWater(int[] arr) {
        int len = arr.length;
        if (len < 3)
            return 0;
        long max = 0;
        int left = 0;
        int right = 1;

        while (right < len) {
            int minorMaxIndex = right;
            while (right < len && arr[right] < arr[left]) {
                minorMaxIndex = arr[minorMaxIndex] < arr[right] ? right : minorMaxIndex;
                right++;
            }

            int threshold;
            if (right == len){
                right--;
                threshold = Math.min(arr[left], arr[minorMaxIndex]);
                for (int i = left; i < minorMaxIndex; i++) {
                    int tmp = Math.max(threshold - arr[i], 0);
                    max += tmp;
                }
                left = minorMaxIndex;
                right = minorMaxIndex + 1;
            }else{
                threshold = Math.min(arr[left], arr[right]);
                for (int i = left; i < right; i++) {
                    int tmp = Math.max(threshold - arr[i], 0);
                    max += tmp;
                }
                left = right;
                right++;
            }

        }
        return max;
    }

    /**
     * 不补了，换个思路
     * @param arr
     * @return
     */
    public long maxWater2(int[] arr) {
        if (arr == null || arr.length <= 2){
            return 0;
        }
        int left = 0, right = arr.length - 1;
        long sum = 0;
        // 找出左右边界的最小值作为水位高度
        int mark = Math.min(arr[left], arr[right]);
        while (left < right) {
            // 如果左边较低，则左边界向右遍历
            if (arr[left] < arr[right]){
                left++;
                if (arr[left] < mark){
                    sum += mark - arr[left];
                }else {
                    mark = Math.min(arr[left], arr[right]);
                }
            }else {
                right--;
                if (arr[right] < mark){
                    sum += mark - arr[right];
                }else{
                    mark = Math.min(arr[right], arr[left]);
                }
            }
        }

        return sum;
    }

    @Test
    public void test(){
        int[] arr= {6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3};
        System.out.println(maxWater(arr));
    }
}
