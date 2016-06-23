// Given a binary tree, find all paths that sum of the nodes in the path equals to a given number target.
//
// A valid path is from root node to any of the leaf nodes.
//
// Have you met this question in a real interview? Yes
// Example
// Given a binary tree, and target = 5:
//
//      1
//     / \
//    2   4
//   / \
//  2   3
// return
//
// [
//   [1, 2, 2],
//   [1, 4]
// ]
// Tags
// Binary Tree Binary Tree Traversal

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
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // Write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<Integer> path = new ArrayList<Integer>();
        path.add(root.val);
        dfs(result, path, root, target - root.val);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> path, TreeNode root, int target) {
        if (root.left == null && root.right == null && target == 0) {
            result.add(new ArrayList<Integer>(path));
            return;
        }

        if (root.left != null) {
            path.add(root.left.val);
            dfs(result, path, root.left, target - root.left.val);
            path.remove(path.size() - 1);
        }

        if (root.right != null) {
            path.add(root.right.val);
            dfs(result, path, root.right, target - root.right.val);
            path.remove(path.size() - 1);
        }
    }
}
