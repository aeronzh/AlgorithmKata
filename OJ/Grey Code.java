// The gray code is a binary numeral system where two successive values differ in only one bit.

// Given a non-negative integer n representing the total number of bits in the code, find the sequence of gray code. A gray code sequence must begin with 0 and with cover all 2n integers.

//  Notice

// For a given n, a gray code sequence is not uniquely defined.

// [0,2,3,1] is also a valid gray code sequence according to the above definition.

// Example
// Given n = 2, return [0,1,3,2]. Its gray code sequence is:

// 00 - 0
// 01 - 1
// 11 - 3
// 10 - 2
// Challenge 
// O(2n) time.

public class Solution {
    /**
     * @param n a number
     * @return Gray code
     */
    public ArrayList<Integer> grayCode(int n) {
        // Write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        if (n == 0) {
            result.add(0);
            return result;
        }
        
        result = grayCode(n - 1);
        
        for (int i = result.size() - 1; i >= 0; i--) {
            int num = result.get(i);
            num |= (1 << (n - 1));
            result.add(num);
        }
        
        return result;
    }
}