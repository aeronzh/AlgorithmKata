public class Solution {
    /**
     * @param nums a list of integer
     * @return an integer, maximum coins
     */
    public int maxCoins(int[] nums) {
        // Write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] coins = new int[nums.length + 2];
        coins[0] = coins[nums.length + 1] = 1;
        for (int i = 1; i < nums.length + 1; i++) {
            coins[i] = nums[i - 1];
        }

        int[][] dp = new int[nums.length + 2][nums.length + 2];
        boolean[][] visited = new boolean[nums.length + 2][nums.length + 2];

        return search(1, nums.length, dp, visited, coins);
    }

    private int search(int start, int end, int[][] dp, boolean[][] visited, int[] coins) {
        if (visited[start][end]) {
            return dp[start][end];
        }

        // if (start > end) {
        //     return 0;
        // }

        int max = 0; // Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            int left = search(start, i - 1, dp, visited, coins);
            int right = search(i + 1, end , dp, visited, coins);
            int now = coins[start - 1] * coins[i] * coins[end + 1];
            max = Math.max(max, left + now + right);
        }

        dp[start][end] = max;
        visited[start][end] = true;

        return max;
    }
}
