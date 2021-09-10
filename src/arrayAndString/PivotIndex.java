package arrayAndString;

/**
 * 724. 寻找数组的中心下标
 * @author Murphy Xu
 * @create 2021-09-10 14:39
 */
public class PivotIndex {
    /**
     * 1ms,100%;39 MB, 62.40%
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int total = 0;
        int length = nums.length;
        for (int num : nums) {
            total += num;
        }
        int sum = 0;
        for (int i = 0; i < length; i++) {
            if (2 * sum + nums[i] == total){
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
}
