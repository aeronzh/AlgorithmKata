class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean isWord;
    TrieNode() {
        children = new HashMap<Character, TrieNode>();
        isWord = false;
    }
}

public class WordDictionary {
    private TrieNode root;
    
    public WordDictionary() {
        root = new TrieNode();
    }
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        // Write your code here
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

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        // Write your code here
        return find(word, 0, root);
    }
    
    private boolean find(String word, int index, TrieNode cur) {
        if (index == word.length()) {
            return false;
        }
        
        if (cur.children.containsKey(word.charAt(index))) {
            if (index == word.length() - 1) {
                return cur.children.get(word.charAt(index)).isWord;
            }
            return find(word, index + 1, cur.children.get(word.charAt(index)));
        } else if (word.charAt(index) == '.') {
            for (char c : cur.children.keySet()) {
                if (index == word.length() - 1 && cur.children.get(c).isWord) {
                    return true;
                }
                if (find(word, index + 1, cur.children.get(c))) {
                    return true;
                }
            }
        }
        
        return false;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
