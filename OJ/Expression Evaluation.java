// Given an expression string array, return the final result of this expression
//
// Example
// For the expression 2*6-(23+7)/(1+2), input is
//
// [
//   "2", "*", "6", "-", "(",
//   "23", "+", "7", ")", "/",
//   (", "1", "+", "2", ")"
// ],
// return 2
//
// Note
// The expression contains only integer, +, -, *, /, (, ).
// -----------------------------------------------------------------------------
// Reverse Polish Notation: https://en.wikipedia.org/wiki/Reverse_Polish_notation
// - reduce computer memory access and utilize the stack to evaluate expressions
// Polish Notation: https://en.wikipedia.org/wiki/Polish_notation
//  - Used in programming language such as Lisp
// 1. Build Cartesian Min Tree (increasing stack)
// 2. Post order Traversal
// 3. Evaluate by stack:
//    - Iterate the post order array
//    - Push numbers into the stack
//    - If arithmatic operator is found, pop out two numbers from the stack and apply the current operator
// -----------------------------------------------------------------------------

class TreeNode {
    String token;
    int val;
    TreeNode left, right;
    public TreeNode(String token, int val) {
        this.token = token;
        this.val = val;
        left = null;
        right = null;
    }
}

public class Solution {
    /**
     * @param expression: an array of strings;
     * @return: an integer
     */
    public int evaluateExpression(String[] expression) {
        // write your code here
        if (expression == null || expression.length == 0) return 0;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        int base = 0;
        int val = 0;
        for (int i = 0; i <= expression.length; i++) {
            if (i != expression.length) {
                if (expression[i].equals("(")) {
                    base += 10;
                    continue;
                }
                if (expression[i].equals(")")) {
                    base -= 10;
                    continue;
                }
                if (expression[i].equals("+") || expression[i].equals("-")) {
                    val = 1 + base;
                } else if (expression[i].equals("*") || expression[i].equals("/")) {
                    val = 2 + base;
                } else {
                    val = Integer.MAX_VALUE;
                }
            }
            TreeNode right = i == expression.length ?
                                new TreeNode("", Integer.MIN_VALUE) :
                                new TreeNode(expression[i], val);
            while (!stack.isEmpty()) {
                if (right.val <= stack.peek().val) {
                    TreeNode mid = stack.pop();
                    if (stack.isEmpty()) {
                        right.left = mid;
                    } else {
                        TreeNode left = stack.peek();
                        if (left.val < right.val) {
                            right.left = mid;
                        } else {
                            left.right = mid;
                        }
                    }
                } else {
                    break;
                }
            }
            stack.push(right);
        }
        ArrayList<String> rpn = new ArrayList<String>(); // reverse polish notation
        postOrderTraversal(stack.peek().left, rpn);

        return eval(rpn);
    }

    private void postOrderTraversal(TreeNode root, ArrayList<String> rpn) {
        if (root == null) return;
        postOrderTraversal(root.left, rpn);
        postOrderTraversal(root.right, rpn);
        rpn.add(root.token);
    }

    private int eval(ArrayList<String> rpn) {
        int res = 0;
        String operators = "+-*/";
        Stack<String> stack = new Stack<String>();
        for (String s : rpn) {
            if (!operators.contains(s)) {
                stack.push(s);
            } else {
              int a = Integer.valueOf(stack.pop());
              int b = Integer.valueOf(stack.pop());
              if (s.equals("+")) {
                  stack.push(String.valueOf(a + b));
              } else if (s.equals("-")) {
                  stack.push(String.valueOf(b - a));
              } else if (s.equals("*")) {
                  stack.push(String.valueOf(a * b));
              } else if (s.equals("/")) {
                  stack.push(String.valueOf(b / a));
              }
            }
        }
        if (stack.isEmpty()) return 0; // test case: ["(","(","(","(","(",")",")",")",")",")"]
        return Integer.valueOf(stack.pop());
    }
}
