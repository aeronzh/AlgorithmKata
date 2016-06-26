// Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
//
//  Notice
//
// You must do this in-place without making a copy of the array.
// Minimize the total number of operations.
// Have you met this question in a real interview? Yes
// Example
// Given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

public class Solution {
    /**
     * @param nums an integer array
     * @return nothing, do this in-place
     */
    public void moveZeroes(int[] nums) {
        // Write your code here
        if (nums == null || nums.length == 0) {
            return;
        }

        int zero = 0; // slow
        int nonZero = 0; // fast

        while (nonZero < nums.length) {
            while (zero < nums.length && nums[zero] != 0) {
                zero++;
            }

            nonZero = zero;
            while (nonZero < nums.length && nums[nonZero] == 0) {
                nonZero++;
            }

            if (nonZero < nums.length) {
                int temp = nums[zero];
                nums[zero] = nums[nonZero];
                nums[nonZero] = temp;
            }
        }
    }
}
