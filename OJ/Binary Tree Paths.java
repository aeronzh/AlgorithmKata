// Given a binary tree, return all root-to-leaf paths.
//
// For example, given the following binary tree:
//
//    1
//  /   \
// 2     3
//  \
//   5
// All root-to-leaf paths are:
//
// ["1->2->5", "1->3"]
// Credits:
// Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
//
// Hide Company Tags Google Apple Facebook
// Hide Tags Tree Depth-first Search
// Hide Similar Problems (M) Path Sum II

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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        if (root == null) {
            return result;
        }

        dfs(result, "" + root.val, root);
        return result;
    }

    private void dfs(List<String> result, String path, TreeNode root) {
        if (root.left == null && root.right == null) {
            result.add(path);
            return;
        }

        if (root.left != null) {
            dfs(result, path + "->" + root.left.val, root.left);
        }

        if (root.right != null) {
            dfs(result, path + "->" + root.right.val, root.right);
        }
    }
}
