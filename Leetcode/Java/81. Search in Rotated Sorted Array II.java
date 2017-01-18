// Follow up for "Search in Rotated Sorted Array":
// What if duplicates are allowed?
//
// Would this affect the run-time complexity? How and why?
// Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//
// Write a function to determine if a given target is in the array.
//
// The array may contain duplicates.
//
// Hide Tags Array Binary Search
// Hide Similar Problems (H) Search in Rotated Sorted Array

public class Solution {
    public boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1, mid = 0;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] > nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (nums[mid] < nums[start]) {
                if (target < nums[start] && target > nums[mid]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else {
                start++;
            }
        }

        if (target == nums[start] || target == nums[end]) {
            return true;
        }

        return false;
    }
}
