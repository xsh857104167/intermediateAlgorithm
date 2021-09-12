package treeAndGraph;

import java.util.*;

/**
 * @author Murphy Xu
 * @create 2021-09-12 21:39
 */
public class MinimumLengthEncoding {
    /**
     * 存储后缀
     * @param words array of word
     * @return the minimum length of the encoding
     */
    public int minimumLengthEncoding(String[] words) {
        Set<String> good = new HashSet<>(Arrays.asList(words));

        for (String word : words) {
            for (int k = 1; k < word.length(); k++) {
                good.remove(word.substring(k));
            }
        }
        int ans = 0;
        for(String word : good){
            ans += word.length() + 1;
        }
        return ans;
    }

    /**
     * 字典树
     * @param words array of word
     * @return the minimum length of the encoding
     */
    public int minimumLengthEncoding2(String[] words) {
        TrieNode trie = new TrieNode();
        Map<TrieNode, Integer> nodes = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode cur = trie;

            for (int j = word.length() - 1; j >= 0; j--) {
                cur = cur.get(word.charAt(j));
            }
            nodes.put(cur, i);
        }
        int ans = 0;
        for (TrieNode node : nodes.keySet()){
            if (node.count == 0){
                ans += words[nodes.get(node)].length() + 1;
            }
        }
        return ans;
    }
}

class TrieNode {
    TrieNode[] children;
    int count;

    public TrieNode() {
        children = new TrieNode[26];
        count = 0;
    }

    public TrieNode get (char c){
        if (children[c - 'a'] == null){
            children[c - 'a'] = new TrieNode();
        }
        return children[c - 'a'];
    }
}
