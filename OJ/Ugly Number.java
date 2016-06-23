// Write a program to check whether a given number is an ugly number`.

// Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

//  Notice

// Note that 1 is typically treated as an ugly number.

// Have you met this question in a real interview? Yes
// Example
// Given num = 8 return true
// Given num = 14 return false

public class Solution {
    /**
     * @param num an integer
     * @return true if num is an ugly number or false
     */
    public boolean isUgly(int num) {
        // Write your code here
        if (num != 1 && num < 2) {
            return false;
        }

        while (num != 1) {
            if (num % 2 == 0) {
                num /= 2;
            } else if (num % 3 == 0) {
                num /= 3;
            } else if (num % 5 == 0) {
                num /= 5;
            } else {
                return false;
            }
        }

        return true;
    }
}
