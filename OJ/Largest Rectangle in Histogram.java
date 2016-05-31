// http://www.lintcode.com/en/problem/largest-rectangle-in-histogram/
// Note
public class Solution {
    /**
     * @param height: A list of integer
     * @return: The area of largest rectangle in the histogram
     */
    public int largestRectangleArea(int[] height) {
        // write your code here
        if (height == null || height.length == 0) return 0;

        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;

        for (int i = 0; i < height.length + 1; i++) {
            int cur = (i == height.length) ? -1 : height[i];
            while (!stack.isEmpty() && height[stack.peek()] >= cur) {
                int h = height[stack.pop()];
                int w = stack.isEmpty() ? i : i - 1 - stack.peek();
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }
        return max;
    }
}
