// Implement pow(x, n).

// Hide Company Tags LinkedIn Google Bloomberg Facebook
// Hide Tags Binary Search Math
// Hide Similar Problems (M) Sqrt(x)

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