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
            for (int i = 0; i < n; i++) {
                maze[i] = in.next().toCharArray();
                System.out.println(Arrays.toString(maze[i]));
            }

        }

    }

//    public boolean dfs(){
//
//    }
}
