// Given an integer array, heapify it into a min-heap array.
//
// For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].
// Have you met this question in a real interview? Yes
// Example
// Given [3,2,1,4,5], return [1,2,3,4,5] or any legal heap array.
//
// Challenge
// O(n) time complexity
//
// Clarification
// What is heap?
//
// Heap is a data structure, which usually have three methods: push, pop and top. where "push" add a new element the heap, "pop" delete the minimum/maximum element in the heap, "top" return the minimum/maximum element.
//
// What is heapify?
// Convert an unordered integer array into a heap array. If it is min-heap, for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].
//
// What if there is a lot of solutions?
// Return any of them.
// -----------------------------------------------------------------------------
// parent = (id - 1) / 2
// left child = id * 2 + 1
// right child = id * 2 + 2
// parent of the last element = (Array length - 1) / 2
// Heapify starts at the parent of the last element in the given array. For every parent, we need to find the smallest element between the current parent and its children, and swap the parent and the smallest element. And this step is repeated until it reaches the end of the tree, if a smaller element is found at one of its children. (sift ddown)
// -----------------------------------------------------------------------------

// O(n)
public class Solution {
    /**
     * @param A: Given an integer array
     * @return: void
     */
    public void heapify(int[] A) {
        // write your code here
        if (A == null || A.length == 0) return;

        for (int i = (A.length - 2) / 2; i >= 0; i--) { // or k * 2 + 1
            siftDown(A, i);
        }
    }

    private void siftDown(int[] A, int k) {
        while (k < A.length) {
            int smallest = k;
            if (k * 2 + 1 < A.length && A[k * 2 + 1] < A[smallest]) {
                smallest = k * 2 + 1;
            }
            if (k * 2 + 2 < A.length && A[k * 2 + 2] < A[smallest]) {
                smallest = k * 2 + 2;
            }
            if (smallest == k) {
                break;
            }
            int temp = A[smallest];
            A[smallest] = A[k];
            A[k] = temp;

            k = smallest;
        }
    }
}

// O(nlogn)
public class Solution {
    /**
     * @param A: Given an integer array
     * @return: void
     */
    public void heapify(int[] A) {
        // write your code here
        if (A == null || A.length == 0) return;

        for (int i = 1; i < A.length ; i++) {
            siftUp(A, i);
        }
    }

    private void siftUp(int[] A, int k) {
        while (k >= 0) {
            if ((k - 1) / 2 >= 0 && A[(k - 1) / 2] > A[k]) {
                int temp = A[(k - 1) / 2];
                A[(k - 1) / 2] = A[k];
                A[k] = temp;

                k = (k - 1) / 2;
            } else {
                break;
            }

        }
    }
}
