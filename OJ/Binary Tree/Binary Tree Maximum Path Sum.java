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
class ResultType {

    int rootToAny;
    int anyToAny;

    public ResultType(int rootToAny, int anyToAny) {
        this.rootToAny = rootToAny;
        this.anyToAny = anyToAny;
    }
}

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxPathSum(TreeNode root) {
        // write your code here
        if (root == null) {
            return 0;
        }

        return helper(root).anyToAny;
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        int rootToAny = Math.max(0, Math.max(left.rootToAny, right.rootToAny)) + root.val;
        int anyToAny = Math.max(Math.max(left.anyToAny, right.anyToAny),
                                 Math.max(0, left.rootToAny) +
                                 Math.max(0, right.rootToAny) + root.val);

        return new ResultType(rootToAny, anyToAny);
    }
}
