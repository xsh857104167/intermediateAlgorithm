package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy Xu
 * @create 2021-08-17 12:46
 */
public class GenerateParenthesis {


    /**
     * 回溯
     * 0ms, 100%; 38.6 MB, 41.75%
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtracking(res, new StringBuffer(), 0, 0, n);
        return res;
    }

    public void backtracking (List<String> res, StringBuffer cur, int open, int close, int max){
        if (cur.length() ==  max * 2){
            res.add(cur.toString());
            return;
        }
        if (open < max){
            cur.append('(');
            backtracking(res, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open){
            cur.append(')');
            backtracking(res, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

}
