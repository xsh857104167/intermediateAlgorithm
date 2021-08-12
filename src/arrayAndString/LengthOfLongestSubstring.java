package arrayAndString;

import java.util.HashSet;
import java.util.Set;

/**
 * 无重复字符的最长字串
 * @author Murphy Xu
 * @create 2021-08-12 15:28
 */
public class LengthOfLongestSubstring {
    /**
     * 滑动窗口
     * 遍历所有的字符，计算以此字符开始的最长不重复字串
     * 7ms, 63.72%; 38.6MB, 45.95%
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;

        Set<Character> occ = new HashSet<>();
        int len = s.length();
        int right = -1;  // 窗口的右指针
        int res = 0;

        for (int i = 0; i < len; i++) {  // i表示起始位置
            if (i != 0){
                occ.remove(s.charAt(i-1));
            }
            while (right + 1 < len && !occ.contains(s.charAt(right + 1))){
                occ.add(s.charAt(right + 1));
                right ++;
            }
            res = Math.max(res, right - i + 1);
        }
        return res;
    }
}
