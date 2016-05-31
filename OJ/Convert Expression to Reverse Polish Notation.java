// Given an expression string array, return the Reverse Polish notation of this expression. (remove the parentheses)
// Example
// For the expression [3 - 4 + 5] (which denote by ["3", "-", "4", "+", "5"]), return [3 4 - 5 +] (which denote by ["3", "4", "-", "5", "+"])
// -----------------------------------------------------------------------------
// Cartesian Min Tree + Post Order Traversal
// Related to Expression Tree Build
// -----------------------------------------------------------------------------
class TreeNode {
    String token;
    int val;
    TreeNode left, right;
    public TreeNode(String token, int val) {
        this.token = token;
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class Solution {
    /**
     * @param expression: A string array
     * @return: The Reverse Polish notation of this expression
     */
    public ArrayList<String> convertToRPN(String[] expression) {
        // write your code here
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int base= 0;
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

        ArrayList<String> postOrder = new ArrayList<String>();
        postOrderTraversal(stack.peek().left, postOrder);

        return postOrder;
    }

    private void postOrderTraversal(TreeNode root, ArrayList<String> postOrder) {
        if (root == null) return;

        postOrderTraversal(root.left, postOrder);
        postOrderTraversal(root.right, postOrder);
        postOrder.add(root.token);
    }
}
