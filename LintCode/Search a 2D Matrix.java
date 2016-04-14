// Write an efficient algorithm that searches for a value in an m x n matrix.
//
// This matrix has the following properties:
//
// Integers in each row are sorted from left to right.
// The first integer of each row is greater than the last integer of the previous row.
//
// Example
// Consider the following matrix:
//
// [
//     [1, 3, 5, 7],
//     [10, 11, 16, 20],
//     [23, 30, 34, 50]
// ]
// Given target = 3, return true.
//
// Challenge
// O(log(n) + log(m)) time
// -----------------------------------------------------------------------------
// Binary search once
// Transform 2D matrix into a 1D array
// Note: matrix[mid / column][mid % column]
// -----------------------------------------------------------------------------
public class Solution {
    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix == null || matrix.length == 0) return false;
        if (matrix[0] == null || matrix[0].length == 0) return false;

        int row = matrix.length;
        int column = matrix[0].length;

        int start = 0;
        int end = row * column - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int midNum = matrix[mid / column][mid % column];
            if (midNum == target) {
                return true;
            } else if (midNum < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (matrix[start / column][start % column] == target) {
            return true;
        } else if (matrix[end / column][end % column] == target) {
            return true;
        }
        return false;
    }
}
// -----------------------------------------------------------------------------
// Binary search twice
// 1. Search for row by the first element of each row
// 2. Search for target in the row found in step one
// -----------------------------------------------------------------------------
public class Solution {
    /**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix == null || matrix.length == 0) return false;
        if (matrix[0] == null || matrix[0].length == 0) return false;

        int row = matrix.length;
        int column = matrix[0].length;

        int start = 0, mid = 0, end = row - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (matrix[end][0] <= target) {
            row = end;
        } else if (matrix[start][0] <= target) {
            row = start;
        } else {
            return false;
        }

        start = 0;
        end = column - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (matrix[row][end] == target) {
            return true;
        } else if (matrix[row][start] == target) {
            return true;
        }
        return false;
    }
}
