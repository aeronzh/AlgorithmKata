// Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
//
// Example
// Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
//
// Challenge
// O(n) time and O(1) memory
//
// O(n) time and O(n) memory is also acceptable.
// -----------------------------------------------------------------------------
// A simple way to solve this problem is to use DP. Pre-processing reduces the time complexity significantly.
// 1. For each bar, find the tallest bar to its left.
// 2. For each bar, find the tallest bar to its right.
// 3. For each bar, find the lower bar on both of its left and right sides. If the lower side is higher than the current bar, subtract the current one from the lower side. Sum up the result.
// Note the initialization of the leftmost and rightmost bars.
// -----------------------------------------------------------------------------
public class Solution {
    /**
     * @param heights: an array of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        // write your code here
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int[] leftMax = new int[heights.length];
        leftMax[0] = heights[0];
        for (int i = 1; i < heights.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], heights[i]);
        }

        int[] rightMax = new int[heights.length];
        rightMax[heights.length - 1] = heights[heights.length - 1];
        for (int i = heights.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], heights[i]);
        }

        int sum = 0;
        for (int i = 1; i < heights.length - 1; i++) {
            sum += Math.max(0, Math.min(leftMax[i - 1], rightMax[i + 1]) - heights[i]);
        }

        return sum;
    }
}
// -----------------------------------------------------------------------------
// O(n) space
// 1. Find the tallest bar
// 2. Progress from both left and right hand side. Stop at the tallest bar.
// -----------------------------------------------------------------------------
public class Solution {
    /**
     * @param heights: an array of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        // write your code here
        if (heights == null || heights.length < 3) return 0;

        int tallest = 0;
        int sum = 0;

        for (int i = 0; i < heights.length; i++) {
            tallest = heights[tallest] < heights[i] ? i : tallest; // index of the tallest bar
        }

        int prev = 0;
        for (int i = 0; i < tallest; i++) {
            if (heights[prev] > heights[i]) {
                sum += heights[prev] - heights[i];
            } else {
                prev = i;
            }
        }

        prev = heights.length - 1;
        for (int i = heights.length - 1; i > tallest; i--) {
            if (heights[prev] > heights[i]) {
                sum += heights[prev] - heights[i];
            } else {
                prev = i;
            }
        }

        return sum;
    }
}
// -----------------------------------------------------------------------------
// A two-pointer solution. With even more prunings!
// -----------------------------------------------------------------------------
public class Solution {
    /**
     * @param heights: an array of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        // write your code here
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int left = 0;
        int right = heights.length - 1;
        int leftMax = heights[0];
        int rightMax = heights[heights.length - 1];
        int sum = 0;

        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                leftMax = Math.max(leftMax, heights[left]);
                sum += leftMax - heights[left];
            } else {
                right--;
                rightMax = Math.max(rightMax, heights[right]);
                sum += rightMax - heights[right];
            }
        }

        return sum;
    }
}
