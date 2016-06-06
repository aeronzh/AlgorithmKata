// You are given a m x n 2D grid initialized with these three possible values.
//
// -1 - A wall or an obstacle.
// 0 - A gate.
// INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
// Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
//
// For example, given the 2D grid:
// INF  -1  0  INF
// INF INF INF  -1
// INF  -1 INF  -1
//   0  -1 INF INF
// After running your function, the 2D grid should be:
//   3  -1   0   1
//   2   2   1  -1
//   1  -1   2  -1
//   0  -1   3   4
// Hide Company Tags Google Facebook
// Hide Tags Breadth-first Search
// Hide Similar Problems (M) Surrounded Regions (M) Number of Islands (H) Shortest Distance from All Buildings

// BFS


// DFS
public class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == Integer.MAX_VALUE) {
                    rooms[i][j] = dfs(rooms, i, j);
                }
            }
        }
    }

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    private int dfs(int[][] rooms, int x, int y) {
        if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || rooms[x][y] == -1) {
            return -1;
        }

        if (rooms[x][y] == 0) {
            return 0;
        }

        int temp = rooms[x][y];
        rooms[x][y] = -1;
        int nearest = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            int distance = dfs(rooms, newX, newY);

            if (distance >= 0 && distance < nearest) {
                nearest = distance + 1;
            }
        }

        rooms[x][y] = temp;

        return nearest;
    }
}
