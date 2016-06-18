// Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.

// Example 1:
// nums1 = [3, 4, 6, 5]
// nums2 = [9, 1, 2, 5, 8, 3]
// k = 5
// return [9, 8, 6, 5, 3]

// Example 2:
// nums1 = [6, 7]
// nums2 = [6, 0, 4]
// k = 5
// return [6, 7, 6, 0, 4]

// Example 3:
// nums1 = [3, 9]
// nums2 = [8, 9]
// k = 3
// return [9, 8, 9]

// Credits:
// Special thanks to @dietpepsi for adding this problem and creating all test cases.

// Hide Company Tags Google
// Hide Tags Dynamic Programming Greedy

// Choose i digits from nums1 and choose k - i digits from nums2
public class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] result = new int[k];
        
        for (int i = Math.max(0, k - len2); i <= k && i <= len1; i++) {
            int[] res1 = maxArray(nums1, i); // choose i digits from nums1
            int[] res2 = maxArray(nums2, k - i); // choose k - i digits from nums2
            int[] temp = merge(res1, res2, k);
            
            if (isGreater(temp, 0, result, 0)) {
                result = temp;
            }
        }
        
        return result;
    }
    
    private int[] maxArray(int[] nums, int k) {
        int[] result = new int[k];
        int j = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // j + nums.length - i > k guarantees there are enough digits left to choose from
            while (j > 0 && j + nums.length - i > k && result[j - 1] < nums[i]) {
                j--;
            }
            
            if (j < k) {
                result[j] = nums[i];
                j++;
            }
        }
        
        return result;
    }
    
    private int[] merge(int[] a, int[] b, int k) {
        int[] result = new int[k];
        
        int i = 0;
        int j = 0;
        int pos = 0;
        while (i < a.length && j < b.length) {
            if (isGreater(a, i, b, j)) {
                result[pos++] = a[i++];
            } else {
                result[pos++] = b[j++];
            }
        }
        
        while (i < a.length) {
            result[pos++] = a[i++];
        }
        
        while (j < b.length) {
            result[pos++] = b[j++];
        }
        
        return result;
    }
    
    private boolean isGreater(int[] a, int i, int[] b, int j) {
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                return false;
            } else if (a[i] > b[j]) {
                return true;
            }
            i++;
            j++;
        }
        
        if (i < a.length) {
            return true;
        }
        
        return false;
    }
}

// DFS TLE
public class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        helper(nums1, 0, nums2, 0, k, "");
        
        int[] result = new int[max.length()];
        char[] s = max.toCharArray();
        for (int i = 0; i < s.length; i++) {
            result[i] = s[i] - '0';
        }
        
        return result;
    }
    
    String max = "";
    private void helper(int[] a, int a_pos, int[] b, int b_pos, int k, String num) {
        if (k == 0) {
            update(num);
            return;
        }
        
        if (a_pos < a.length) {
            helper(a, a_pos + 1, b, b_pos, k - 1, num + a[a_pos]);
            helper(a, a_pos + 1, b, b_pos, k, num);
        }
        
        if (b_pos < b.length) {
            helper(a, a_pos, b, b_pos + 1, k - 1, num + b[b_pos]);
            helper(a, a_pos, b, b_pos + 1, k, num);
        }
        
        if (a_pos < a.length && b_pos < b.length) {
            helper(a, a_pos + 1, b, b_pos + 1, k, num);
        }
    }
    
    private void update(String num) {
        int zero = 0;
        while (zero < num.length() && num.charAt(zero) == '0') {
            zero++;
        }
        
        num = num.substring(zero, num.length());
        
        if (num.length() < max.length()) {
            return;
        }
        
        if (num.length() > max.length()) {
            max = num;
            return;
        }
        
        for (int i = 0; i < max.length(); i++) {
            if (num.charAt(i) > max.charAt(i)) {
                max = num;
                break;
            } else if (num.charAt(i) < max.charAt(i)) {
                break;
            }
        }
    }
}