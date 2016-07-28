// Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return -1 instead.

// Have you met this question in a real interview? Yes
// Example
// Given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length under the problem constraint.

// Challenge 
// If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

public class Solution {
    /**
     * @param nums: an array of integers
     * @param s: an integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int j = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            while (j < nums.length) {
                if (sum < s) {
                    sum += nums[j];
                } else {
                    break;
                }
                j++;
            }
            
            if (sum >= s) {
                min = Math.min(min, j - i);
            }
            
            sum -= nums[i];
        }
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}

public class Solution {
    /**
     * @param nums: an array of integers
     * @param s: an integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int i = 0;
        int j = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        
        while (i < nums.length) {
            while (j < nums.length && sum < s) {
                sum += nums[j++];
            }
            
            if (sum >= s) {
                min = Math.min(min, j - i);
            }
            
            sum -= nums[i++];
        }
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}