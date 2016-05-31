// Given a sorted integer array without duplicates, return the summary of its ranges.

// For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

// Credits:
// Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

// Hide Company Tags Google
// Hide Tags Array
// Hide Similar Problems (M) Missing Ranges


public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<String>();
        
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int i = 0;
        while (i < nums.length) {
            int j = i + 1;
            
            while (j < nums.length && nums[j - 1] + 1 == nums[j]) {
                j++;
            }
            
            if (i + 1 == j) {
                result.add("" + nums[i]);
            } else {
                result.add("" + nums[i] + "->" + nums[j - 1]);
            }
            
            i = j;
        }
        
        return result;
    }
}