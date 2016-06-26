// Given a binary tree, find all leaves and then remove those leaves. Then repeat the previous steps until the tree is empty.
//
// Example:
// Given binary tree
//           1
//          / \
//         2   3
//        / \
//       4   5
// Returns [4, 5, 3], [2], [1].
//
// Explanation:
// 1. Remove the leaves [4, 5, 3] from the tree
//
//           1
//          /
//         2
// 2. Remove the leaf [2] from the tree
//
//           1
// 3. Remove the leaf [1] from the tree
//
//           []
// Returns [4, 5, 3], [2], [1].
//
// Credits:
// Special thanks to @elmirap for adding this problem and creating all test cases.
//
// Hide Company Tags LinkedIn
// Hide Tags Tree Depth-first Search

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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        TreeNode dummy = new TreeNode(0);
        dummy.left = root;

        while (dummy.left != null) {
            List<Integer> level = new ArrayList<>();
            dfs(level, dummy);
            Collections.sort(level);
            result.add(level);
        }

        return result;
    }

    private void dfs(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null && root.left.left == null && root.left.right == null) {
            list.add(root.left.val);
            root.left = null;
        }

        if (root.right != null && root.right.left == null && root.right.right == null) {
            list.add(root.right.val);
            root.right = null;
        }

        dfs(list, root.left);
        dfs(list, root.right);
    }
}
