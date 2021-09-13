package additional;

/**
 * 检查是否区域内所有整数都被覆盖
 * @author Murphy Xu
 * @create 2021-09-13 9:59
 */
public class IsCovered {
    /**
     * 差分数组
     * 0ms,100%; 37.6 MB, 74.51%
     * @param ranges
     * @param left
     * @param right
     * @return
     */
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] diff = new int[52];
        for (int[] range : ranges) {
            ++diff[range[0]];
            --diff[range[1] + 1];
        }
        // 前缀和
        int curr = 0;
        for (int i = 1; i <= 50; i++) {
            curr += diff[i];
            if (i >= left && i <= right && curr <= 0){
                return false;
            }
        }
        return true;
    }
}
