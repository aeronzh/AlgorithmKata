// Implement a basic calculator to evaluate a simple expression string.
//
// The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
//
// You may assume that the given expression is always valid.
//
// Some examples:
// "1 + 1" = 2
// " 2-1 + 2 " = 3
// "(1+(4+5+2)-3)+(6+8)" = 23
// Note: Do not use the eval built-in library function.
//
// Hide Company Tags Google
// Hide Tags Stack Math
// Hide Similar Problems (M) Evaluate Reverse Polish Notation (M) Basic Calculator II (M) Different Ways to Add Parentheses (H) Expression Add Operators

public class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        s = s.replace(" ", "");
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);

        int sign = 1;
        int result = 0;
        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '+') {
                sign = 1;
                i++;
            } else if (c == '-') {
                sign = -1;
                i++;
            } else if (c == '(') {
                stack.push(sign * stack.peek());
                sign = 1;
                i++;
            } else if (c == ')') {
                stack.pop();
                i++;
            } else {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                result += num * sign * stack.peek();
            }
        }

        return result;
    }
}
