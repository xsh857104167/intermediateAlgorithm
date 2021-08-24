package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 迷宫问题
 * 输入两个整数，分别表示二维数组的行数，列数。再输入相应的数组，
 * 其中的1表示墙壁，0表示可以走的路。数据保证有唯一解,不考虑有多解的情况，即迷宫只有一条通道。
 * 输出：左上角到右下角的最短路径，格式如样例所示。
 * @author Murphy Xu
 * @create 2021-08-23 22:00
 */
public class MazeII {
    public int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    private Stack<int[]> path;
    private ArrayList<int[]> minPath;
    private int[][] matrix;
    private boolean[][] visited;

    public static void main(String[] args) throws Exception {
        MazeII main= new MazeII();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = null;
        while ((str1 = br.readLine()) != null) {
            String[] arr = str1.split(" ");
            int rows = Integer.parseInt(arr[0]);
            int cols = Integer.parseInt(arr[1]);
            main.path = new Stack<>();
            main.minPath = null;
            main.matrix = new int[rows][cols];
            main.visited = new boolean[rows][cols];
            for (int i = 0; i < rows; i++) {
                String[] str2 = br.readLine().split(" ");
                for (int j = 0; j < cols; j++) {
                    main.matrix[i][j] = Integer.parseInt(str2[j]);
                }
            }
            main.dfs(0, 0);

            StringBuilder sb = new StringBuilder();
            for (int[] res : main.minPath) {
                sb.append('(').append(res[0]).append(',').append(res[1]).append(")\n");
            }
            System.out.print(sb);
        }
    }

    private void dfs(int i, int j) {
        if (i > matrix.length - 1 || i < 0 || j > matrix[0].length - 1 || j < 0 ||
                visited[i][j] || matrix[i][j] == 1 ||
                (minPath != null && path.size() >= minPath.size())) {
            return;
        }
        if (i == matrix.length - 1 && j == matrix[0].length - 1) {
            path.add(new int[]{i, j});
            minPath = new ArrayList<>(path);
            path.pop();
            return;
        }
        path.add(new int[]{i, j});
        visited[i][j] = true;
        for (int[] direction : directions) {
            dfs(i + direction[0], j + direction[1]);
        }
        visited[i][j] = false;
        path.pop();
    }
}
