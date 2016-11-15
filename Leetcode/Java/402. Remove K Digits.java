// Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
//
// Note:
// The length of num is less than 10002 and will be ≥ k.
// The given num does not contain any leading zero.
// Example 1:
//
// Input: num = "1432219", k = 3
// Output: "1219"
// Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
// Example 2:
//
// Input: num = "10200", k = 1
// Output: "200"
// Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
// Example 3:
//
// Input: num = "10", k = 2
// Output: "0"
// Explanation: Remove all the digits from the number and it is left with nothing which is 0.

public class Solution {
    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0 || k >= num.length()) {
            return "0";
        }

        int len = num.length();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            int n = num.charAt(i) - '0';
            while (!stack.isEmpty()) {
                if (stack.peek() > n && len - i + stack.size() > len - k) {
                    stack.pop();
                } else {
                    break;
                }
            }

            stack.push(n);
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            int n = stack.pop();
            sb.append(n);
        }

        sb = sb.reverse();
        int idx = 0;
        while(idx < sb.length() && sb.charAt(idx) == '0') {
            idx++;
        }

        return idx == sb.length() ? "0" : sb.substring(idx, Math.min(len, len - k));
    }
}
