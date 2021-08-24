package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Murphy Xu
 * @create 2021-08-24 17:46
 */
public class Maze3 {
    int rows;
    int cols;
    boolean[][] visited;
    char[][] matrix;
    Node S;
    Node T;
    Node node; // 临时值
    int X[] = {0, 0, -1, 1};
    int Y[] = {1, -1, 0, 0};

    /**
     * 判断位置(x, y)是否需要访问
     * @param x
     * @param y
     * @return
     */
    public boolean judge(int x, int y){
        if (x < 0 || x >= rows || y < 0 || y >= cols){
            return false;
        }

        if (visited[x][y] || matrix[x][y] == '#'){
            return false;
        }
        return true;
    }

    public int bfs(){
        Deque<Node> queue = new LinkedList<>();
        queue.add(S);
        visited[S.x][S.y] = true;
        while (!queue.isEmpty()) {
            Node top = queue.poll();
            if (top.x == T.x && top.y == T.y){
                return top.step;
            }
            // 遍历当前节点的邻接点
            for (int i = 0; i < 4; i++) {
                int nx = top.x + X[i];
                int ny = top.y + Y[i];
                if (judge(nx, ny)){
                    node.x = nx;
                    node.y = ny;
                    node.step = top.step + 1;
                    queue.add(node);
                    visited[nx][ny] = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        Maze3 main = new Maze3();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = null;
        while ((str1 = br.readLine()) != null){
            String[] arr = str1.split(" ");
            main.rows = Integer.parseInt(arr[0]);
            main.cols = Integer.parseInt(arr[1]);

            main.matrix = new char[main.rows][main.cols];
            main.visited = new boolean[main.rows][main.cols];
            for (int i = 0; i < main.rows; i++) {
                main.matrix[i] = br.readLine().toCharArray();
            }
            for (int i = 0; i < main.rows; i++) {
                for (int j = 0; j < main.cols; j++) {
                    if (main.matrix[i][j] == 'S'){
                        main.S = new Node(i, j);
                    }
                    if (main.matrix[i][j] == 'T'){
                        main.T = new Node(i, j);
                    }
                }
            }
            main.node = new Node();
            System.out.println(main.bfs());
        }
    }
}

class Node{
    int x, y;
    int step;

    public Node(){

    }

    public Node(int x, int y){
        this.x = x;
        this.y = y;
        this.step = 0;
    }
}