// Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

// Note:
// A subtree must include all of its descendants.
// Here's an example:
//     10
//     / \
//    5  15
//   / \   \ 
//  1   8   7
// The Largest BST Subtree in this case is the highlighted one. 
// The return value is the subtree's size, which is 3.
// Hint:

// You can recursively use algorithm similar to 98. Validate Binary Search Tree at each node of the tree, which will result in O(nlogn) time complexity.
// Follow up:
// Can you figure out ways to solve it with O(n) time complexity?

// Hide Company Tags Microsoft
// Hide Tags Tree

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Bottom up D&V. Each node is visited twice so the time complexity is O(n) where n = num of nodes
public class Solution {
    public int largestBSTSubtree(TreeNode root) {
        return helper(root).maxSize;
    }
    
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        ResultType result = new ResultType(1, 1, Integer.MAX_VALUE, Integer.MIN_VALUE, false);
        
        if (left.isBST && right.isBST) {
            if ((root.left == null || root.left.val < root.val) && (root.right == null || root.right.val > root.val)
                && left.upper < root.val && right.lower > root.val) {
                result.size = left.size + right.size + 1;
                result.isBST = true;
            }
        }
        
        result.lower = Math.min(root.val, Math.min(left.lower, right.lower));
        result.upper = Math.max(root.val, Math.max(left.upper, right.upper));
        result.maxSize = Math.max(result.size, Math.max(left.maxSize, right.maxSize));
        return result;
    }
}

class ResultType {
    int size;
    int maxSize;
    int lower;
    int upper;
    boolean isBST;
    public ResultType(int size, int maxSize, int lower, int upper, boolean isBST) {
        this.size = size;
        this.maxSize = maxSize;
        this.lower = lower;
        this.upper = upper;
        this.isBST = isBST;
    }
}