// Given a linked list, remove the nth node from the end of list and return its head.

//  Notice

// The minimum number of nodes in list is n.

// Have you met this question in a real interview? Yes
// Example
// Given linked list: 1->2->3->4->5->null, and n = 2.

// After removing the second node from the end, the linked list becomes 1->2->3->5->null.

// Challenge 
// Can you do it without getting the length of the linked list?

/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */ 
public class Solution {
    /**
     * @param head: The first node of linked list.
     * @param n: An integer.
     * @return: The head of linked list.
     */
    ListNode removeNthFromEnd(ListNode head, int n) {
        // write your code here
        if (head == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        while (n > 0) {
            fast = fast.next;
            n -= 1;
        }
        
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        
        return dummy.next;
    }
}