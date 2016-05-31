// Evaluate the value of an arithmetic expression in Reverse Polish Notation.
//
// Valid operators are +, -, *, /. Each operand may be an integer or another expression.
//
// Example
// ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
// ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
// -----------------------------------------------------------------------------
// Push when number is scanned
// Pop two numbers when operator is scanned
// -----------------------------------------------------------------------------
public class Solution {
    /**
     * @param tokens The Reverse Polish Notation
     * @return the value
     */
    public int evalRPN(String[] tokens) {
        // Write your code here
        if (tokens == null || tokens.length == 0) return 0;

        Stack<Integer> stack = new Stack<Integer>();
        String operators = "+-*/";

        for (String s : tokens) {
            if (!operators.contains(s)) {
                stack.push(Integer.valueOf(s));
            } else {
                int a = stack.pop();
                int b = stack.pop();
                if (s.equals("+")) {
                    stack.push(a + b);
                } else if (s.equals("-")) {
                    stack.push(b - a);
                } else if (s.equals("*")) {
                    stack.push(a * b);
                } else if (s.equals("/")) {
                    stack.push(b / a);
                }
            }
        }
        return stack.pop();
    }
}
