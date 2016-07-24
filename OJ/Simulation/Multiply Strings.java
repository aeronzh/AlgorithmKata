// Given two numbers represented as strings, return multiplication of the numbers as a string.

// Note:
// The numbers can be arbitrarily large and are non-negative.
// Converting the input string to integer is NOT allowed.
// You should NOT use internal library such as BigInteger.
// Hide Company Tags Facebook Twitter
// Hide Tags Math String
// Hide Similar Problems (M) Add Two Numbers (E) Plus One (E) Add Binary

public class Solution {
    public String multiply(String num1, String num2) {
        boolean positive = true;
        if (num1.charAt(0) == '-' && num2.charAt(0) != '-' || (num1.charAt(0) != '-' && num2.charAt(0) == '-')) {
            positive = false;
        }
        
        num1 = num1.charAt(0) == '-' ? num1.substring(1) : num1;
        num2 = num2.charAt(0) == '-' ? num2.substring(1) : num2;
        
        String result = "0";
        int offset = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            int carry = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int multi = n1 * n2 + carry;
                sb.append(multi % 10);
                carry = multi / 10;
            }
            if (carry != 0) {
                sb.append(carry);
            }
            result = result.substring(0, offset) + add(result.substring(offset), sb.toString());
            offset++;
        }
        
        result = new StringBuilder(result).reverse().toString();
        
        if (result.charAt(0) - '0' == 0) {
            return "0";
        }
        
        if (!positive) {
            result = "-" + result;
        }
        
        return result;
    }
    
    private String add(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ai = 0;
        int bi = 0;
        int carry = 0;
        while (ai < a.length() || bi < b.length() || carry != 0) {
            int aVal = ai < a.length() ? a.charAt(ai++) - '0' : 0;
            int bVal = bi < b.length() ? b.charAt(bi++) - '0' : 0;
            int sum = aVal + bVal + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        
        return sb.toString();
    }
}


// https://discuss.leetcode.com/topic/30508/easiest-java-solution-with-graph-explanation

public class Solution {
    public String multiply(String num1, String num2) {
        int[] result = new int[num1.length() + num2.length()];
        
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int multi = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                result[i + j] += (result[i + j + 1] + multi) / 10; // calculate carry
                result[i + j + 1] = (result[i + j + 1] + multi) % 10;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int n : result) {
            if (n == 0 && sb.length() == 0) {
                continue;
            }
            sb.append(n);
        }
        
        return sb.length() == 0 ? "0" : sb.toString();
    }
}