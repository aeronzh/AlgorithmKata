// Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.

// Hide Company Tags Palantir Airbnb
// Hide Tags Binary Search Tree
// Hide Similar Problems (E) Contains Duplicate (E) Contains Duplicate II

public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            Integer ceiling = set.ceiling(nums[i] - t);
            Integer floor = set.floor(nums[i] + t);
            if (ceiling != null && ceiling <= nums[i] || (floor != null && floor >= nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        
        return false;
    }
}