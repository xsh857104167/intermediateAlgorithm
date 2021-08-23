package dfs;

import org.junit.Test;

/**
 * 网易821笔试
 * @author Murphy Xu
 * @create 2021-08-23 20:11
 */
public class SailCost {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 给你一个由'0'（水）、'1'（陆地）和'2'（障碍物）组成的二维网格，再给你一个两栖交通工具，走陆地费用为1，
     * 走水路费用为2，障碍物无法通行，请你计算从网格的起始位置行驶到最终位置的最小费用。
     * 注意：尽可以水平方向和竖直方向行驶，如果无法到达目的地，则返回-1，
     * 另外：起始第一个位置不算，根据到达位置的属性来决定费用。
     * 计算最小航行费用
     * @param input int整型二维数组 二维网格
     * @return int整型
     */
    public int minSailCost (int[][] input) {
        boolean[][] visited = new boolean[input.length][input[0].length];
        return getMin(input, visited, 0, 0);
    }

    public int getMin(int[][] input, boolean[][] visited, int x, int y){
        if (x < 0 || x >= input.length
                || y < 0 || y >= input[0].length){
            return Integer.MAX_VALUE;
        }
        if (visited[x][y]){
            return Integer.MAX_VALUE;
        }
        if (input[x][y] == 2){
            return Integer.MAX_VALUE;
        }
        int result = Integer.MAX_VALUE;
        int weight = 0;

        if (input[x][y] == 0){
            weight = 2;
        }else if (input[x][y] == 1){
            weight = 1;
        }
        // 第一个点
        if (x == 0 && y == 0){
            weight = 0;
        }

        // 到达最后一个点
        if (x == input.length - 1 && y == input[0].length - 1){
            return weight;
        }

        visited[x][y] = true;
        result = Math.min(result, getMin(input, visited, x + 1, y));
        result = Math.min(result, getMin(input, visited, x - 1, y));
        result = Math.min(result, getMin(input, visited, x, y + 1));
        result = Math.min(result, getMin(input, visited, x, y - 1));
        visited[x][y] = false;
        if (x == 0 && y == 0 && result == Integer.MAX_VALUE){
            return -1;
        }

        return result == Integer.MAX_VALUE ? Integer.MAX_VALUE : result + weight;
    }

    @Test
    public void test(){
        int[][] test = {{1, 1, 1 ,1, 0},{0,1,0,1,0},{1,1,2,1,1},{0,2,0,0,1}};
        System.out.println(minSailCost(test));
    }
}
