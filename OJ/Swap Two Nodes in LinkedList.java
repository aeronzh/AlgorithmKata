// Given a linked list and two values v1 and v2. Swap the two nodes in the linked list with values v1 and v2. It's guaranteed there is no duplicate values in the linked list. If v1 or v2 does not exist in the given linked list, do nothing.
//
//  Notice
//
// You should swap the two nodes with values v1 and v2. Do not directly swap the values of the two nodes.
//
// Have you met this question in a real interview? Yes
// Example
// Given 1->2->3->4->null and v1 = 2, v2 = 4.
//
// Return 1->4->3->2->null.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    /**
     * @param head a ListNode
     * @oaram v1 an integer
     * @param v2 an integer
     * @return a new head of singly-linked list
     */
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        // Write your code here
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre1 = null;
        ListNode pre2 = null;
        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == v1) {
                pre1 = cur;
            } else if (cur.next.val == v2) {
                pre2 = cur;
            }
            cur = cur.next;
        }

        if (pre1.next == null || pre2 == null) {
            return head;
        }


        if (pre2.next == pre1) {
            // make sure pre1 is before pre2
            ListNode temp = pre1;
            pre1 = pre2;
            pre2 = temp;
        }

        ListNode node1 = pre1.next;
        ListNode node2 = pre2.next;
        if (pre1.next == pre2) {
            node1.next = node2.next;
            node2.next = node1;
            pre1.next = node2;
        } else {
            pre1.next = node2;
            ListNode temp = node2.next;
            node2.next = node1.next;

            pre2.next = node1;
            node1.next = temp;
        }

        return dummy.next;
    }
}
