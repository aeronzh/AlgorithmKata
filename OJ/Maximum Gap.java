class Solution {
    /**
     * @param nums: an array of integers
     * @return: the maximum difference
     */
    public int maximumGap(int[] nums) {
        // write your code here
        if (nums == null || nums.length < 2) {
            return 0;
        }
        
        int max = Integer.MIN_VALUE;
        for (int n : nums) {
            max = Math.max(max, n);
        }
        
        int len = nums.length;
        int[] sorted = new int[len];
        int exp = 1;
        
        while (max / exp > 0) {
            int[] count = new int[10];
            
            for (int n : nums) {
                count[n / exp % 10]++;
            }
            
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1]; // get index for the partial sorted array
            }
            
            // traverse in reverse order because of duplicates may exists
            // -1 because count starts with 1
            for (int i = len - 1; i >= 0; i--) {
                int pos = count[nums[i] / exp % 10] - 1; // look up each num's new index for sorted array
                sorted[pos] = nums[i];
                count[nums[i] / exp % 10]--;
            }
            
            for (int i = 0; i < len; i++) {
                nums[i] = sorted[i];
            }
            
            exp *= 10;
        }
        
        int maxGap = 0;
        for (int i = 1; i < sorted.length; i++) {
            maxGap = Math.max(maxGap, sorted[i] - sorted[i - 1]);
        }
        
        return maxGap;
    }
}