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
     * @param k an integer
     * @return a ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // Write your code here
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = dummy;

        while (tail != null) {
            tail = reverse(tail, k);
        }

        return dummy.next;
    }

    // pass in the node before to-be-reversed k nodes
    private ListNode reverse(ListNode head, int k) {

        ListNode tail = head.next;

        for (int i = 0; i < k; i++) {
            if (tail == null) {
                return tail;
            }

            tail = tail.next;
        }

        // reverse
        ListNode pre = head;
        ListNode cur = head.next;
        ListNode reversedTail = head.next;

        while (cur != tail) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        head.next = pre;
        reversedTail.next = cur;

        return reversedTail;
    }
}
