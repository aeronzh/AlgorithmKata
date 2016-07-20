// Given two integer arrays sorted in ascending order and an integer k. Define sum = a + b, where a is an element from the first array and b is an element from the second one. Find the kth smallest sum out of all possible sums.

// Have you met this question in a real interview? Yes
// Example
// Given [1, 7, 11] and [2, 4, 6].

// For k = 3, return 7.

// For k = 4, return 9.

// For k = 8, return 15.

// Challenge 
// Do it in either of the following time complexity:

// O(k log min(n, m, k)). where n is the size of A, and m is the size of B.
// O( (m + n) log maxValue). where maxValue is the max number in A and B.
// Tags 
// Heap Priority Queue Sorted Matrix

public class Solution {
    /**
     * @param A an integer arrays sorted in ascending order
     * @param B an integer arrays sorted in ascending order
     * @param k an integer
     * @return an integer
     */
    public int kthSmallestSum(int[] A, int[] B, int k) {
        // Write your code here
        PriorityQueue<Node> heap = new PriorityQueue<>(k, new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return a.sum - b.sum;
            }
        });
        heap.offer(new Node(0, 0, A[0] + B[0]));
        
        HashSet<String> set = new HashSet<>();
        set.add("0,0");
        
        for (int i = 0; i < k - 1; i++) {
            Node min = heap.poll();
            
            if (min.x + 1 < A.length && set.add(min.x + 1 + "," + min.y)) {
                heap.offer(new Node(min.x + 1, min.y, A[min.x + 1] + B[min.y]));
            }
            if (min.y + 1 < B.length && set.add(min.x + "," + (min.y + 1))) {
                heap.offer(new Node(min.x, min.y + 1, A[min.x] + B[min.y + 1]));
            }
        }
        
        return heap.poll().sum;
    }
}

class Node {
    int x;
    int y;
    int sum;
    public Node(int x, int y, int sum) {
        this.x = x;
        this.y = y;
        this.sum = sum;
    }
}