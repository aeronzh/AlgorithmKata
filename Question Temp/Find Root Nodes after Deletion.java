// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=197777&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
//         1
//     /       \
//    2        3
// /     \    /   \
// 4     5   6    7
// 
// Delete 2 and 6, return [1, 4, 5]

class Solution {

    public List<TreeNode> findRoots(TreeNode root, HashSet<TreeNode> delete) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        if (root == null) {
            return result;
        }
        
        // corner case: if root is not to be deleted, add root to result
        if (!delete.contains(root)) {
            result.add(root);
        }

        helper(root, delete);
        return result;
    }

    private void helper(List<TreeNode> result, TreeNode root, HashSet<TreeNode> delete) {
        if (root == null) {
            return result;
        }
        
        if (delete.contains(root)) {
            if (root.left != null && !delete.contains(root.left)) {
                result.add(root.left);
            }
            if (root.right != null && !delete.contains(root.right)) {
                result.add(root.right);
            }
        }

        helper(result, root.left, delete);
        helper(result, root.right, delete);

        root.left = null;
        root.right = null;
    }

    // D&V
    private List<TreeNode> helper(TreeNode root, HashSet<TreeNode> delete) {
        if (root == null) {
            return new ArrayList<TreeNode>();
        }

        List<TreeNode> result = new ArrayList<>();
        result.addAll(helper(root.left, delete));
        result.addAll(helper(root.right, delete));

        if (delete.contains(root)) {
            if (root.left != null) {
                result.add(left);
                root.left == null;
            }
            if (root.right != null) {
                result.add(root.right);
                root.right = null;
            }
        }

        return result;
    } 
}