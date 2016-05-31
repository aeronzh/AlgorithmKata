// Given an integer array with no duplicates. A max tree building on this array is defined as follow:
//
// The root is the maximum number in the array
// The left subtree and right subtree are the max trees of the subarray divided by the root number.
// Construct the max tree by the given array.
//
// Example
// Given [2, 5, 6, 0, 3, 1], the max tree constructed by this array is:
//
//     6
//    / \
//   5   3
//  /   / \
// 2   0   1
// Challenge
// O(n) time and memory.
//------------------------------------------------------------------------------
// Cartesian max tree. Related to expression tree build
// Max tree is not always a heap. It's not a complete tree.
// # Top down recursion: worst case O(n^2) if input [1,2,3,4,5,6,7]
// 1. Find the largest element in the array and make it root.
// 2. Recusively get the largest element in the left and right subarray. Make them children.
// # Bottem up: O(n): every element only goes into the stack once, and pop once.
// For each element:
// 1. Find the first element on the left that is larger than the current element
// 2. Find the first element on the right that is larger than the current element
// Note: use a dummy tail to force the stack popping all the elements in the end.
//------------------------------------------------------------------------------
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
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] A) {
        // write your code here

        Stack<TreeNode> stack = new Stack<TreeNode>();

        for (int i = 0; i <= A.length; i++) {

            TreeNode right = i == A.length? new TreeNode(Integer.MAX_VALUE) : new TreeNode(A[i]); // dummy tail

            while (!stack.isEmpty()) {
                if (stack.peek().val < right.val) {
                    TreeNode cur = stack.pop();
                    if (stack.isEmpty()) {
                        right.left = cur;
                    } else {
                        TreeNode left = stack.peek();
                        if (left.val > right.val) {
                            right.left = cur;
                        } else {
                            left.right = cur;
                        }
                    }
                } else {
                    break;
                }
            }
            stack.push(right);
        }
        return stack.peek().left;
    }
}
