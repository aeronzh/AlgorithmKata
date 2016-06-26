public class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (words == null || words.length == 0) {
            return result;
        }

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Trie trie = new Trie();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
            trie.insert(words[i]);
        }

        for (int i = 0; i < words.length; i++) {
            String reverse = new StringBuilder(words[i]).reverse().toString();
            if (trie.search(reverse)) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                list.add(map.get(reverse));
                result.add(list);
            }
        }

        return result;
    }
}

class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean isWord;

    TrieNode() {
        children = new HashMap<Character, TrieNode>();
        isWord = false;
    }
}

class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode());
            }
            cur = cur.children.get(c);
        }

        cur.isWord = true;
    }

    public boolean search(String word) {
        TrieNode cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                return false;
            }
            cur = cur.children.get(c);
        }

        return true;
    }
}
