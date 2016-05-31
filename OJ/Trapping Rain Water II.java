// Given n x m non-negative integers representing an elevation map 2d where the area of each cell is 1 x 1, compute how much water it is able to trap after raining.
//
// Example
// Given 5*4 matrix
//
// [12,13,0,12]
// [13,4,13,12]
// [13,8,10,12]
// [12,13,12,12]
// [13,13,13,13]
// return 14.
// -----------------------------------------------------------------------------
// 1. Find the min height of the outer-most by keeping a heap.
// 2. Look up on the four sides of each bar. Set the unvisited bar's height to be the higher one between the outer bar and the current unvisited bar.
// -----------------------------------------------------------------------------
class Cell {
    public int x, y, h;
    Cell(int x, int y, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }
}

public class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    public int trapRainWater(int[][] heights) {
        // write your code here
        if (heights == null || heights.length == 0) return 0;

        // PriorityQueue(int initialCapacity, Comparator<? super E> comparator)
        PriorityQueue<Cell> heap = new PriorityQueue<Cell>(1, new Comparator<Cell>() {
            @Override
            public int compare(Cell x, Cell y) {
                if (x.h > y.h) {
                    return 1;
                } else if (x.h < y.h) {
                    return -1;
                }
                return 0;
            }
        });

        int m = heights.length;
        int n = heights[0].length;
        int[][] visited = new int[m][n];

        // Initialising the visited matrix
        // Adding the outer-most cells to the heap
        for (int i = 0; i < m; i++) {
            heap.add(new Cell(i, 0, heights[i][0]));
            heap.add(new Cell(i, n - 1, heights[i][n - 1]));
            visited[i][0] = 1;
            visited[i][n - 1] = 1;
        }

        for (int i = 0; i < n; i++) {
            heap.add(new Cell(0, i, heights[0][i]));
            heap.add(new Cell(m - 1, i, heights[m - 1][i]));
            visited[0][i] = 1;
            visited[m - 1][i] = 1;
        }

        int sum = 0;
        while(!heap.isEmpty()) {
            Cell cur = heap.poll();

            for(int i = 0; i < 4; i++) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];
                if (x >= 0 && x < m && y >= 0 && y < n && visited[x][y] != 1) {
                    visited[x][y] = 1;
                    heap.add(new Cell(x, y, Math.max(cur.h, heights[x][y])));
                    sum += Math.max(0, cur.h - heights[x][y]);
                }
            }
        }
        return sum;
    }
}
