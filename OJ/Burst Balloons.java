// Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

// Find the maximum coins you can collect by bursting the balloons wisely.

// Note: 
// (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
// (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

// Example:

// Given [3, 1, 5, 8]

// Return 167

//     nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
//    coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
// Credits:
// Special thanks to @dietpepsi for adding this problem and creating all test cases.

// Hide Company Tags Google
// Hide Tags Divide and Conquer Dynamic Programming

public class Solution {
    public int maxCoins(int[] nums) {
        int[] coins = new int[nums.length + 2];
        for (int i = 1; i <= nums.length; i++) {
            coins[i] = nums[i - 1];
        }
        coins[0] = coins[nums.length + 1] = 1;
        return helper(coins, 1, nums.length, new HashMap<String, Integer>());
    }
    
    private int helper(int[] nums, int left, int right, HashMap<String, Integer> map) {
        String key = left + "," + right;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        
        int max = 0;
        for (int i = left; i <= right; i++) {
            int leftCoins = helper(nums, left, i - 1, map);
            int rightCoins = helper(nums, i + 1, right, map);
            int cur = nums[left - 1] * nums[i] * nums[right + 1];
            max = Math.max(max, leftCoins + cur + rightCoins);
        }
        
        map.put(key, max);
        return max;
    }
}