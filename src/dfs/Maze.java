package dfs;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Murphy Xu
 * @create 2021-08-23 15:00
 */
public class Maze {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n , m;
        while (in.hasNext()){
            n = in.nextInt();
            m = in.nextInt();
            char[][] maze = new char[n][m];
            boolean[][] visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                maze[i] = in.next().toCharArray();
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (maze[i][j] == 'S'){
                        System.out.println(dfs(maze, visited, i, j));
                    }
                }
            }
        }

    }

    public static int dfs(char[][] maze, boolean[][] visited, int r, int c){
        System.out.println(r + c);
        if (r < 0 || r >= maze.length || c < 0 || c >= maze[0].length || maze[r][c] == '#' || visited[r][c]){
            return -1;
        } else if (maze[r][c] == 'T'){
            return 0;
        }

        visited[r][c] = true;
        int len = 1;
        int top = dfs(maze, visited, r - 1, c);
        int left = dfs(maze, visited, r, c - 1);
        int bottom = dfs(maze, visited, r + 1, c);
        int right = dfs(maze, visited, r, c + 1);

        if (top == -1 && left == -1 && bottom == -1 && right == -1){
            visited[r][c] = false;
            return -1;
        }else{
            int min = Integer.MAX_VALUE;
            if (top != -1){
                min = Math.min(top, min);
            }
            if (left != -1){
                min = Math.min(left, min);
            }
            if (bottom != -1){
                min = Math.min(bottom, min);
            }
            if (right != -1){
                min = Math.min(right, min);
            }
            len += min;
        }
        visited[r][c] = false;

        return len;
    }
}
