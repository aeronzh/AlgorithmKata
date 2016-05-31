// Given a binary tree, find the length of the longest consecutive sequence path.
//
// The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
//
// For example,
//    1
//     \
//      3
//     / \
//    2   4
//         \
//          5
// Longest consecutive sequence path is 3-4-5, so return 3.
//    2
//     \
//      3
//     /
//    2
//   /
//  1
// Longest consecutive sequence path is 2-3,not3-2-1, so return 2.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return helper(root, 1);
    }

    private int helper(TreeNode root, int preMax) {
        if (root.left == null && root.right == null) {
            return preMax;
        }

        int left = 0;
        int right = 0;

        if (root.left != null) {
            if (root.val + 1 == root.left.val) {
                left = helper(root.left, preMax + 1);
            } else {
                left = helper(root.left, 1);
            }

        }

        if (root.right != null) {
            if (root.val + 1 == root.right.val) {
                right = helper(root.right, preMax + 1);
            } else {
                right = helper(root.right, 1);
            }
        }

        return Math.max(preMax, Math.max(left, right));
    }
}
