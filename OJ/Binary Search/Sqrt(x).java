// Implement int sqrt(int x).

// Compute and return the square root of x.

// Have you met this question in a real interview? Yes
// Example
// sqrt(3) = 1

// sqrt(4) = 2

// sqrt(5) = 2

// sqrt(10) = 3

class Solution {
    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // write your code here
        long start = 0;
        long end = x;
        
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (mid * mid == x) {
                start = mid;
            } else if (mid * mid < x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (end * end <= x) {
            return (int)end;
        }
        return (int)start;
    }
}