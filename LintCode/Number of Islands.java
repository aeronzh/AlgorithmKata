// Given a boolean 2D matrix, find the number of islands.

// Have you met this question in a real interview? Yes
// Example
// Given graph:

// [
  // [1, 1, 0, 0, 0],
  // [0, 1, 0, 0, 1],
  // [0, 0, 0, 1, 1],
  // [0, 0, 0, 0, 0],
  // [0, 0, 0, 0, 1]
// ]
// return 3.

// Note
// 0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

public class Solution {
    
    int[] dx = {-1, 0, 0, 1};
    int[] dy = {0, -1, 1, 0};
    
    public void removeIsland(int x, int y, boolean[][] grid) {
        grid[x][y] = false;
        
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length) {
                if (grid[nextX][nextY] == true) {
                    removeIsland(nextX, nextY, grid);   
                }
            }
        }
    }   
    
    /**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
    public int numIslands(boolean[][] grid) {
        // Write your code here
        
        if (grid == null || grid.length == 0) return 0;
        
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == true) {
                    removeIsland(i, j, grid);
                    count++;
                }
            }
        }
        return count;
    }
}
