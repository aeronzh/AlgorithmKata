public class Solution {
    /**
     * @param A an integer array
     * @return an integer
     */
    public int stoneGame(int[] A) {
        // Write your code
        if (A == null || A.length == 0) {
            return 0;
        }

        int[][] dp = new int[A.length][A.length];
        boolean[][] visited = new boolean[A.length][A.length];
        int[] sum = new int[A.length + 1];

        for (int i = 0; i < A.length; i++) {
            for (int j = 1; j < A.length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < A.length; i++) {
            dp[i][i] = 0;
            visited[i][i] = true;
            sum[i + 1] = sum[i] + A[i];
        }

        return search(0, A.length - 1, dp, sum, visited);
    }

    private int search(int start, int end, int[][] dp, int[] sum, boolean[][] visited) {
        if (visited[start][end]) {
            return dp[start][end];
        }

        int min = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
            int left = search(start, i, dp, sum, visited);
            int right = search(i + 1, end, dp, sum, visited);
            int now = sum[end + 1] - sum[start];

            min = Math.min(min, left + now + right);
        }

        dp[start][end] = min;
        visited[start][end] = true;

        return min;
    }
}
