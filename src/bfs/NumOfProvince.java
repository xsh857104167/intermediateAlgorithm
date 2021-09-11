package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 547. 省份数量
 * 抽象出来就是求图的连通分量的个数
 *
 * @author Murphy Xu
 * @create 2021-09-11 15:28
 */
public class NumOfProvince {

    /**
     * dfs
     * 1ms, 96.83%; 39.3MB,56.29%
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        int nr = isConnected.length; // 省份的数量
        boolean[] visited = new boolean[nr];
        int circleNum = 0;
        for (int i = 0; i < nr; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, nr, i);
                circleNum++;
            }
        }
        return circleNum;
    }

    public void dfs(int[][] isConnected, boolean[] visited, int nr, int i) {
        for (int j = 0; j < nr; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, nr, j);
            }
        }
    }

    /**
     * bfs
     * 6ms, 13.17%; 38.4 MB, 99.86%
     * @param isConnected
     * @return
     */
    public int findCircleNum2(int[][] isConnected) {
        int provinces = isConnected.length;
        boolean[] visited = new boolean[provinces];
        int circleNum = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < provinces; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < provinces; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }
                circleNum++;
            }
        }
        return circleNum;
    }

    /**
     * 并查集
     *
     * @param isConnected
     * @return
     */
    public int findCircleNum3(int[][] isConnected) {
        int provinces = isConnected.length;
        int[] parent = new int[provinces];

        for (int i = 0; i < provinces; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < provinces; i++) {
            for (int j = i + 1; j < provinces; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int circleNum = 0;

        for (int i = 0; i < provinces; i++) {
            if (parent[i] == i) {
                circleNum++;
            }
        }
        return circleNum;
    }

    // 并
    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    // 查
    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }

}
