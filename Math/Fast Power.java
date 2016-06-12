// Calculate the a^n % b where a, b and n are all 32bit integers.

// Example
// For 231 % 3 = 2

// For 1001000 % 1000 = 0

// Challenge 
// O(logn)

class Solution {
    /*
     * @param a, b, n: 32bit integers
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        // write your code here
        if (n == 0) {
            return 1 % b;
        }
        
        if (n == 1) {
            return a % b;
        }
        
        long result = fastPower(a, b, n / 2);
        
        // (a * b) % p = (a % p * b % p) % p
        if (n % 2 == 0) {
            return (int)(result * result % b);
        } else {
            return (int)(result * result % b * (a % b) % b); // first mod is to avoid overflow
        }
    }
};