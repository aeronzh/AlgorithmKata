// Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

// Example 1:
// Given nums = [1, -1, 5, -2, 3], k = 3,
// return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

// Example 2:
// Given nums = [-2, -1, 2, 1], k = 1,
// return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

// Follow Up:
// Can you do it in O(n) time?

// Hide Company Tags Palantir Facebook
// Hide Tags Hash Table
// Hide Similar Problems (M) Minimum Size Subarray Sum (E) Range Sum Query - Immutable

public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int max = Integer.MIN_VALUE;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int preSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if (map.containsKey(preSum - k)) {
                max = Math.max(max, i - map.get(preSum - k));
            }
            if (!map.containsKey(preSum)) {
                map.put(preSum, i);
            }
        }
        
        return max == Integer.MIN_VALUE ? 0 : max;
    }
}