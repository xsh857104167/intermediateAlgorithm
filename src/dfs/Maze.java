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

    public static int dfs (char[][] maze, boolean[][] visited,
                           int r, int c){

        if (r < 0 || r >= maze.length || c < 0 || c >= maze[0].length){
            return Integer.MAX_VALUE;
        }
        if (maze[r][c] == '#' || visited[r][c]){
            return Integer.MAX_VALUE;
        }
        if (maze[r][c] == 'T'){
            return 1;
        }
        int weight;
        if (maze[r][c] == 'S'){
            weight = 0;
        } else {
            weight = 1;
        }
        int result = Integer.MAX_VALUE;
        visited[r][c] = true;
        result = Math.min(result, dfs(maze, visited, r + 1, c));
        result = Math.min(result, dfs(maze, visited, r - 1, c));
        result = Math.min(result, dfs(maze, visited, r, c + 1));
        result = Math.min(result, dfs(maze, visited, r, c - 1));
        if (maze[r][c] == 'S' && result == Integer.MAX_VALUE){
            return -1;
        }
        return result == Integer.MAX_VALUE ? Integer.MAX_VALUE : result + weight;
    }

}
