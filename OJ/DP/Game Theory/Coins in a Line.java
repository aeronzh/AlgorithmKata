// There are n coins in a line. Two players take turns to take one or two coins from right side until there are no more coins left. The player who take the last coin wins.
//
// Could you please decide the first play will win or lose?
//
// Have you met this question in a real interview? Yes
// Example
// n = 1, return true.
//
// n = 2, return true.
//
// n = 3, return false.
//
// n = 4, return true.
//
// n = 5, return true.
//
// Challenge
// O(n) time and O(1) memory

public class Solution {
    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        // write your code here
        boolean[] dp = new boolean[n + 1];
        boolean[] visited = new boolean[n + 1];
        return canWin(n, dp, visited);
    }

    private boolean canWin(int n, boolean[] dp, boolean[] visited) {
        if (visited[n]) {
            return dp[n];
        }

        if (n <= 0) {
            dp[n] = false;
        } else if (n == 1) {
            dp[n] = true;
        } else if (n == 2) {
            dp[n] = true;
        } else if (n == 3) {
            dp[n] = false;
        } else {
            dp[n] = (canWin(n - 2, dp, visited) && canWin(n - 3, dp, visited)) || (canWin(n - 3, dp, visited) && canWin(n - 4, dp, visited));
        }

        visited[n] = true;
        return dp[n];
    }
}

        //           5
        //     1  /     \  2
        //       4       3
        //   1/  \2   1/  \2
        //   3   2    2    1
