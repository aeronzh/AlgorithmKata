// Suppose a sorted array is rotated at some pivot unknown to you beforehand.
//
// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//
// Find the minimum element.
//
// Example
// Given [4, 5, 6, 7, 0, 1, 2] return 0
//
// Note
// You may assume no duplicate exists in the array.
// -----------------------------------------------------------------------------
// Binary search is used since no duplicate exists
// Method one: Find the first element that is smaller or equal to the last element
// -----------------------------------------------------------------------------
public class Solution {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        // write your code here
        if (num == null || num.length == 0) return -1;

        int start = 0;
        int end = num.length - 1;
        int target = num[end]; // for finding the first element that is smaller than the last element

         while (start + 1 < end) {
             int mid = start + (end - start) / 2;
             if (num[mid] <= target) {
                 end = mid;
             } else {
                 start = mid;
             }
         }
         if (num[start] <= target) {
             return num[start];
         } else {
             return num[end];
         }
    }
}
// -----------------------------------------------------------------------------
// Method two: find an element that is smaller then its previous and larger than its latter one
// -----------------------------------------------------------------------------
public class Solution {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        // write your code here
        if (num == null || num.length == 0) return -1;

        int start = 0;
        int mid = 0;
        int end = num.length - 1;

         while (start + 1 < end) {
             mid = start + (end - start) / 2;
             if (num[mid] < num[mid - 1] && num[mid] < num[mid + 1]) {
                 return num[mid];
             } else if (num[mid] > num[start] && num[mid] > num[end]) {
                 start = mid;
             } else {
                 end = mid;
             }
         }
         if (num[start] > num[end]) {
             return num[end];
         } else {
             return num[start];
         }
    }
}
