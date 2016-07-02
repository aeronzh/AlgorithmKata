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
      * @return: An integer.
      */
     public int minDepth(TreeNode root) {
         // write your code here
         if (root == null) {
             return 0;
         }

         return helper(root);
     }

     private int helper(TreeNode root) {
         if (root == null) {
             return Integer.MAX_VALUE;
         }


         if (root.left == null && root.right == null) {
             return 1;
         }

         return Math.min(helper(root.left), helper(root.right)) + 1;
     }
 }

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int minDepth(TreeNode root) {
        // write your code here
        if (root == null) {
            return 0;
        }

        if (root.left == null) {
            return minDepth(root.right) + 1;
        }

        if (root.right == null) {
            return minDepth(root.left) + 1;
        }

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        return Math.min(left, right) + 1;
    }
}
