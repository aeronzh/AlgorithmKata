// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
// Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
//
// Credits:
// Special thanks to @ifanchu for adding this problem and creating all test cases. Also thanks to @ts for adding additional test cases.
//
// Subscribe to see which companies asked this question
//
// Hide Tags Dynamic Programming
// Hide Similar Problems (M) Maximum Product Subarray (M) House Robber II (M) Paint House (E) Paint Fence (M) House Robber III



// dfs + memoization
public class Solution {
    public int rob(int[] nums) {
        return helper(nums, 0, new HashMap<Integer, Integer>());
    }

    private int helper(int[] nums, int i, Map<Integer, Integer> dp) {
        if (dp.containsKey(i)) {
            return dp.get(i);
        }

        if (i >= nums.length) {
            return 0;
        }

        int sum = Math.max(nums[i] + helper(nums, i + 2, dp), helper(nums, i + 1, dp));
        dp.put(i, sum);

        return sum;
    }
}
