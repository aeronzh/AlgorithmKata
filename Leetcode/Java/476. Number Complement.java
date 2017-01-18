// Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.
//
// Note:
// The given integer is guaranteed to fit within the range of a 32-bit signed integer.
// You could assume no leading zero bit in the integer’s binary representation.
// Example 1:
// Input: 5
// Output: 2
// Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
// Example 2:
// Input: 1
// Output: 0
// Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
// Hide Company Tags Cloudera
// Hide Tags Bit Manipulation

public class Solution {
    public int findComplement(int num) {
        int firstSet = 0;
        for (int i = 31; i>= 0; i--) {
            if (((num >> i) & 1) == 1) {
                firstSet = i;
                break;
            }
        }

        int result = 0;
        for (int i = 0; i <= firstSet; i++) {
            int n = (num >> i) & 1;

            result += n == 0 ? Math.pow(2, i) : 0;
        }

        return result;
    }
}
