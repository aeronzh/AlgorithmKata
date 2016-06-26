// Given a 2D boolean matrix filled with False and True, find the largest rectangle containing all True and return its area.
//
// Have you met this question in a real interview? Yes
// Example
// Given a matrix:
//
// [
//   [1, 1, 0, 0, 1],
//   [0, 1, 0, 0, 1],
//   [0, 0, 1, 1, 1],
//   [0, 0, 1, 1, 1],
//   [0, 0, 0, 0, 1]
// ]
// return 6.

public class Solution {
    /**
     * @param matrix a boolean 2D matrix
     * @return an integer
     */
    public int maximalRectangle(boolean[][] matrix) {
        // Write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] height = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]) {
                    height[i][j] = i == 0 ? 1 : height[i - 1][j] + 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < m; i++) {
            max = Math.max(max, largestRecArea(height[i]));
        }

        return max;
    }

    private int largestRecArea(int[] height) {
        Stack<Integer> stack = new Stack<Integer>(); // increasing stack
        int max = 0;
        for (int i = 0; i <= height.length; i++) {
            int cur = i == height.length ? -1 : height[i];
            while (!stack.isEmpty() && height[stack.peek()] > cur) {
                int h = height[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }

        return max;
    }
}
