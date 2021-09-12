package additional;

/**
 * @author Murphy Xu
 * @create 2021-09-12 17:09
 */
public class MaximalRectangle {
    /**
     * 16ms, 18.42%; 41.6 MB, 31.79%
     * @param matrix 矩阵
     * @return 最大矩形
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0){
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1'){
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0'){
                    continue;
                }
                int width = left[i][j];
                int area = width;
                for (int k = i - 1; k >= 0; k--) {
                    width = Math.min(width, left[k][j]);
                    area = Math.max(area, (i - k + 1) * width);
                }
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

    /**
     * 单调栈
     * @param matrix
     * @return
     */
    public int maximalRectangle2(char[][] matrix) {
        return 0;
    }
}
