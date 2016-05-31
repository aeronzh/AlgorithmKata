// Numbers keep coming, return the median of numbers at every time a new number added.
// Example
// For numbers coming list: [1, 2, 3, 4, 5], return [1, 1, 2, 2, 3].
//
// For numbers coming list: [4, 5, 1, 3, 2, 6, 0], return [4, 4, 4, 3, 3, 3, 3].
//
// For numbers coming list: [2, 20, 100], return [2, 2, 20].
//
// Challenge
// Total run time in O(nlogn).
//
// Clarification
// What's the definition of Median? - Median is the number that in the middle of a sorted array. If there are n numbers in a sorted array A, the median is A[(n - 1) / 2]. For example, if A=[1,2,3], median is 2. If A=[1,19], median is 1.
// -----------------------------------------------------------------------------
// Keep a max heap for elements lower than current median
// Keep a min heap for elements greater than the current median
// Min heap is allowed to have one more elements than max heap since median = N / 2
// -----------------------------------------------------------------------------
public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) return nums;

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(1, Collections.reverseOrder());
        // PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
        //     @Override
        //     public int compare(Integer a, Integer b) {
        //         return b - a;
        //     }
        // });

        int[] result = new int[nums.length];
        result[0] = nums[0];
        int median = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < median) {
                maxHeap.add(nums[i]);
            } else {
                minHeap.add(nums[i]);
            }

            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(median);
                median = maxHeap.poll();
            } else if (maxHeap.size() + 1 < minHeap.size()) {
                maxHeap.add(median);
                median = minHeap.poll();
            }
            result[i] = median;
        }
        return result;
    }
}
