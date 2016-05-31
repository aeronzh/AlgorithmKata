public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        if (values == null || values.length == 0) {
            return true;
        }

        int len = values.length;
        int[][] dp = new int[len + 1][len + 1];
        boolean[][] visited = new boolean[len + 1][len + 1];

        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }

        return sum <= search(0, len - 1, values, dp, visited) * 2;
    }

    private int search(int left, int right, int[] values, int[][] dp, boolean[][] visited) {
        if (visited[left][right]) {
            return dp[left][right];
        }

        if (left > right) {
            dp[left][right] = 0;
        } else if(left == right) {
            dp[left][right] = values[left];

        } else if (left + 1 == right) {
            dp[left][right] = Math.max(values[left], values[right]);
        } else {
            int pick_left = Math.min(search(left + 2, right, values, dp, visited),
                                        search(left + 1, right - 1, values, dp, visited)) + values[left];
            int pick_right = Math.min(search(left, right - 2, values, dp, visited),
                                        search(left + 1, right - 1, values, dp, visited)) + values[right];

            dp[left][right] = Math.max(pick_left, pick_right);
        }

        visited[left][right] = true;

        return dp[left][right];
    }
}
