public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        // write your code here
        ArrayList<String> result = new ArrayList<String>();
        if (board == null || board.length == 0 || board[0].length == 0) {
            return result;
        }

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                search(board, i, j, result, trie.root);
            }
        }

        return result;
    }

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    private void search(char[][] board, int x, int y, ArrayList<String> result, TrieNode root) {
        if (root.isWord) {
            if (!result.contains(root.word)) {
                result.add(root.word);
            }
        }

        if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] != 0 && root != null) {

            for (int i = 0; i < 4; i++) {
                if (root.children.containsKey(board[x][y])) {
                    char c = board[x][y];
                    board[x][y] = 0;
                    search(board, x + dx[i], y + dy[i], result, root.children.get(c));
                    board[x][y] = c;
                }
            }
        }
    }
}

class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean isWord;
    String word;

    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        isWord = false;
        word = "";
    }
}

class Trie {
    TrieNode root;

    public Trie() {
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
        cur.word = word;
    }
}
