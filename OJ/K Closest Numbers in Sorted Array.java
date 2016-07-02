// Given a target number, a non-negative integer k and an integer array A sorted in ascending order, find the k closest numbers to target in A, sorted in ascending order by the difference between the number and target. Otherwise, sorted in ascending order by number if the difference is same.
//
// Have you met this question in a real interview? Yes
// Example
// Given A = [1, 2, 3], target = 2 and k = 3, return [2, 1, 3].
//
// Given A = [1, 4, 6, 8], target = 3 and k = 3, return [4, 1, 6].
//
// Challenge
// O(logn + k) time complexity.

public class Solution {
    /**
     * @param A an integer array
     * @param target an integer
     * @param k a non-negative integer
     * @return an integer array
     */
    public int[] kClosestNumbers(int[] A, int target, int k) {
        // Write your code here
        int[] result = new int[k];

        if (A == null || A.length < k) {
            return result;
        }

        // find the first element that is larger than target
        int firstLarger = findFirstLarger(A, target);

        int left = firstLarger - 1;
        int right = firstLarger;
        int i = 0;

        while (i < result.length) {
            if (right >= A.length) {
                result[i] = A[left--];
            } else if (left < 0) {
                result[i] = A[right++];
            } else {
                // if diff if the same, result array still need to be in ascending order
                if (Math.abs(A[left] - target) <= Math.abs(A[right] - target)) {
                    result[i] = A[left--];
                } else {
                    result[i] = A[right++];
                }
            }
            i++;
        }

        return result;
    }

    private int findFirstLarger(int[] A, int target) {
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                start = mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        int index = 0;
        if (A[start] >= target) {
            index = start;
        } else {
            index = end;
        }

        return index;
    }
}
