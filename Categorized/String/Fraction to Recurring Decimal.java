// Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

// If the fractional part is repeating, enclose the repeating part in parentheses.

// For example,

// Given numerator = 1, denominator = 2, return "0.5".
// Given numerator = 2, denominator = 1, return "2".
// Given numerator = 2, denominator = 3, return "0.(6)".
// Hint:

// No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
// Try a long division on 4/9, the repeating part is obvious. Now try 4/333. Do you see a pattern?
// Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.
// Credits:
// Special thanks to @Shangrila for adding this problem and creating all test cases.

// Hide Company Tags Google
// Hide Tags Hash Table Math

public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        if (numerator < 0 && denominator > 0 || (numerator > 0 && denominator < 0)) {
            sb.append("-");
        }
        
        long numer = Math.abs((long)numerator);
        long denom = Math.abs((long)denominator);
        
        sb.append(numer / denom);
        numer = numer % denom;
        
        if (numer == 0) {
            return sb.toString();
        }
        
        sb.append(".");
        int pos = sb.length();
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        
        while (numer != 0) {
            numer *= 10;
            
            if (map.containsKey(numer)) {
                sb.insert(map.get(numer), "(").append(")");
                break;
            }
            
            map.put(numer, pos);
            pos++;
            long quotient = numer / denom;
            sb.append(quotient);
            numer %= denom;
        }
        
        return sb.toString();
    }
}