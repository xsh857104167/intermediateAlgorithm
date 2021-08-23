package backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Murphy Xu
 * @create 2021-08-17 15:24
 */
public class SearchWords {

    List<Integer> path = new ArrayList<>();

    /**
     * dfs
     * 151ms, 26.34%; 36.5 MB, 50.19%
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int nr = board.length;
        int nc = board[0].length;
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (board[r][c] == word.charAt(0)){
                    if (dfs(board, r, c, word, 0))
                        return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, int r, int c, String word, int cur){

        int len = word.length();
        int nr = board.length;
        int nc = board[0].length;


        if (r<0 || c < 0 || r >= nr || c >= nc || board[r][c] != word.charAt(cur)){
            return false;
        }
        if (cur == len - 1){
            return true;
        }
        board[r][c] = '#';
        path.add(r * nc + c);

        if (dfs(board, r - 1, c, word, cur + 1)
                || dfs(board, r + 1, c, word, cur + 1)
                || dfs(board, r, c - 1, word, cur + 1)
                || dfs(board, r, c + 1, word, cur + 1)){

            return true;
        }else{
            int tmp = path.get(path.size() - 1);
            board[tmp / nc][tmp % nc] = word.charAt(cur);
            path.remove(path.size() - 1);

            return false;
        }

    }

    /**
     * 回溯,其实也就是dfs
     * 166ms, 21.12%; 38.9MB,6.38%
     * @param board
     * @param word
     * @return
     */
    public boolean exist2(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)){
            return false;
        } else if (k == s.length() - 1){
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length){
                if (!visited[newi][newj]){
                    boolean flag = check(board, visited, newi, newj, s, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }

    @Test
    public void test(){
        char[][] board = {{'A','B','C','E'},
                {'S', 'F','E', 'S'},
                {'A', 'D', 'E', 'E'}};
        String word = "ABCESEEEFS";

        System.out.println(exist(board, word));
    }
}
