// Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

// An example is the root-to-leaf path 1->2->3 which represents the number 123.

// Find the total sum of all root-to-leaf numbers.

// For example,

//     1
//    / \
//   2   3
// The root-to-leaf path 1->2 represents the number 12.
// The root-to-leaf path 1->3 represents the number 13.

// Return the sum = 12 + 13 = 25.

// Hide Tags Tree Depth-first Search
// Hide Similar Problems (E) Path Sum (H) Binary Tree Maximum Path Sum

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
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int sum = dfs(root, 0);
        return sum;
    }
    
    public int dfs(TreeNode root, int num) {
        if (root.left == null && root.right == null) {
            return num * 10 + root.val;
        }
        
        int left = 0;
        if (root.left != null) {
            left = dfs(root.left, num * 10 + root.val);
        }
        
        int right = 0;
        if (root.right != null) {
            right = dfs(root.right, num * 10 + root.val);
        }
        
        return left + right;
    }
}