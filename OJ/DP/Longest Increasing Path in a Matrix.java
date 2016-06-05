// Given an integer matrix, find the length of the longest increasing path.

// From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

// Example 1:

// nums = [
//   [9,9,4],
//   [6,6,8],
//   [2,1,1]
// ]
// Return 4
// The longest increasing path is [1, 2, 6, 9].

// Example 2:

// nums = [
//   [3,4,5],
//   [3,2,6],
//   [2,2,1]
// ]
// Return 4
// The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

// Credits:
// Special thanks to @dietpepsi for adding this problem and creating all test cases.

// Hide Company Tags Google
// Hide Tags Depth-first Search Topological Sort Memoization

public class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int max = 1;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, dfs(matrix, i, j, dp, visited));
            }
        }
        
        return max;
    }
    
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    
    private int dfs(int[][] matrix, int x, int y, int[][] dp, boolean[][] visited) {
        if (visited[x][y]) {
            return dp[x][y];
        }
        
        int max = 1;
        int m = matrix.length;
        int n = matrix[0].length;
        
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            
            if (newX >= 0 && newX < m && newY >= 0 && newY < n && matrix[x][y] < matrix[newX][newY]) {
                max = Math.max(max, dfs(matrix, newX, newY, dp, visited) + 1);
            }
        }
        
        visited[x][y] = true;
        dp[x][y] = max;
        
        return max;
    }
}