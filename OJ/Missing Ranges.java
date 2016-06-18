// Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

// For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].

// Hide Company Tags Google
// Hide Tags Array
// Hide Similar Problems (M) Summary Ranges

public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<String>();
        
        int prev = lower - 1;
        for (int i = 0; i <= nums.length; i++) {
            int next = i == nums.length ? upper + 1 : nums[i];
            
            // add range
            if (prev + 2 == next) {
                result.add("" + (prev + 1));
            } else if (prev + 2 < next) {
                result.add("" + (prev + 1) + "->" + (next - 1));
            }
            
            prev = next;
        }
        
        return result;
    }
}