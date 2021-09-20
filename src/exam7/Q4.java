package exam7;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 网易9.18
 * @author Murphy Xu
 * @create 2021-09-18 19:16
 */
public class Q4 {

    public char[][] maze;
    public int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public boolean[][] visited;
    public Deque<Node> queue;
    public int n, a, b;
    public Node[] stars;

    public static void main(String[] args) {
        Q4 main = new Q4();
        Scanner in = new Scanner(System.in);
        String[] ins = in.nextLine().split(" ");
        main.n = Integer.parseInt(ins[0]);
        main.a = Integer.parseInt(ins[1]);
        main.b = Integer.parseInt(ins[2]);

        main.queue = new LinkedList<>();
        main.visited = new boolean[main.n][main.n];

        main.maze = new char[main.n][];
        for (int i = 0; i < main.n; i++) {
            main.maze[i] = in.nextLine().toCharArray();
        }
        // 找到*的位置
        main.stars = new Node[2];
        int t = 0;
        for (int i = 0; i < main.n; i++) {
            for (int j = 0; j < main.n; j++) {
                if (main.maze[i][j] == '*'){
                    main.stars[t++] = new Node(i, j);
                }
            }
        }
        main.queue.add(new Node(0, 0));
        main.visited[0][0] = true;
        System.out.println(main.bfs());
    }

    public int bfs(){
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            int len = 4;
            int[][] dirs = new int[5][2];
            Node temp = queue.removeFirst();
            if (maze[temp.x][temp.y] == '*'){
                len++;
                if (temp.x == stars[0].x && temp.y == stars[0].y){
                    dirs[4][0] = stars[1].x;
                    dirs[4][1] = stars[1].y;
                }else{
                    dirs[4][0] = stars[0].x;
                    dirs[4][1] = stars[0].y;
                }
            }
            for (int i = 0; i < 4; i++) {
                dirs[i][0] = temp.x + directions[i][0];
                dirs[i][1] = temp.y + directions[i][1];
            }

            for (int i = 0; i < len; i++) {
                int x = dirs[i][0];
                int y = dirs[i][1];
                if (x < 0 || x > n - 1 || y < 0 || y > n - 1){
                    continue;
                }
                // 走过了
                if (visited[x][y]){
                    continue;
                }
                // 到终点了
                if (x == n - 1 && y == n - 1){
                    if (i == 4){
                        min = Math.min(min, temp.cost + b);
                    }else {
                        if (maze[x][y] == '#'){
                            min = Math.min(min, temp.cost + a);
                        }else{
                            min = Math.min(min, temp.cost);
                        }
                    }
                    continue;
                }

                Node node = new Node(x, y);
                if (i == 4){
                    node.cost = temp.cost + b;
                }else {
                    if (maze[x][y] == '#'){
                        node.cost = temp.cost + a;
                    }else{
                        node.cost = temp.cost;
                    }
                }
                queue.add(node);
                visited[x][y] = true;
            }
        }
        return min;
    }

}

class Node{
    int x, y;
    int cost;

    public Node(){

    }

    public Node(int x, int y){
        this.x = x;
        this.y = y;
        this.cost = 0;
    }
}