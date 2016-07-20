// Find K-th largest element in N arrays.

//  Notice

// You can swap elements in the array

// Have you met this question in a real interview? Yes
// Example
// In n=2 arrays [[9,3,2,4,7],[1,2,3,4,8]], the 3rd largest element is 7.

// In n=2 arrays [[9,3,2,4,8],[1,2,3,4,2]], the 1st largest element is 9, 2nd largest element is 8, 3rd largest element is 7 and etc.

public class Solution {
    /**
     * @param arrays a list of array
     * @param k an integer
     * @return an integer, K-th largest element in N arrays
     */
    public int KthInArrays(int[][] arrays, int k) {
        // Write your code here
        if (arrays == null) {
            return -1;
        }
        
        PriorityQueue<Node> heap = new PriorityQueue<>(k, new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return b.val - a.val;
            }
        });
        
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                Arrays.sort(arrays[i]);
                int col = arrays[i].length - 1;
                heap.offer(new Node(arrays[i][col], i, col));
            }
        }
        
        for (int i = 0; i < k; i++) {
            if (heap.isEmpty()) {
                break;
            }
            
            Node cur = heap.poll();
            if (i == k - 1) {
                return cur.val;
            }
            
            if (cur.col != 0) {
                heap.offer(new Node(arrays[cur.row][cur.col - 1], cur.row, cur.col - 1));
            }
        }
        
        return -1;
    }
}

class Node {
    int val;
    int row;
    int col;
    public Node(int val, int row, int col) {
        this.val = val;
        this.row = row;
        this.col = col;
    }
}