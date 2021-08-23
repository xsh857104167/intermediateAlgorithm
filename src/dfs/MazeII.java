package dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

/**
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
