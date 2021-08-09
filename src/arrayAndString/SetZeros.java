package arrayAndString;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Murphy Xu
 * @create 2021-08-09 17:50
 */
public class SetZeros {
    /**
     * 空间复杂度未O(m + n)
     * 1ms, 99.19%; 39.8MB, 79.69%
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int[] row = new int[matrix.length];
        int[] col = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0){
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }

        for (int i = 0; i < row.length; i++) {
            if (row[i] == 1){
                Arrays.fill(matrix[i], 0);
            }
        }
        for (int i = 0; i < col.length; i++) {
            if (col[i] == 1){
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }

    /**
     * 常数空间复杂度的方法
     * 先判断第一行第一列是否为0，然后利用两个变量存储第一行和第一列是否为0
     * 然后再利用第一行和第一列的空间做第一种方法的操作
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {

    }

    @Test
    public void test(){
        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(matrix);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
