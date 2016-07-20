// Follow up for "Remove Duplicates":
// What if duplicates are allowed at most twice?

// For example,
// Given sorted array nums = [1,1,1,2,2,3],

// Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.

// Hide Company Tags Facebook
// Hide Tags Array Two Pointers

public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int size = 0;
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            while (j < nums.length && nums[j] == nums[i]) {
                j++;
            }
            
            nums[size++] = nums[i];
            if (j - i > 1) {
                nums[size++] = nums[i];
            }
            i = j;
        }
        
        return size;
    }
}

// v2
public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int size = 0;
        int i = 0;
        int j = 0;
        for (i = 0; i < nums.length;) {
            for (j = i; j < nums.length; j++) {
                if (nums[j] != nums[i]) {
                    break;
                }
                if (j - i < 2) {
                    nums[size++] = nums[i];
                }
            }
            i = j;
        }
        
        return size;
    }
}