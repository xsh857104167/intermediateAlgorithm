package arrayAndString;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Murphy Xu
 * @create 2021-08-09 20:29
 */
public class GroupAnagrams {
    /**
     * 排序
     * 258ms, 5.00%; 40.8 MB, 96.88%
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length == 1){
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(strs[0]);
            res.add(tmp);
            return res;
        }

        char[][] chars = new char[strs.length][];
        for (int i = 0; i < strs.length; i++) {
            chars[i] = strs[i].toCharArray();
            Arrays.sort(chars[i]);
        }
        for (int i = 0; i < strs.length; i++) {
            ArrayList<String> tmp = new ArrayList<>();
            if (chars[i] == null)
                continue;
            tmp.add(strs[i]);

            for (int j = i+1; j < strs.length; j++) {
                if ((chars[j] != null) && (Arrays.equals(chars[i], chars[j]))){
                    tmp.add(strs[j]);
                    chars[j] = null;
                }
            }
            if (!tmp.isEmpty()){
                res.add(tmp);
            }
            chars[i] = null;
        }
        return res;
    }

    @Test
    public void test(){
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }
}
