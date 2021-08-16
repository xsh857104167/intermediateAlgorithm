package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy Xu
 * @create 2021-08-16 15:13
 */
public class LetterCombinations {

    List<String> res = new ArrayList<>();
    StringBuffer path = new StringBuffer();
    char[] nums;

    /**
     * 回溯
     * 0ms,100%; 37.1MB
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }
        char[][] map = new char[11][];
        map[2] = new char[]{'a', 'b', 'c'};
        map[3] = new char[]{'d', 'e', 'f'};
        map[4] = new char[]{'g', 'h', 'i'};
        map[5] = new char[]{'j', 'k', 'l'};
        map[6] = new char[]{'m', 'n', 'o'};
        map[7] = new char[]{'p', 'q', 'r', 's'};
        map[8] = new char[]{'t', 'u', 'v'};
        map[9] = new char[]{'w', 'x', 'y', 'z'};

        nums = digits.toCharArray();

        backtracking(map, digits.length(), 0);

        return res;
    }

    public void backtracking(char[][] map, int k, int cur) {
        if (path.length() == k) {
            String tmp = path.toString();
            res.add(tmp);
            return;
        }

        char[] chars = map[nums[cur] - '0'];

        for (char c : chars) {
            path.append(c);
            backtracking(map, k, cur + 1);
            path.deleteCharAt(path.length() - 1);
        }

    }

}
