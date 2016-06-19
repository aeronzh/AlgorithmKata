// Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

// Note:
// Given target value is a floating point.
// You may assume k is always valid, that is: k ≤ total nodes.
// You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
// Follow up:
// Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

// Hint:

// Consider implement these two helper functions:
// getPredecessor(N), which returns the next smaller node to N.
// getSuccessor(N), which returns the next larger node to N.
// Try to assume that each node has a parent pointer, it makes the problem much easier.
// Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
// You would need two stacks to track the path in finding predecessor and successor node separately.
// Hide Company Tags Google
// Hide Tags Tree Stack
// Hide Similar Problems (M) Binary Tree Inorder Traversal (E) Closest Binary Search Tree Value

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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Queue<Integer> queue = new LinkedList<Integer>();
        
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (queue.size() < k) {
                queue.offer(cur.val);
            } else {
                if (Math.abs(queue.peek() - target) > Math.abs(cur.val - target)) {
                    queue.poll();
                    queue.offer(cur.val);
                } else {
                    break; // The rest will always be larger
                }
            }
            cur = cur.right;
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }
        
        return new ArrayList<Integer>(queue);
    }
}