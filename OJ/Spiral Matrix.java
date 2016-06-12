// Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

// Have you met this question in a real interview? Yes
// Example
// Given the following matrix:

// [
//  [ 1, 2, 3 ],
//  [ 4, 5, 6 ],
//  [ 7, 8, 9 ]
// ]
// You should return [1,2,3,6,9,8,7,4,5].

public class Solution {
    /**
     * @param matrix a matrix of m x n elements
     * @return an integer list
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        // Write your code here
        List<Integer> result = new ArrayList<Integer>();
        
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = m - 1;
        int i = 0;
        int j = 0;
        int direction = 0;
        
        while (left <= right && top <= bottom) {
            result.add(matrix[i][j]);
            
            if (direction == 0) {
                // going right
                j++;
                if (j == right + 1) {
                    direction = (direction + 1) % 4;
                    i++;
                    j--;
                    top++;
                }
            } else if (direction == 1) {
                // going down
                i++;
                if (i == bottom + 1) {
                    direction = (direction + 1) % 4;
                    i--;
                    j--;
                    right--;
                }
            } else if (direction == 2) {
                // going left
                j--;
                if (j == left - 1) {
                    direction = (direction + 1) % 4;
                    i--;
                    j++;
                    bottom--;
                }
            } else if (direction == 3) {
                // going up
                i--;
                if (i == top - 1) {
                    direction = (direction + 1) % 4;
                    i++;
                    j++;
                    left++;
                }
            }
        }
        
        return result;
    }
}