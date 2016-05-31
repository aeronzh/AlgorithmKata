// Given an array of n integer with duplicate number, and a moving window(size k), move the window at each iteration from the start of the array, find the maximum number inside the window at each moving.
//
// Example
// For array [1, 2, 7, 7, 8], moving window size k = 3. return [7, 7, 8]
//
// At first the window is at the start of the array like this
//
// [|1, 2, 7| ,7, 8] , return the maximum 7;
//
// then the window move one step forward.
//
// [1, |2, 7 ,7|, 8], return the maximum 7;
//
// then the window move one step forward again.
//
// [1, 2, |7, 7, 8|], return the maximum 8;
//
// Challenge
// o(n) time and O(k) memory
// -----------------------------------------------------------------------------
// O(n) = O(2n) = addinng to deque + polling from deque
// 1. If input is larger then the last element in the deque, keep polling until the last element in deque is larger than the input
// 2. Add input into the deque
// 3. If the first element to be deleted from the window is the same as the first element in the deque, delete the first element in the deque
// Note: deque is always in descending order. Because the smaller input might later become the maximum
// Sliding window problem: add and delete. Order doesn't matter.
// In case of duplicates: transform input from [1,3,3,7,9] to [(0,1), (2,3), (3,3), (4,7), (5,9)]
// -----------------------------------------------------------------------------
public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: The maximum number inside the window at each moving.
     */
    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();
        Deque<Integer> deque = new ArrayDeque<Integer>();

        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] > deque.peekLast()) {
                deque.pollLast();
            }
            deque.offer(nums[i]);

            if (i >= k && nums[i - k] == deque.peekFirst()) {
                deque.pollFirst();
            }

            if (i + 1 >= k) {
                result.add(deque.peekFirst());
            }
        }

        return result;
    }
}
