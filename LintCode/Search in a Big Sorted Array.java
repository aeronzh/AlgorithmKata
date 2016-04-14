// Given a big sorted array, find the first index of a target number. Your algorithm should be in O(log k), where k is the first index of the target number.
//
// Return -1, if the number doesn't exist in the array.
//
// Example
// Given [1, 3, 6, 9, 21], and target = 3, return 1.
//
// Given [1, 3, 6, 9, 21], and target = 4, return -1.
//
// Challenge
// O(log k), k is the first index of the given target number.
// -----------------------------------------------------------------------------
// Note the pre-processing of end pointer
// A similar idea taken from ArrayList
// -----------------------------------------------------------------------------
public class Solution {
    /**
     * @param A: An integer array
     * @param target: An integer
     * @return : An integer which is the index of the target number
     */
    public int searchBigSortedArray(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) return -1;

        // end is Initialised to 0 in case of there is only a single element in A[]
        int start = 0, mid = 0, end = 0;
        while (end < A.length && A[end] < target) {
            end = end * 2 + 1; // plus 1 since end is initialised to be 0
            if (end >= A.length - 1) {
                end = A.length - 1;
                break;
            }
        }
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (A[start] == target) {
            return start;
        } else if (A[end] == target) {
            return end;
        }
        return -1;
    }
}
