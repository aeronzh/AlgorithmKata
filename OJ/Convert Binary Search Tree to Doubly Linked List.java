// Convert a binary search tree to doubly linked list with in-order traversal.

// Have you met this question in a real interview? Yes
// Example
// Given a binary search tree:

//     4
//    / \
//   2   5
//  / \
// 1   3
// return 1<->2<->3<->4<->5

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
 * Definition for Doubly-ListNode.
 * public class DoublyListNode {
 *     int val;
 *     DoublyListNode next, prev;
 *     DoublyListNode(int val) {
 *         this.val = val;
 *         this.next = this.prev = null;
 *     }
 * }
 */ 

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
 * Definition for Doubly-ListNode.
 * public class DoublyListNode {
 *     int val;
 *     DoublyListNode next, prev;
 *     DoublyListNode(int val) {
 *         this.val = val;
 *         this.next = this.prev = null;
 *     }
 * }
 */ 

public class Solution {
    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
    public DoublyListNode bstToDoublyList(TreeNode root) {  
        // Write your code here
        if (root == null) {
            return null;
        }
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        
        DoublyListNode dummy = new DoublyListNode(0);
        DoublyListNode last = dummy;
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            
            DoublyListNode cur = new DoublyListNode(node.val);
            last.next = cur;
            cur.prev = last;
            last = last.next;
            
            node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        
        dummy.next.prev = null;
        return dummy.next;
    }
}

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
 * Definition for Doubly-ListNode.
 * public class DoublyListNode {
 *     int val;
 *     DoublyListNode next, prev;
 *     DoublyListNode(int val) {
 *         this.val = val;
 *         this.next = this.prev = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param root: The root of tree
     * @return: the head of doubly list node
     */
     
    DoublyListNode last = null;
    public DoublyListNode bstToDoublyList(TreeNode root) {  
        // Write your code here
        if (root == null) {
            return null;
        }
        
        DoublyListNode dummy = new DoublyListNode(0);
        last = dummy;
        helper(root);
        
        dummy.next.prev = null;
        return dummy.next;
    }
    
    private void helper(TreeNode cur) {
        if (cur == null) {
            return;
        }
        
        helper(cur.left);
        DoublyListNode newListNode = new DoublyListNode(cur.val);
        newListNode.prev = last;
        last.next = newListNode;
        last = newListNode;
        helper(cur.right);
    }
}