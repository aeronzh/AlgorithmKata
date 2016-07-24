// http://www.1point3acres.com/bbs/thread-161616-1-1.html
// [1,1,2]
// [1,0,3]
// [0,2,3]
// Given start position at [0][0], find the perimeter of the same color i.e. return 3

// tested
class Solution {
    int m;
    int n;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    public int calculatePerimeter(int[][] grid, int x, int y) {
        m = grid.length;
        n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        return dfs(grid, x, y, grid[x][y], visited);
    }

    private int dfs(int[][] grid, int x, int y, int color, boolean[][] visited) {
        visited[x][y] = true;
        boolean isEdge = false;
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
                isEdge = true;
            } else {
                if (visited[newX][newY]) {
                    continue;
                }
                if (grid[newX][newY] == color) {
                    count += dfs(grid, newX, newY, color, visited);
                } else {
                    isEdge = true;
                }
            }
        }

        if (isEdge) {
            count++;
        }

        return count;
    }
}
