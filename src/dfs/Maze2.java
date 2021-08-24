package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 输入数据第一行两个整数n和m。n和m的范围[10,500]。
 * 接下来n行，每行m个元素，表示迷宫的每个方格。
 * 'S'表示机器人的出发点,
 * 'T'表示目的地，
 * '#'表示该方格不能通过，
 * '.'表示可以通过。
 * 输出：
 * 输出一个整数表示机器人到达目的地的最短时间，如果机器人不能到达目的地，输出-1。
 * @author Murphy Xu
 * @create 2021-08-24 16:36
 */
public class Maze2 {
    public int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public Deque<int[]> path;
    public int minCost;
    public char[][] matrix;
    public boolean[][] visited;

    public static void main(String[] args) throws IOException {
        Maze2 main = new Maze2();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = null;
        while ((str1 = br.readLine()) != null){
            String[] arr = str1.split(" ");
            int rows = Integer.parseInt(arr[0]);
            int cols = Integer.parseInt(arr[1]);

            main.path = new LinkedList<>();
            main.minCost = -1;
            main.matrix = new char[rows][cols];
            main.visited = new boolean[rows][cols];
            for (int i = 0; i < rows; i++) {
//                main.matrix[i] = br.readLine().toCharArray();
                String strs = br.readLine();
                for (int j = 0; j < cols; j++) {
                    main.matrix[i][j] = strs.charAt(j);
                }
                System.out.println(Arrays.toString(main.matrix[i]));
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (main.matrix[i][j] == 'S'){
                        System.out.println(i + ", " + j);
                        System.out.println("*************");
                        main.dfs(i, j);
                        System.out.println(main.minCost);
                    }
                }
            }

        }
    }

    public void dfs(int i, int j){
        System.out.println("走过" + i + ", " + j);
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (i > rows - 1 || i < 0 || j > cols - 1 || j < 0){
            return;
        }
        if (visited[i][j]){
            return;
        }
        if (matrix[i][j] == '#'){
            return;
        }

        if (minCost > 0 && path.size() >= minCost){
            return;
        }

        if (matrix[i][j] == 'T'){
            minCost = path.size();
            System.out.println(minCost);
            System.out.println("-----------------");
            return;
        }

        path.add(new int[]{i, j});
        visited[i][j] = true;

        for (int[] direction: directions) {
            dfs(i + direction[0], j + direction[1]);
        }
        visited[i][j] = false;
        path.removeLast();
    }
}
