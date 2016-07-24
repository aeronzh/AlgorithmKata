public class Solution {
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        }

        if (b == null || b.length() == 0) {
            return a;
        }

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();

        while (i >= 0 || j >= 0 || carry > 0) {
            int aBit = i >= 0 ? a.charAt(i) - '0' : 0;
            int bBit = j >= 0 ? b.charAt(j) - '0' : 0;

            int curBit = aBit ^ bBit ^ carry;
            carry = (aBit + bBit + carry) > 1 ? 1 : 0;

            sb.append(curBit);

            i--;
            j--;
        }

        return sb.reverse().toString();
    }
}

// v2
public class Solution {
    public String addBinary(String a, String b) {
        int ai = a.length() - 1;
        int bi = b.length() - 1;
        int carry = 0;
        StringBuilder result = new StringBuilder();
        
        while (ai >= 0 || bi >= 0 || carry != 0) {
            int aBit = ai >= 0 ? a.charAt(ai--) - '0' : 0;
            int bBit = bi >= 0 ? b.charAt(bi--) - '0' : 0;
            int sum = aBit ^ bBit ^ carry;
            carry = aBit + bBit + carry > 1 ? 1 : 0;
            result.append(sum);
        }
        
        return result.reverse().toString();
    }
}