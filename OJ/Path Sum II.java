// Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

// For example:
// Given the below binary tree and sum = 22,
//               5
//              / \
//             4   8
//            /   / \
//           11  13  4
//          /  \    / \
//         7    2  5   1
// return
// [
//    [5,4,11,2],
//    [5,8,4,5]
// ]
// Hide Company Tags Bloomberg
// Hide Tags Tree Depth-first Search
// Hide Similar Problems (E) Path Sum (E) Binary Tree Paths

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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        dfs(root, result, path, sum - root.val);
        return result;
    }
    
    private void dfs(TreeNode root, List<List<Integer>> result, List<Integer> path, int sum) {
        if (sum == 0 && root.left == null && root.right == null) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        
        if (root.left != null) {
            path.add(root.left.val);
            dfs(root.left, result, path, sum - root.left.val);
            path.remove(path.size() - 1);
        }
        
        if (root.right != null) {
            path.add(root.right.val);
            dfs(root.right, result, path, sum - root.right.val);
            path.remove(path.size() - 1);
        }
    }
}