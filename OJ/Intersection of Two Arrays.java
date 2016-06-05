// Given two arrays, write a function to compute their intersection.

//  Notice

// Each element in the result must be unique.
// The result can be in any order.
// Have you met this question in a real interview? Yes
// Example
// Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

// Challenge 
// Can you implement it in three different algorithms?

// Tags 
// Binary Search Two Pointers Sort Hash Table

// HashSet
public class Solution {
    /**
     * @param nums1 an integer array
     * @param nums2 an integer array
     * @return an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // Write your code here
        
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        
        HashSet<Integer> set = new HashSet<Integer>();
        for (int n : nums1) {
            set.add(n);
        }
        
        HashSet<Integer> intersection = new HashSet<Integer>();
        for (int n : nums2) {
            if (set.contains(n)) {
                intersection.add(n);
            }
        }
        
        int[] result = new int[intersection.size()];
        int i = 0;
        for (int n : intersection) {
            result[i] = n;
            i++;
        }
        
        return result;
    }
}

// Two Pointers
public class Solution {
    /**
     * @param nums1 an integer array
     * @param nums2 an integer array
     * @return an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // Write your code here
        
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        
        HashSet<Integer> intersection = new HashSet<Integer>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                intersection.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }
        
        int[] result = new int[intersection.size()];
        int index = 0;
        for (int n : intersection) {
            result[index] = n;
            index++;
        }
        
        return result;
    }
}

// binary search
// efficient when the size of two arrays are very different
public class Solution {
    /**
     * @param nums1 an integer array
     * @param nums2 an integer array
     * @return an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // Write your code here
        
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        
        HashSet<Integer> intersection = new HashSet<Integer>();
        int[] longer = nums1.length > nums2.length ? nums1 : nums2;
        int[] shorter = nums1.length > nums2.length ? nums2 : nums1;
        Arrays.sort(longer);
        
        for (int i = 0; i < shorter.length; i++) {
            int start = 0;
            int end = longer.length - 1;
            int target = shorter[i];
            
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                
                if (longer[mid] == target) {
                    intersection.add(target);
                    break;
                } else if (longer[mid] < target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
            
            if (longer[start] == target) {
                intersection.add(longer[start]);
            } else if (longer[end] == target) {
                intersection.add(longer[end]);
            }
        }
        
        int[] result = new int[intersection.size()];
        int i = 0;
        for (int n : intersection) {
            result[i] = n;
            i++;
        }
        
        return result;
    }
}