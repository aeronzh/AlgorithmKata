// Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

// Example:
// Given nums = [-2, 0, 3, -5, 2, -1]

// sumRange(0, 2) -> 1
// sumRange(2, 5) -> -1
// sumRange(0, 5) -> -3
// Note:
// You may assume that the array does not change.
// There are many calls to sumRange function.
// Hide Company Tags Palantir
// Hide Tags Dynamic Programming
// Hide Similar Problems (M) Range Sum Query 2D - Immutable (M) Range Sum Query - Mutable (E) Maximum Size Subarray Sum Equals k

public class NumArray {
    int[] prefix_sum;
    
    public NumArray(int[] nums) {
        prefix_sum = new int[nums.length + 1];
        
        for (int i = 1; i < prefix_sum.length; i++) {
            prefix_sum[i] = prefix_sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return prefix_sum[j + 1] - prefix_sum[i];
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);