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
     * @param root, the root of binary tree.
     * @return true if it is a complete binary tree, or false.
     */
    public boolean isComplete(TreeNode root) {
        // Write your code here
        if (root == null) {
            return true;
        }

        return helper(root).isComplete;
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, true, true);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        if (!left.isComplete || !right.isComplete) {
            return new ResultType(0, false, false);
        }

        // depth is the same: left subtree must be full, right subtree is at least complete
        if (left.depth == right.depth) {
            if (left.isFull) {
                return new ResultType(left.depth + 1, right.isFull, true);
            } else {
                return new ResultType(0, false, false);
            }
        }

        // depth is different: left subtree is complete, right subtree is full
        if (left.depth == right.depth + 1) {
            if (right.isFull) {
                return new ResultType(left.depth + 1, false, true);
            } else {
                return new ResultType(0, false, false);
            }
        }

        return new ResultType(0, false, false);
    }
}

class ResultType {
    int depth;
    boolean isFull;
    boolean isComplete;

    public ResultType(int depth, boolean isFull, boolean isComplete) {
        this.depth = depth;
        this.isFull = isFull;
        this.isComplete = isComplete;
    }
}
