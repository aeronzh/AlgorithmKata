// Given an expression string array, return the Polish notation of this expression. (remove the parentheses)
//
// Have you met this question in a real interview? Yes
// Clarification
// Definition of Polish Notation:
//
// http://en.wikipedia.org/wiki/Polish_notation
// http://baike.baidu.com/view/7857952.htm
// Example
// For the expression [(5 − 6) * 7] (which represented by ["(", "5", "−", "6", ")", "*", "7"]), the corresponding polish notation is [* - 5 6 7] (which the return value should be ["*", "−", "5", "6", "7"]).

public class Solution {
    /**
     * @param expression: A string array
     * @return: The Polish notation of this expression
     */
    public ArrayList<String> convertToPN(String[] expression) {
        // write your code here
        ArrayList<String> result = new ArrayList<String>();
        if (expression == null || expression.length == 0) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        int base = 0;
        int weight = 0;

        for (int i = 0; i <= expression.length; i++) {
            if (i != expression.length) {
                if (expression[i].equals("(")) {
                    base += 10;
                    continue;
                } else if (expression[i].equals(")")) {
                    base -= 10;
                    continue;
                }

                if (expression[i].equals("+") || expression[i].equals("-")) {
                    weight = base + 1;
                } else if (expression[i].equals("*") || expression[i].equals("/")) {
                    weight = base + 2;
                } else {
                    weight = Integer.MAX_VALUE;
                }
            }

            TreeNode right = i == expression.length ? new TreeNode("#", Integer.MIN_VALUE) :
                                                        new TreeNode(expression[i], weight);

            while (!stack.isEmpty()) {
                // beware of the equal conditions. Consider case "999 / 3 / 3 / 3"
                if (stack.peek().weight >= right.weight) {
                    TreeNode mid = stack.pop();
                    if (stack.isEmpty()) {
                        right.left = mid;
                    } else {
                        TreeNode left = stack.peek();
                        if (left.weight >= right.weight) {
                            left.right = mid;
                        } else {
                            right.left = mid;
                        }
                    }
                } else {
                    break;
                }
            }

            stack.push(right);
        }

        preorder(result, stack.peek().left);
        return result;
    }

    private void preorder(ArrayList<String> result, TreeNode root) {
        if (root == null) {
            return;
        }

        result.add(root.token);
        preorder(result, root.left);
        preorder(result, root.right);
    }
}

class TreeNode {
    String token;
    int weight;
    TreeNode left;
    TreeNode right;
    public TreeNode(String token, int weight) {
        this.token = token;
        this.weight = weight;
        left = right = null;
    }
}
