// Divide two integers without using multiplication, division and mod operator.

// If it is overflow, return MAX_INT.

// Hide Tags Math Binary Search

// Integer.MIN_VALUE / -1 == Integer.MIN_VALUE
public class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
            return Integer.MAX_VALUE;
        }
        
        int sign = 1;
        if (dividend < 0 && divisor > 0 || (dividend > 0 && divisor < 0)) {
            sign = -1;
        }
        
        long dvd = Math.abs((long)dividend);
        long dvs = Math.abs((long)divisor);
        
        // num = a_0*2^0 + a_1*2^1 + a_2*2^2 +...+ a_n*2^n
        int result = 0;
        while (dvd >= dvs) {
            long sum = dvs;
            int multiple = 1;
            while ((sum << 1) < dvd) {
                sum <<= 1;
                multiple <<= 1;
            }
            dvd -= sum;
            result += multiple;
        }
        
        return result * sign;
    }
}

// Divide two floats. Cannot use bit shifting
public class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
            return Integer.MAX_VALUE;
        }
        
        int sign = 1;
        if (dividend < 0 && divisor > 0 || (dividend > 0 && divisor < 0)) {
            sign = -1;
        }
        
        long dvd = Math.abs((long)dividend);
        long dvs = Math.abs((long)divisor);
        
        // num = a_0*2^0 + a_1*2^1 + a_2*2^2 +...+ a_n*2^n
        int result = 0;
        while (dvd >= dvs) {
            long sum = dvs;
            int multiple = 1;
            while ((sum + sum) < dvd) {
                sum += sum;
                multiple += multiple;
            }
            dvd -= sum;
            result += multiple;
        }
        
        return result * sign;
    }
}