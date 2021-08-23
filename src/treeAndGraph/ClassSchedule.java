package treeAndGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Murphy Xu
 * @create 2021-08-23 13:45
 */
public class ClassSchedule {
    /**
     * 9ms, 20%; 38.9 MB, 67.92%
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Course[] courses = new Course[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course(i);
        }

        for (int i = 0; i < prerequisites.length; i++) {
            courses[prerequisites[i][0]].pre++;
            courses[prerequisites[i][1]].after.add(prerequisites[i][0]);
        }

        for (int i = 0; i < numCourses; i++) {
            int j = 0;
            for (; j < numCourses; j++) {
                if (courses[j].pre == 0){
                    courses[j].pre--; //  置为-1
                    break;
                }
            }
            if (j < numCourses){
                for (int k = 0; k < courses[j].after.size(); k++) {
                    courses[courses[j].after.get(k)].pre--;
                }
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].pre != -1){
                return false;
            }
        }
        return true;
    }

    List<List<Integer>> edges; // 出节点列表
    int[] indeg; // 入度
    /**
     * 广度优先
     * 5ms, 66.09%; 39.3 MB, 25.43%
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indeg[i] == 0){
                queue.offer(i);
            }
        }
        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for (int v : edges.get(u)) {
                --indeg[v];
                if (indeg[v] == 0){
                    queue.offer(v);
                }
            }
        }
        return visited == numCourses;
    }


    int[] visited;
    boolean valid = true;
    /**
     * 深度优先
     * 3ms, 98.65%; 38.9MB, 67.92%
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; i++) {
            if (visited[i] == 0){
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs (int u){
        visited[u] = 1; // 正在搜索
        for (int v : edges.get(u)) {
            if (visited[v] == 0){
                dfs(v);
                if (!valid){
                    return;
                }
            }else if (visited[v] == 1){
                valid = false;
                return;
            }
        }
        visited[u] = 2; // 搜索完成
    }
}
class Course {
    int num;
    int pre; // 入度
    List<Integer> after;

    public Course(int num){
        this.num = num;
        this.pre = 0;
        this.after = new ArrayList<>();
    }
}