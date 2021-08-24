package dfs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 迷宫问题II的第二中解法
 * @author Murphy Xu
 * @create 2021-08-24 15:28
 */
public class MazeII2 {
    public int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public Deque<int[]> path;
    public ArrayList<int[]> minPath;
    public int[][] matrix;
    public boolean[][] visited;

    public static void main(String[] args) {
        MazeII2 maze = new MazeII2();
        maze.path = new LinkedList<>();
        maze.minPath = null;

        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String[] strs = in.nextLine().split(" ");
            int rows = Integer.parseInt(strs[0]);
            int cols = Integer.parseInt(strs[1]);
            maze.matrix = new int[rows][cols];
            maze.visited = new boolean[rows][cols];
            for (int i = 0; i < rows; i++) {
                String[] str = in.nextLine().split(" ");
                for (int j = 0; j < cols; j++) {
                    maze.matrix[i][j] = Integer.parseInt(str[j]);
                }
            }

            maze.dfs(0, 0);

            for (int[] res : maze.minPath) {
                System.out.println("(" + res[0] + "," + res[1] + ")");
            }
        }


    }

    public void dfs (int i, int j){
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (i < 0 || i > rows - 1 || j < 0 || j > cols - 1){
            return;
        }
        if (visited[i][j]){
            return;
        }
        if (matrix[i][j] == 1){
            return;
        }
        if (minPath != null && path.size() >= minPath.size()){
            return;
        }
        if (i == rows - 1 && j == cols - 1){
            path.add(new int[]{i, j});
            minPath = new ArrayList<>(path);
            path.removeLast();
            return;
        }
        path.add(new int[]{i, j});
        visited[i][j] = true;
        for (int[] direction : directions) {
            dfs(i + direction[0], j + direction[1]);
        }
        visited[i][j] = false;
        path.removeLast();
    }
}
