// Given an integer array, adjust each integers so that the difference of every adjacent integers are not greater than a given number target.
//
// If the array before adjustment is A, the array after adjustment is B, you should minimize the sum of |A[i]-B[i]|
//
//  Notice
//
// You can assume each number in the array is a positive integer and not greater than 100.
//
// Have you met this question in a real interview? Yes
// Example
// Given [1,4,2,3] and target = 1, one of the solutions is [2,3,2,3], the adjustment cost is 2 and it's minimal.
//
// Return 2.

public class Solution {
    /**
     * @param A: An integer array.
     * @param target: An integer.
     */
    public int MinAdjustmentCost(ArrayList<Integer> A, int target) {
        // write your code here
        if (A == null || A.size() == 0) {
            return 0;
        }

        // dp[i][j]: the min adjustment cost by adjusting the ith number to j
        int[][] dp = new int[A.size()][101];
        for (int i = 0; i <= 100; i++) {
            dp[0][i] = Math.abs(i - A.get(0));
        }

        for (int i = 1; i < A.size(); i++) {
            for (int j = 0; j <= 100; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                // j: cur element to adjust to
                // k: last element after adjustment
                for (int k = Math.max(0, j - target); k <= Math.min(100, j + target); k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.abs(A.get(i) - j));
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= 100; i++) {
            min = Math.min(min, dp[A.size() - 1][i]);
        }

        return min;
    }
}
