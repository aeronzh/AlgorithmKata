public class Solution {
    /**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    public boolean exist(char[][] board, String word) {
        // write your code here
        if (board == null || board.length == 0 || board[0].length == 0 || word == null) {
            return false;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    private boolean search(char[][] board, String word, int x, int y, int pos) {
        if (pos == word.length()) {
            return true;
        }

        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != word.charAt(pos)) {
            return false;
        }

        board[x][y] = '#';

        for (int i = 0; i < 4; i ++) {
            if (search(board, word, x + dx[i], y + dy[i], pos + 1)) {
                return true;
            }
        }

        board[x][y] = word.charAt(pos);

        return false;
    }
}
