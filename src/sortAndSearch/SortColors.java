package sortAndSearch;

/**
 * @author Murphy Xu
 * @create 2021-08-19 14:24
 */
public class SortColors {
    /**
     * 双指针一次循环
     * 0ms, 100%, 37MB
     * @param nums
     */
    public void sortColors(int[] nums) {
        int pre = 0;
        int post = nums.length - 1;
        int tmp;
        for (int i = 0; i <= post;){
            if (nums[i] == 0){
                tmp = nums[pre];
                nums[pre] = nums[i];
                nums[i] = tmp;
                pre++;
                i++;
            }else if (nums[i] == 2){
                tmp = nums[post];
                nums[post] = nums[i];
                nums[i] = tmp;
                post--;
            }else{
                i++;
            }
        }
    }
}
