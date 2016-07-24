// Check a binary tree is completed or not. A complete binary tree is a binary tree that every level is completed filled except the deepest level. In the deepest level, all nodes must be as left as possible. See more definition
//
// Have you met this question in a real interview? Yes
// Example
//     1
//    / \
//   2   3
//  /
// 4
// is a complete binary.
//
//     1
//    / \
//   2   3
//    \
//     4
// is not a complete binary.
//
// Challenge
// Do it in O(n) time

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
        return helper(root).isComplete;
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(true, true, 0);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        if (!left.isComplete || !right.isComplete) {
            return new ResultType(false, false, -1);
        }

        if (left.depth == right.depth) {
            if (left.isFull) {
                return new ResultType(true, right.isFull, left.depth + 1);
            }
        } else if (left.depth == right.depth + 1) {
            if (right.isFull) {
                return new ResultType(true, false, left.depth + 1);
            }
        }

        return new ResultType(false, false, -1);
    }
}

class ResultType {
    boolean isComplete;
    boolean isFull;
    int depth;
    public ResultType(boolean isComplete, boolean isFull, int depth) {
        this.isComplete = isComplete;
        this.isFull = isFull;
        this.depth = depth;
    }
}



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
