package arrayAndString;

import org.junit.Test;

import javax.sql.rowset.spi.SyncResolver;
import java.util.*;

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

    /**
     * 官方答案
     * 排序 + map
     * 6ms, 98.17%; 41.2MB, 71.42%
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str:strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    /**
     * 官方答案
     * 计数 + map
     * 10ms, 33.30%; 41.8MB, 20.62%
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams3(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str:strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a'] ++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0){
                    sb.append((char)('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    @Test
    public void test(){
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams2(strs));
    }
}
