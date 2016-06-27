// Given an integer, write a function to determine if it is a power of three.
//
// Follow up:
// Could you do it without using any loop / recursion?
//
// Credits:
// Special thanks to @dietpepsi for adding this problem and creating all test cases.
//
// Hide Company Tags Google
// Hide Tags Math
// Hide Similar Problems (E) Power of Two (E) Power of Four

public class Solution {
    public boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }

        return Math.log10(n) / Math.log10(3) % 1 == 0;
    }
}

public class Solution {
    public boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }

        while (n != 1) {
            if (n % 3 == 0) {
                n /= 3;
            } else {
                return false;
            }
        }

        return true;
    }
}
