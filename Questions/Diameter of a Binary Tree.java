// http://www.geeksforgeeks.org/diameter-of-a-binary-tree/

public class BinaryTreeDiameter {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        System.out.println(diameter(root));
    }

    // The longest path in a tree is the max of:
    // 1. longest path between leaves that goes through the root
    // 2. longest path in the left subtree
    // 3. longest path in the right subtree
    private static int diameter(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // height is the longest path in the subtrees
        int lHeight = getHeight(root.left);
        int rHeight = getHeight(root.right);

        int lDiameter = diameter(root.left);
        int rDiameter = diameter(root.right);

        return Math.max(lHeight + rHeight + 1, Math.max(lDiameter, rDiameter));
    }

    private static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = getHeight(root.left);
        int right = getHeight(root.right);

        return Math.max(left, right) + 1;
    }
}

class TreeNode {
    int val;
    TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}