// Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in ZigZag-order.

// Have you met this question in a real interview? Yes
// Example
// Given a matrix:

// [
//   [1, 2,  3,  4],
//   [5, 6,  7,  8],
//   [9,10, 11, 12]
// ]
// return [1, 2, 5, 9, 6, 3, 4, 7, 10, 11, 8, 12]

public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @return: an array of integers
     */ 
    public int[] printZMatrix(int[][] matrix) {
        // write your code here
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[] result = new int[m * n];
        result [0] = matrix[0][0];
        int index = 1;
        
        int x = 0;
        int y = 0;
        int dx = -1;
        int dy = 1;
        
        while (index < result.length) {
            if (x + dx >= 0 && x + dx < m && y + dy >= 0 && y + dy < n) {
                x += dx;
                y += dy;
            } else {
                if (dx == -1 && dy == 1) {
                    // upper right
                    if (y + dy < n) {
                        y++;
                    } else {
                        x++;
                    }
                } else {
                    // lower left
                    if (x + dx < m) {
                        x++;
                    } else {
                        y++;
                    }
                }
                
                dx *= -1;
                dy *= -1;
            }
            
            result[index] = matrix[x][y];
            index++;
        }
        
        return result;
    }
}