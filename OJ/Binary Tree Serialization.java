// Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
//
// Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
//
// For example, you may serialize the following tree
//
//     1
//    / \
//   2   3
//      / \
//     4   5
// as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
// Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
//
// Credits:
// Special thanks to @Louis1992 for adding this problem and creating all test cases.
//
// Hide Company Tags LinkedIn Google Uber Facebook Amazon Microsoft Yahoo Bloomberg
// Hide Tags Tree Design
// Hide Similar Problems (M) Encode and Decode Strings

// BFS
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                sb.append("#");
            } else {
                sb.append(cur.val);
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            if (!queue.isEmpty()) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }

        String[] strs = data.split(",");
        int strIdx = 1;
        List<TreeNode> queue = new ArrayList<TreeNode>();
        queue.add(new TreeNode(Integer.parseInt(strs[0])));

        for (int i = 0; i < queue.size(); i++) {
            TreeNode cur = queue.get(i);
            if (!strs[strIdx].equals("#")) {
                cur.left = new TreeNode(Integer.parseInt(strs[strIdx]));
                queue.add(cur.left);
            }
            strIdx++;
            if (!strs[strIdx].equals("#")) {
                cur.right = new TreeNode(Integer.parseInt(strs[strIdx]));
                queue.add(cur.right);
            }
            strIdx++;
        }

        return queue.get(0);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));


// DFS
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        buildString(sb, root);
        return sb.toString();
    }

    private void buildString(StringBuilder sb, TreeNode root) {
        if (root == null) {
            sb.append("#,");
            return;
        }

        sb.append(root.val + ",");
        buildString(sb, root.left);
        buildString(sb, root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }

        Queue<String> queue = new LinkedList<String>();
        queue.addAll(Arrays.asList(data.split(",")));
        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        String cur = queue.poll();

        if (cur.equals("#")) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(cur));
        node.left = buildTree(queue);
        node.right = buildTree(queue);

        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
