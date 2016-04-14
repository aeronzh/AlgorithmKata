// brute force O(n^6)
public class Solution {
    /**
     * @param matrix an integer matrix
     * @return the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSum(int[][] matrix) {
        // Write your code here
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] result = new int[2][2];

        if (maxtrix == null || matrix[0] == null) {
            return result;
        }

        int max = 0;

        // upper left corner
        for (int lx = 0; lx < m; lx++) {
            for (int ly = 0; ly < n; ly++) {
                result[0][0] = lx;
                result[0][1] = ly;
                // lower right corner
                for (int rx = lx; rx < m; rx++) {
                    for (int ry = ly; ry < n; ry++) {
                        //calculate submatrix sum
                        int sum = 0;
                        for (int i = lx; i < rx; i++) {
                            for (int j = ly; j < ry; j++) {
                                sum += matrix[i][j];
                            }
                        }
                        if (max < sum) {
                            max = sum;
                            result[1][0] = rx;
                            result[1][1] = ry;
                        }
                    }
                }
            }
        }

        return null;
    }
}

// pruned brute force O(n^4)
public class Solution {
    /**
     * @param matrix an integer matrix
     * @return the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSum(int[][] matrix) {
        // Write your code here
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] result = new int[2][2];

        if (maxtrix == null || matrix[0] == null) {
            return result;
        }

        // pre-process
        int[][] preSum = new in[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    preSum = matrix[0][j];
                } else if (j == 0) {
                    preSum = matrix[j][0];
                } else {
                    preSum = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i][j];
                }
            }
        }
        // upper left corner
        for (int lx = 0; lx < m; lx++) {
            for (int ly = 0; ly < n; ly++) {
                result[0][0] = lx;
                result[0][1] = ly;
                int sum = 0;
                // lower right corner
                for (int rx = lx; rx < m; rx++) {
                    for (int ry = ly; ry < n; ry++) {
                        //calculate submatrix sum
                        sum = preSum[rx][ry] - preSum[rx - 1][ly] - preSum[lx][ry - 1] + preSum[lx - 1][ly - 1];
                        if (sum == 0) {
                            result[1][0] = rx;
                            result[1][1] = ry;
                            return result;
                        }
                    }
                }
            }
        }

        return null;
    }
}

// O(n^3)
