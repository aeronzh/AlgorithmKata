// Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
//
// Have you met this question in a real interview? Yes
// Example
// Given binary tree {3,9,20,#,#,15,7},
//
//     3
//    / \
//   9  20
//     /  \
//    15   7
//
//
// return its zigzag level order traversal as:
//
// [
//   [3],
//   [20,9],
//   [15,7]
// ]

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
     * @return: A list of lists of integer include
     *          the zigzag level order traversal of its nodes' values
     */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> curLevel = new Stack<TreeNode>();
        Stack<TreeNode> nextLevel = new Stack<TreeNode>();
        curLevel.push(root);
        boolean flag = false;

        while (!curLevel.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            while (!curLevel.isEmpty()) {
                TreeNode cur = curLevel.pop();
                list.add(cur.val);
                if (flag) {
                    if (cur.right != null) {
                        nextLevel.push(cur.right);
                    }
                    if (cur.left != null) {
                        nextLevel.push(cur.left);
                    }
                } else {
                    if (cur.left != null) {
                        nextLevel.push(cur.left);
                    }
                    if (cur.right != null) {
                        nextLevel.push(cur.right);
                    }
                }
            }
            result.add(list);
            flag = !flag;
            curLevel = nextLevel;
            nextLevel = new Stack<TreeNode>();
        }

        return result;
    }
}
