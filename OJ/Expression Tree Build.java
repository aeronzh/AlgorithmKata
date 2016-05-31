// The structure of Expression Tree is a binary tree to evaluate certain expressions. All leaves of the Expression Tree have an number string value. All non-leaves of the Expression Tree have an operator string value.
//
// Now, given an expression array, build the expression tree of this expression, return the root of this expression tree.
//
// Example
// For the expression (2*6-(23+7)/(1+2)) (which can be represented by ["2" "*" "6" "-" "(" "23" "+" "7" ")" "/" "(" "1" "+" "2" ")"]). The expression tree will be like
//
//                  [ - ]
//              /          \
//         [ * ]              [ / ]
//       /     \           /         \
//     [ 2 ]  [ 6 ]      [ + ]        [ + ]
//                      /    \       /      \
//                    [ 23 ][ 7 ] [ 1 ]   [ 2 ] .
// After building the tree, you just need to return root node [-].
// -----------------------------------------------------------------------------
// Cartesian min Tree. Max Tree variation.
// The first element with larger value on either of current element's sides becomes its child
// Each token is assigned a value according to its priority:
// - Numbers are assigned with minimum priority with Integer.MAX_VALUE
// -----------------------------------------------------------------------------
/**
 * Definition of ExpressionTreeNode:
 * public class ExpressionTreeNode {
 *     public String symbol;
 *     public ExpressionTreeNode left, right;
 *     public ExpressionTreeNode(String symbol) {
 *         this.symbol = symbol;
 *         this.left = this.right = null;
 *     }
 * }
 */
class TreeNode {
    int val;
    ExpressionTreeNode etn;
    public TreeNode(String token, int val) {
        this.val = val;
        etn = new ExpressionTreeNode(token);
    }
}
public class Solution {
    /**
     * @param expression: A string array
     * @return: The root of expression tree
     */
    public ExpressionTreeNode build(String[] expression) {
        // write your code here
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
                    val = Integer.MAX_VALUE; // if token is a number
                }
            }
            TreeNode right = i == expression.length ?
                                new TreeNode("", Integer.MIN_VALUE) :
                                new TreeNode(expression[i], val);
            while (!stack.isEmpty()) {
                if (stack.peek().val >= right.val) { // the first greater value on either of its sides becomes its child
                    TreeNode mid = stack.pop();
                    if (stack.isEmpty()) {
                        right.etn.left = mid.etn;
                    } else {
                        TreeNode left = stack.peek();
                        if (left.val < right.val) {
                            right.etn.left = mid.etn;
                        } else {
                            left.etn.right = mid.etn;
                        }
                    }
                } else {
                    break;
                }
            }
            stack.push(right);
        }
        return stack.peek().etn.left;
    }
}
