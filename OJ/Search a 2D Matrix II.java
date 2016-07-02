// Write an efficient algorithm that searches for a value in an m x n matrix, return the occurrence of it.
//
// This matrix has the following properties:
//
// Integers in each row are sorted from left to right.
// Integers in each column are sorted from up to bottom.
// No duplicate integers in each row or column.
// Have you met this question in a real interview? Yes
// Example
// Consider the following matrix:
//
// [
//   [1, 3, 5, 7],
//   [2, 4, 7, 8],
//   [3, 5, 9, 10]
// ]
// Given target = 3, return 2.
//
// Challenge
// O(m+n) time and O(1) extra space

// note this algorithm only works if there are no duplicates in both rows and columns
public class Solution {
    /**
     * @param matrix: A list of lists of integers
     * @param: A number you want to search in the matrix
     * @return: An integer indicate the occurrence of target in the given matrix
     */
    public int searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int row = matrix.length - 1;
        int col = matrix[0].length - 1;

        int count = 0;
        int x = row;
        int y = 0;

        while (x >= 0 && y <= col) {
            if (matrix[x][y] == target) {
                count++;
                x--;
                y++;
            } else if (matrix[x][y] < target) {
                y++;
            } else {
                x--;
            }
        }

        return count;
    }
}
