// Implement pow(x, n).

//  Notice

// You don't need to care about the precision of your answer, it's acceptable if the expected answer and your answer 's difference is smaller than 1e-3.

// Example
// Pow(2.1, 3) = 9.261
// Pow(0, 1) = 0
// Pow(1, 0) = 1
// Challenge 
// O(logn) time

public class Solution {
    /**
     * @param x the base number
     * @param n the power number
     * @return the result
     */
    public double myPow(double x, int n) {
        // Write your code here
        if (n == 0) {
            return 1.0;
        }
        
        if (n > 0) {
            return helper(x, n);
        } else {
            return 1.0 / helper(x, -n);
        }
    }
    
    private double helper(double x, int n) {
        
        if (n == 1) {
            return x;
        }
        
        double result = helper(x, n / 2);
        
        if (n % 2 == 0) {
            return result * result;
        } else {
            return result * result * x;
        }
    }
}