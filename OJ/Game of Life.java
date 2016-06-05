// According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

// Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

// Any live cell with fewer than two live neighbors dies, as if caused by under-population.
// Any live cell with two or three live neighbors lives on to the next generation.
// Any live cell with more than three live neighbors dies, as if by over-population..
// Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
// Write a function to compute the next state (after one update) of the board given its current state.

// Follow up: 
// Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
// In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
// Credits:
// Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

// Hide Company Tags Dropbox Google Snapchat
// Hide Tags Array
// Hide Similar Problems (M) Set Matrix Zeroes

public class Solution {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        int m = board.length;
        int n = board[0].length;
        
        int[] dx = {0, 0, 1, -1, -1, 1, -1, 1};
        int[] dy = {1, -1, 0, 0, -1, 1, 1, -1};
        
        int[][] next = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                for (int k = 0; k < 8; k++) {
                    int newX = i + dx[k];
                    int newY = j + dy[k];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && board[newX][newY] == 1) {
                        count++;
                    }
                }
                
                if (board[i][j] == 1) {
                    if (count < 2) {
                        next[i][j] = 0;
                    } else if (count == 2 || count == 3) {
                        next[i][j] = 1;
                    } else if (count > 3) {
                        next[i][j] = 0;
                    }
                } else {
                    if (count == 3) {
                        next[i][j] = 1;
                    }
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = next[i][j];
            }
        }
    }
}

// no extra space
public class Solution {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        int m = board.length;
        int n = board[0].length;
        
        int[] dx = {0, 0, 1, -1, -1, 1, -1, 1};
        int[] dy = {1, -1, 0, 0, -1, 1, 1, -1};
        
        //    before | after
        // 0:  dead  | dead
        // 1:  alive | alive
        // 2:  dead  | alive
        // 3:  alive | dead
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                for (int k = 0; k < 8; k++) {
                    int newX = i + dx[k];
                    int newY = j + dy[k];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && (board[newX][newY] == 1 || board[newX][newY] == 3)) {
                        count++;
                    }
                }
                
                if (board[i][j] == 1 || board[i][j] == 3) {
                    if (count < 2) {
                        board[i][j] = 3;
                    } else if (count == 2 || count == 3) {
                        board[i][j] = 1;
                    } else if (count > 3) {
                        board[i][j] = 3;
                    }
                } else {
                    if (count == 3) {
                        board[i][j] = 2;
                    }
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1 || board[i][j] == 2) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }
}