package bfs;

/**
 * @author Murphy Xu
 * @create 2021-08-25 16:29
 */
public class SailCost {
    int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int[][] grid;
    int[][] visited;

    public int minSailCost (int[][] input) {
        int m = input.length;
        int n = input[0].length;
        grid = input;
        boolean[][] visited = new boolean[m][n];
        return 0;
    }

    public void bfs(int x, int y){

    }
}


class Node2 {
    int x;
    int y;
    int value;

    public Node2() {
    }

    public Node2(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}