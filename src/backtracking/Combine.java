package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy Xu
 * @create 2021-08-23 16:00
 */
public class Combine {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    /**
     * 回溯
     * 17ms, 46.50%; 39.7 MB, 61.23%
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);
        return res;
    }

    public void backtracking(int n, int k, int cur){
        if (path.size() == k){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = cur; i <= n; i++) {
            path.add(i);
            backtracking(n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
