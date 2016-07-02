// Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

// Note: 
// You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

// Follow up:
// What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

// Hint:

// Try to utilize the property of a BST.
// What if you could modify the BST node's structure?
// The optimal runtime complexity is O(height of BST).
// Credits:
// Special thanks to @ts for adding this problem and creating all test cases.

// Hide Company Tags Bloomberg Uber Google
// Hide Tags Binary Search Tree
// Hide Similar Problems (M) Binary Tree Inorder Traversal

// Binary Search: O(logn * logn) logn nodes, each needs O(logn) to count total subtree nodes
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
    public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left);
        if (count + 1 == k) {
            return root.val;
        } else if (count + 1 > k) {
            return kthSmallest(root.left, k);
        } else {
            return kthSmallest(root.right, k - count - 1);
        }
    }
    
    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}

// iterative
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            k--;
            if (k == 0) {
                return cur.val;
            }
            cur = cur.right;
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }
        
        return -1;
    }
}

// inorder
public class Solution {
    int kth = 0;
    int count = 0;
    
    public int kthSmallest(TreeNode root, int k) {
        count = k;
        inorderTraversal(root);
        return kth;
    }
    
    private void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        
        inorderTraversal(root.left);
        count--;
        if (count == 0) {
            kth = root.val;
            return;
        }
        inorderTraversal(root.right);
    }
}

// follow up: add an attribute "leftCount" to TreeNode