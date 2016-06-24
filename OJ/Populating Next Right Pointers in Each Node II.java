/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode lastHead = root;
        while (lastHead != null) {
            TreeLinkNode dummy = new TreeLinkNode(0);
            TreeLinkNode cur = dummy;
            while (lastHead != null) {
                if (lastHead.left != null) {
                    cur.next = lastHead.left;
                    cur = cur.next;
                }
                if (lastHead.right != null) {
                    cur.next = lastHead.right;
                    cur = cur.next;
                }
                lastHead = lastHead.next;
            }
            lastHead = dummy.next;
        }
    }
}