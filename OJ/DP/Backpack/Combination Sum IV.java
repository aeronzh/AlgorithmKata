// Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

// Example:

// nums = [1, 2, 3]
// target = 4

// The possible combination ways are:
// (1, 1, 1, 1)
// (1, 1, 2)
// (1, 2, 1)
// (1, 3)
// (2, 1, 1)
// (2, 2)
// (3, 1)

// Note that different sequences are counted as different combinations.

// Therefore the output is 7.
// Follow up:
// What if negative numbers are allowed in the given array?
// How does it change the problem?
// What limitation we need to add to the question to allow negative numbers?

// Credits:
// Special thanks to @pbrother for adding this problem and creating all test cases.

// Hide Company Tags Google Snapchat
// Hide Tags Dynamic Programming
// Hide Similar Problems (M) Combination Sum

// brute force
public class Solution {
    public int combinationSum4(int[] nums, int target) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target - nums[i] < 0) {
                continue;
            }
            if (target - nums[i] == 0) {
                count++;
                continue;
            }
            count += combinationSum4(nums, target - nums[i]);
        }
        
        return count;
    }
}

// memoization
public class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();
    
    public int combinationSum4(int[] nums, int target) {
        if (map.containsKey(target)) {
            return map.get(target);
        }
        
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target - nums[i] < 0) {
                continue;
            }
            if (target - nums[i] == 0) {
                count++;
                continue;
            }
            count += combinationSum4(nums, target - nums[i]);
        }
        
        map.put(target, count);
        return count;
    }
}

// bottom up DP
public class Solution {
    public int combinationSum4(int[] nums, int target) {
        // state: dp[i] how many ways can some elements in nums sum up to i
        // function: dp[i] += dp[i - nums[j]] if i >= nums[j]
        // init: dp[0] = 1
        // result: dp[target];
        
        int[] dp = new int[target + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= target; i++) {
            for (int n : nums) {
                if (i >= n) {
                    dp[i] += dp[i - n];
                }
            }
        }
        
        return dp[target];
    }
}

// if order doesn't matter: [1, 2] and [2, 1] counts as the same combination
public class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        
        for (int n : nums) {
            for (int i = 1; i <= target; i++) {
                if (i >= n) {
                    dp[i] += dp[i - n];
                }
            }
        }
        
        return dp[target];
    }
}