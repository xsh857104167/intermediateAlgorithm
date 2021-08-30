package exam2;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Murphy Xu
 * @create 2021-08-25 19:06
 */
public class Q2 {
    public int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int[][] grid;
    public boolean[][] visited;
    public Deque<Nodes> queue;

    public static void main(String[] args) {
        Q2 main = new Q2();
        Scanner in = new Scanner(System.in);

        while (in.hasNext()){
            String[] str =  in.nextLine().split(" ");
            int row = Integer.parseInt(str[0]);
            int col = Integer.parseInt(str[1]);
            main.grid =  new int[row][col];
            main.visited =  new boolean[row][col];
            main.queue = new LinkedList<>();
            for (int i = 0; i < row; i++) {
                String[] str2 = in.nextLine().split(" ");
                for (int j = 0; j < col; j++) {
                    main.grid[i][j] = Integer.parseInt(str2[j]);
                }
            }

            // 将起点和终点改变
            main.grid[0][0] = Integer.MAX_VALUE;
            main.grid[row - 1][col - 1] = Integer.MAX_VALUE;

            main.queue.add(new Nodes(0, 0));
            main.dec();
            main.visited[0][0] = true;// 走到这的步数
            System.out.println(main.bfs());
        }
    }

    public int bfs(){
        int row = grid.length;
        int col = grid[0].length;

        while(!queue.isEmpty()){
            int len = queue.size();
            for (int k = 0; k < len; k++) {
                Nodes temp = queue.removeFirst();
                for (int i = 0; i < 4; i++) {
                    int x = temp.x + directions[i][0];
                    int y = temp.y + directions[i][1];
                    // 判断溢出
                    if (x < 0 || x > row -1 || y < 0 || y > col - 1){
                        continue;
                    }
                    // 走过了
                    if (visited[x][y]){
                        continue;
                    }
                    if (x == row - 1 && y == col - 1){
                        return temp.step + 1;
                    }
                    // 死地
                    if (grid[x][y] <= 0){
                        continue;
                    }
                    // 终点
                    Nodes tmp = new Nodes(x , y);
                    tmp.step = temp.step + 1;
                    queue.add(tmp);
                    visited[x][y] = true;

                }
            }
            dec();
        }
        return -1;

    }

    // 倒计时
    public void dec(){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j]--;
            }
        }
    }
}
class Nodes{
    int x;
    int y;
    int step;

    public Nodes(int x, int y) {
        this.x = x;
        this.y = y;
        this.step = 0;
    }
}
