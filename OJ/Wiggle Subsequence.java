// A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.

// For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

// Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.

// Examples:
// Input: [1,7,4,9,2,5]
// Output: 6
// The entire sequence is a wiggle sequence.

// Input: [1,17,5,10,13,15,10,5,16,8]
// Output: 7
// There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

// Input: [1,2,3,4,5,6,7,8,9]
// Output: 2
// Follow up:
// Can you do it in O(n) time?

// Credits:
// Special thanks to @agave and @StefanPochmann for adding this problem and creating all test cases.

// Hide Tags Dynamic Programming Greedy

// Similar to LIS
public class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] increase = new int[nums.length];
        int[] decrease = new int[nums.length];
        
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    increase[i] = Math.max(increase[i], decrease[j] + 1);
                } else if (nums[j] > nums[i]) {
                    decrease[i] = Math.max(decrease[i], increase[j] + 1);
                }
            }
        }
        
        return Math.max(increase[nums.length - 1], decrease[nums.length - 1]) + 1; // count the first element
    }
}

// Greedy: compress the continuous increasing or decrease arrays
public class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int increase = 1;
        int decrease = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                increase = decrease + 1;
            } else if (nums[i - 1] > nums[i]) {
                decrease = increase + 1;
            }
        }
        
        return Math.max(increase, decrease);
    }
}

public class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int count = 1;
        int i = 0;
        while (i + 1 < nums.length) {
            if (nums[i + 1] < nums[i]) {
                count++;
                while (i + 1 < nums.length && nums[i + 1] <= nums[i]) {
                    i++;
                }
            } else if (nums[i + 1] > nums[i]) {
                count++;
                while (i + 1 < nums.length && nums[i + 1] >= nums[i]) {
                    i++;
                }
            } else {
                while (i + 1 < nums.length && nums[i + 1] == nums[i]) {
                    i++;
                }
            }
        }
        
        return count;
    }
}