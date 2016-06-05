// Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

// If two nodes are in the same row and column, the order should be from left to right.

// Examples:

// Given binary tree [3,9,20,null,null,15,7],
//    3
//   /\
//  /  \
//  9  20
//     /\
//    /  \
//   15   7
// return its vertical order traversal as:
// [
//   [9],
//   [3,15],
//   [20],
//   [7]
// ]
// Given binary tree [3,9,8,4,0,1,7],
//      3
//     /\
//    /  \
//    9   8
//   /\  /\
//  /  \/  \
//  4  01   7
// return its vertical order traversal as:
// [
//   [4],
//   [9],
//   [3,0,1],
//   [8],
//   [7]
// ]
// Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
//      3
//     /\
//    /  \
//    9   8
//   /\  /\
//  /  \/  \
//  4  01   7
//     /\
//    /  \
//    5   2
// return its vertical order traversal as:
// [
//   [4],
//   [9,5],
//   [3,0,1],
//   [8,2],
//   [7]
// ]
// Hide Company Tags Google Snapchat Facebook
// Hide Tags Hash Table
// Hide Similar Problems (E) Binary Tree Level Order Traversal

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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        map.put(0, new ArrayList<Integer>());
        map.get(0).add(root.val);
        Pair rootPair = new Pair(root, 0);
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.offer(rootPair);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                Pair cur = queue.poll();
                if (cur.node.left != null) {
                    process(cur.node.left, map, queue, cur.pos - 1);
                }
                if (cur.node.right != null) {
                    process(cur.node.right, map, queue, cur.pos + 1);
                }
            }
        }
        
        for (List<Integer> list : map.values()) {
            result.add(list);
        }
        
        return result;
    }
    
    private void process(TreeNode node, TreeMap<Integer, List<Integer>> map, Queue<Pair> queue, int pos) {
        Pair child = new Pair(node, pos);
        queue.offer(child);
        if (!map.containsKey(pos)) {
            map.put(pos, new ArrayList<Integer>());
        }
        map.get(pos).add(node.val);
    }
}

class Pair {
    TreeNode node;
    int pos;
    Pair(TreeNode node, int pos) {
        this.node = node;
        this.pos = pos;
    }
}