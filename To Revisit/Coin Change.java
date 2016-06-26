// Bottom up DP
public class Solution {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }

        int[] dp = new int[amount + 1];

        for (int i = 1; i < amount + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}

// DFS + Memoization
public class Solution {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        return dfs(coins, amount, map);
    }

    private int dfs(int[] coins, int amount, HashMap<Integer, Integer> map) {
        if (map.containsKey(amount)) {
            return map.get(amount);
        }

        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return -1;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int count = dfs(coins, amount - coins[i], map);

            if (count >= 0) {
                min = Math.min(min, count + 1);
            }
        }

        min = min == Integer.MAX_VALUE ? -1 : min;
        map.put(amount, min);

        return min;
    }
}

// BFS
public class Solution {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount == 0) {
            return 0;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(amount);
        int min = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            min++;

            for (int i = 0; i < size; i++) {
                int left = queue.poll();
                for (int coin : coins) {
                    if (left - coin == 0) {
                        return min;
                    }
                    if (left - coin > 0) {
                        queue.offer(left - coin);
                    }
                }
            }
        }

        return -1;
    }
}
