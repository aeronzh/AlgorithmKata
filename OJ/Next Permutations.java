// Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

// If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

// The replacement must be in-place, do not allocate extra memory.

// Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
// 1,2,3 → 1,3,2
// 3,2,1 → 1,2,3
// 1,1,5 → 1,5,1
// Hide Company Tags Google
// Hide Tags Array
// Hide Similar Problems (M) Permutations (M) Permutations II (M) Permutation Sequence (M) Palindrome Permutation II

public class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int index = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                index = i;
                break;
            }
        }
        
        if (index == -1) {
            reverse(0, nums.length - 1, nums);
            return;
        }
        
        int firstLarger = index + 1;
        for (int i = nums.length - 1; i > index; i--) {
            if (nums[i] > nums[index]) {
                firstLarger = i;
                break;
            }
        }
        
        swap(index, firstLarger, nums);
        
        reverse(index + 1, nums.length - 1, nums);
    }
    
    private void swap(int a, int b, int[] nums) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    
    private void reverse(int left, int right, int[] nums) {
        while (left < right) {
            swap(left, right, nums);
            left++;
            right--;
        }
    }
}