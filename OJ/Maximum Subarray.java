// Given an array of integers, find a contiguous subarray which has the largest sum.
//
//  Notice
//
// The subarray should contain at least one number.
//
// Have you met this question in a real interview? Yes
// Example
// Given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1] has the largest sum = 6.
//
// Challenge
// Can you do it in time complexity O(n)?

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        // write your code
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int preSum = 0;
        int max = nums[0];
        int min = 0;

        for (int n : nums) {
            preSum += n;
            max = Math.max(max, preSum - min);
            min = Math.min(min, preSum);
        }

        return max;
    }
}

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        // write your code
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]); // local
            max = Math.max(max, dp[i]); // global
        }

        return max;
    }
}
