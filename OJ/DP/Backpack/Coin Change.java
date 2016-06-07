// You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

// Example 1:
// coins = [1, 2, 5], amount = 11
// return 3 (11 = 5 + 5 + 1)

// Example 2:
// coins = [2], amount = 3
// return -1.

// Note:
// You may assume that you have an infinite number of each kind of coin.

// Credits:
// Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

// Hide Tags Dynamic Programming
// -----------------------------------------
// Difference between coin change problem and backpack:
// Coin change problem is to find the fewest number of coins that make up a given amount.
// Each coin given can be used multiple times, unlike in Backpack, each item can only be used once

public class Solution {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount == 0) {
            return 0;
        }
        
        // dp[i]: fewest coins needed to make up amount j
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 0; i < amount + 1; i++) {
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}

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