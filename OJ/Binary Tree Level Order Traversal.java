// Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
//------------------------------------------------------------------------------
// BFS
// -----------------------------------------------------------------------------
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
      * @return: Level order a list of lists of integer
      */
     public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
         // write your code here
         ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

         if (root == null) return res;

         Queue<TreeNode> queue = new LinkedList<TreeNode>();
         queue.add(root);

         while (!queue.isEmpty()) {
             ArrayList<Integer> cur = new ArrayList<Integer>();
             int size = queue.size();
             for (int i = 0; i < size; i++) {
                 TreeNode node = queue.poll();
                 cur.add(node.val);

                 if (node.left != null) queue.add(node.left);
                 if (node.right != null) queue.add(node.right);
             }
             res.add(cur);
         }
         return res;
     }
 }

// -----------------------------------------------------------------------------
// DFS: do DFS for each level in the tree. Need to computer max depth first
// -----------------------------------------------------------------------------
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: Level order a list of lists of integer
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write your code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        if (root == null) return res;

        int height = maxHeight(root);
        for (int i = 0; i < height; i++) {
            ArrayList<Integer> curLevel = new ArrayList<Integer>();
            levelTraversal(root, i, curLevel);
            res.add(curLevel);
        }
        return res;
    }

    private int maxHeight(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = maxHeight(root.left);
        int rightHeight = maxHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    private void levelTraversal(TreeNode root, int level, ArrayList<Integer> curLevel) {
        if (root == null) return;
        if (level == 0) {
            curLevel.add(root.val);
            return;
        }

        levelTraversal(root.left, level - 1, curLevel);
        levelTraversal(root.right, level - 1, curLevel);
    }

}
