// Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.

// The returned array must be in sorted order.

// Expected time complexity: O(n)

// Example:
// nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

// Result: [3, 9, 15, 33]

// nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

// Result: [-23, -5, 1, 7]
// Credits:
// Special thanks to @elmirap for adding this problem and creating all test cases.

// Hide Company Tags Google
// Hide Tags Math Two Pointers

// mergesort
// if a < 0 : two ends are smaller than center
// if a > 0 : two ends are greater than center
public class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] result = new int[nums.length];
        
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int i = 0;
        int j = nums.length - 1;
        int pos = a < 0 ? 0 : nums.length - 1;
        
        while (i <= j) {
            int left = cal(nums[i], a, b, c);
            int right = cal(nums[j], a, b, c);
            if (a < 0) {
                if (left < right) {
                    result[pos++] = left;
                    i++;
                } else {
                    result[pos++] = right;
                    j--;
                }
            } else {
                if (left < right) {
                    result[pos--] = right;
                    j--;
                } else {
                    result[pos--] = left;
                    i++;
                }
            }
        }
        
        return result;
    }
    
    private int cal(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}