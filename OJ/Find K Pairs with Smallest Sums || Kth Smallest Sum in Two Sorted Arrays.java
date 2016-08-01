// You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

// Define a pair (u,v) which consists of one element from the first array and one element from the second array.

// Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

// Example 1:
// Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

// Return: [1,2],[1,4],[1,6]

// The first 3 pairs are returned from the sequence:
// [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
// Example 2:
// Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

// Return: [1,1],[1,1]

// The first 2 pairs are returned from the sequence:
// [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
// Example 3:
// Given nums1 = [1,2], nums2 = [3],  k = 3 

// Return: [1,3],[2,3]

// All possible pairs are returned from the sequence:
// [1,3],[2,3]
// Credits:
// Special thanks to @elmirap and @StefanPochmann for adding this problem and creating all test cases.

// Hide Company Tags Google Uber
// Hide Tags Heap

// O(nlogn): same as Kth Smallest Element in a Sorted Matrix
public class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return result;
        }
        
        PriorityQueue<Pair> heap = new PriorityQueue<>(k, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.val - b.val;
            }
        });
        heap.offer(new Pair(0, 0, nums1[0] + nums2[0]));
        
        while (!heap.isEmpty() && k-- > 0) {
            Pair cur = heap.poll();
            result.add(new int[]{nums1[cur.y], nums2[cur.x]});
            if (cur.x + 1 < nums2.length) {
                heap.offer(new Pair(cur.x + 1, cur.y, nums1[cur.y] + nums2[cur.x + 1]));
            }
            if (cur.x == 0 && cur.y + 1 < nums1.length) {
                heap.offer(new Pair(cur.x, cur.y + 1, nums1[cur.y + 1] + nums2[cur.x]));
            }
        }
        
        return result;
    }
}

class Pair {
    int x;
    int y;
    int val;
    public Pair(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

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