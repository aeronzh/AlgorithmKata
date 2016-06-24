// Given n kind of items with size Ai and value Vi( each item has an infinite number available) and a backpack with size m. What's the maximum value can you put into the backpack?
//
//  Notice
//
// You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.
//
// Have you met this question in a real interview? Yes
// Example
// Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 15.

// similar to coin change!
public class Solution {
    /**
     * @param A an integer array
     * @param V an integer array
     * @param m an integer
     * @return an array
     */
    public int backPackIII(int[] A, int[] V, int m) {
        // Write your code here
        // dp[i][j][k]: in the first i items, the max value of size k

        int[] dp = new int[m + 1];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < m + 1; j++) {
                if (j >= A[i]) {
                    dp[j] = Math.max(dp[j], dp[j - A[i]] + V[i]);
                }
            }
        }

        return dp[m];
    }
}

public class Solution {
    /**
     * @param A an integer array
     * @param V an integer array
     * @param m an integer
     * @return an array
     */
    public int backPackIII(int[] A, int[] V, int m) {
        // Write your code here

        int[][] dp = new int[A.length + 1][m + 1];

        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (j >= A[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j],
                                Math.max(dp[i - 1][j - A[i - 1]] + V[i - 1],
                                        dp[i][j - A[i - 1]] + V[i - 1]));
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[A.length][m];
    }
}
