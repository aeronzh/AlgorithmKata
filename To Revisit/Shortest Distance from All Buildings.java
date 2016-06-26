// You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
//
// Each 0 marks an empty land which you can pass by freely.
// Each 1 marks a building which you cannot pass through.
// Each 2 marks an obstacle which you cannot pass through.
// For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
//
// 1 - 0 - 2 - 0 - 1
// |   |   |   |   |
// 0 - 0 - 0 - 0 - 0
// |   |   |   |   |
// 0 - 0 - 1 - 0 - 0
// The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
//
// Note:
// There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
//
// Hide Company Tags Google Zenefits
// Hide Tags Breadth-first Search
// Hide Similar Problems (M) Walls and Gates (H) Best Meeting Point

public class Solution {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        int[][] distances = new int[m][n];
        int[][] reach = new int[m][n];
        int numOfBuildings = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, distances, reach, i, j);
                    numOfBuildings++;
                }
            }
        }

        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (reach[i][j] == numOfBuildings) {
                    shortest = Math.min(shortest, distances[i][j]);
                }
            }
        }

        return shortest == Integer.MAX_VALUE ? -1 : shortest;
    }

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    private void bfs(int[][] grid, int[][] distances, int[][] reach, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(convert(x, y, n));
        boolean[][] visited = new boolean[m][n];
        visited[x][y] = true;
        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;

            for (int i = 0; i < size; i++) {
                int pos = queue.poll();
                x = pos / n;
                y = pos % n;
                for (int j = 0; j < 4; j++) {
                    int newX = x + dx[j];
                    int newY = y + dy[j];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] != 1 && grid[newX][newY] != 2 && !visited[newX][newY]) {
                        distances[newX][newY] += distance;
                        reach[newX][newY]++;
                        visited[newX][newY] = true;
                        queue.offer(convert(newX, newY, n));
                    }
                }
            }
        }
    }

    private int convert(int row, int col, int n) {
        return row * n + col;
    }
}
