/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Postorder in ArrayList which contains node values.
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode pre = null;
        TreeNode cur = root;

        while (!stack.isEmpty()) {
            cur = stack.peek();
            // traverse down
            if (pre == null || pre.left == cur || pre.right == cur) {
                if (cur.left != null) {
                    stack.push(cur.left);
                } else if (cur.right != null) {
                    stack.push(cur.right);
                } else {
                    result.add(cur.val);
                    stack.pop();
                }
            } else if (pre == cur.left) {
                // traverse up from left
                if (cur.right != null) {
                    stack.push(cur.right);
                } else {
                    result.add(cur.val);
                    stack.pop();
                }
            } else { // else if (pre == cur.right)
                // traverse up from right
                result.add(cur.val);
                stack.pop();
            }
            pre = cur;
        }

        return result;
    }
}
