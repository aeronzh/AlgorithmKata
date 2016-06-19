// Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
// The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
// Note that you can only put the bomb at an empty cell.
//
// Example:
// For the given grid
//
// 0 E 0 0
// E 0 W E
// 0 E 0 0
//
// return 3. (Placing a bomb at (1,1) kills 3 enemies)
// Credits:
// Special thanks to @memoryless for adding this problem and creating all test cases.
//
// Hide Company Tags Google
// Hide Tags Dynamic Programming

public class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int[][] enemies = new int[m][n]; // no. of killed enemies

        int killed = 0;

        // left to right
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || grid[i][j] == 'W') {
                    // wall
                    killed = 0;
                }
                if (grid[i][j] == 'E') {
                    // enemy
                     killed++;
                } else {
                    // empty
                    enemies[i][j] += killed;
                }
            }
        }

        // right to left
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (j == n - 1 || grid[i][j] == 'W') {
                    // wall
                    killed = 0;
                }
                if (grid[i][j] == 'E') {
                    // enemy
                     killed++;
                } else {
                    // empty
                    enemies[i][j] += killed;
                }
            }
        }

        // top down
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0 || grid[j][i] == 'W') {
                    // wall
                    killed = 0;
                }
                if (grid[j][i] == 'E') {
                    // enemy
                    killed++;
                } else {
                    // empty
                    enemies[j][i] += killed;
                }
            }
        }

        int max = 0;

        // bottom up
        for (int i = 0; i < n; i++) {
            for (int j = m - 1; j >= 0; j--) {
                if (j == m - 1 || grid[j][i] == 'W') {
                    // wall
                    killed = 0;
                }
                if (grid[j][i] == 'E') {
                    // enemy
                    killed++;
                } else {
                    // empty
                    enemies[j][i] += killed;
                }
                max = Math.max(max, enemies[j][i]);
            }
        }

        return max;
    }
}
