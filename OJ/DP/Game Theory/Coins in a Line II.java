// There are n coins with different value in a line. Two players take turns to take one or two coins from left side until there are no more coins left. The player who take the coins with the most value wins.
//
// Could you please decide the first player will win or lose?
//
// Have you met this question in a real interview? Yes
// Example
// Given values array A = [1,2,2], return true.
//
// Given A = [1,2,4], return false.

public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        if (values == null || values.length == 0) {
            return false;
        }

        int sum = 0;
        for (int v : values) {
            sum += v;
        }

        int[] dp = new int[values.length + 1];
        boolean[] visited = new boolean[values.length + 1];

        return sum / 2 < search(values.length, values, dp, visited);
    }

    private int search(int n, int[] values, int[] dp, boolean[] visited) {
        if (visited[n]) {
            return dp[n];
        }

        if (n <= 0) {
            dp[n] = 0;
        } else if (n == 1) {
            dp[n] = values[values.length - 1];
        } else if (n == 2) {
            dp[n] = values[values.length - 1] + values[values.length - 2];
        } else if (n == 3) {
            dp[n] = values[values.length - 2] + values[values.length - 3];
        } else {
            dp[n] = Math.max(
                    Math.min(search(n - 2, values, dp, visited), search(n - 3, values, dp, visited)) + values[values.length - n],
                    Math.min(search(n - 3, values, dp, visited), search(n - 4, values, dp, visited)) + values[values.length - n]
                                             + values[values.length - n + 1]);
        }

        visited[n] = true;
        return dp[n];
    }
}
