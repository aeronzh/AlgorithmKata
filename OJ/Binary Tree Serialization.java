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
class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                sb.append("#,");
                continue;
            }
            sb.append(cur.val);
            sb.append(",");
            queue.offer(cur.left);
            queue.offer(cur.right);
        }

        return sb.toString().substring(0, sb.length() - 1);
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        if (data == "") {
            return null;
        }

        String[] array = data.split(",");
        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
        queue.add(new TreeNode(Integer.parseInt(array[0])));
        int aIdx = 1;

        for (int i = 0; i < queue.size(); i++) {
            TreeNode cur = queue.get(i);

            if (!array[aIdx].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(array[aIdx]));
                cur.left = left;
                queue.add(left);
            }
            aIdx++;
            if (!array[aIdx].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(array[aIdx]));
                cur.right = right;
                queue.add(right);
            }
            aIdx++;
        }

        return queue.get(0);
    }
}
