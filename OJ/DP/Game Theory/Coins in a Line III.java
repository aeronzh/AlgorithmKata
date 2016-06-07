// There are n coins in a line. Two players take turns to take a coin from one of the ends of the line until there are no more coins left. The player with the larger amount of money wins.
//
// Could you please decide the first player will win or lose?
//
// Have you met this question in a real interview? Yes
// Example
// Given array A = [3,2,2], return true.
//
// Given array A = [1,2,4], return true.
//
// Given array A = [1,20,4], return false.
//
// Challenge
// Follow Up Question:
//
// If n is even. Is there any hacky algorithm that can decide whether first player will win or lose in O(1) memory and O(n) time?

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

        int[][] dp = new int[values.length + 1][values.length + 1];
        boolean[][] visited = new boolean[values.length + 1][values.length + 1];

        return sum / 2 < search(0, values.length - 1, values, dp, visited);
    }

    private int search(int left, int right, int[] values, int[][] dp, boolean[][] visited) {
        if (visited[left][right]) {
            return dp[left][right];
        }

        if (left > right) {
            dp[left][right] = 0;
        } else if (left == right) {
            dp[left][right] = values[left];
        } else if (left + 1 == right) {
            dp[left][right] = Math.max(values[left], values[right]);
        } else {
            int pick_left = Math.min(search(left + 2, right, values, dp, visited),  search(left + 1, right - 1, values, dp, visited)) + values[left];
            int pick_right = Math.min(search(left, right - 2, values, dp, visited), search(left + 1, right - 1, values, dp, visited)) + values[right];
            dp[left][right] = Math.max(pick_left, pick_right);
        }

        visited[left][right] = true;

        return dp[left][right];
    }
}
