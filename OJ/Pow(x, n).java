// Implement pow(x, n).

// Hide Company Tags LinkedIn Google Bloomberg Facebook
// Hide Tags Binary Search Math
// Hide Similar Problems (M) Sqrt(x)

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

public class Solution {
    public double myPow(double x, int n) {
        if (n >= 0) {
            return pow(x, n);
        } else {
            return 1 / pow(x, -n);
        }
    }
    
    private double pow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        
        double r = pow(x, n / 2);
        
        if (n % 2 == 0) {
            return r * r;
        } else {
            return r * r * x;
        }
    }
}