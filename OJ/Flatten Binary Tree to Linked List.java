// Given a binary tree, flatten it to a linked list in-place.

// For example,
// Given

//          1
//         / \
//        2   5
//       / \   \
//      3   4   6
// The flattened tree should look like:
//    1
//     \
//      2
//       \
//        3
//         \
//          4
//           \
//            5
//             \
//              6
// click to show hints.

// Hints:
// If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.

// Hide Company Tags Microsoft
// Hide Tags Tree Depth-first Search

/*********************************************************
 * thoughts: use preorder traversal. But this does not meet the contraint "flattern in-place"
 * conclusion: use D & V. Divide into sub-problems. i.e. flattern the smallest subtree first (2 -> 3 -> 4)
 * Note the base case
 ********************************************************/

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
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        
        helper(root);
    }
    
    // return the last node of the linked list
    private TreeNode helper(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root;
        }
        
        if (root.left == null) {
            return helper(root.right);
        }
        
        if (root.right == null) {
            root.right = root.left;
            root.left = null;
            return helper(root.right);
        }
        
        TreeNode left = helper(root.left);
        TreeNode right = helper(root.right);
        
        left.right = root.right;
        root.right = root.left;
        root.left = null;

        return right;
    }
}