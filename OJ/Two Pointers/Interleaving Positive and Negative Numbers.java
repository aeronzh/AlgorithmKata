// Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.

//  Notice

// You are not necessary to keep the original order of positive integers or negative integers.

// Have you met this question in a real interview? Yes
// Example
// Given [-1, -2, -3, 4, 5, 6], after re-range, it will be [-1, 5, -2, 4, -3, 6] or any other reasonable answer.

// Challenge 
// Do it in-place and without extra memory.

class Solution {
    /**
     * @param A: An integer array.
     * @return: void
     */
    public void rerange(int[] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return;
        }
        
        int pos = 0;
        int neg = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0) {
                neg++;
            } else {
                pos++;
            }
        }
        
        int pos_pr = 0;
        int neg_pr = 1;
        if (pos < neg) {
            neg_pr = 0;
            pos_pr = 1;
        }
        
        while (pos_pr < A.length && neg_pr < A.length) {
            while (pos_pr < A.length && A[pos_pr] > 0) {
                pos_pr += 2;
            }
            
            while (neg_pr < A.length && A[neg_pr] < 0) {
                neg_pr += 2;
            }
            
            if (pos_pr < A.length && neg_pr < A.length) {
                swap(A, pos_pr, neg_pr);   
            }
        }
   }
   
   private void swap(int[] A, int a, int b) {
       int temp = A[a];
       A[a] = A[b];
       A[b] = temp;
   }
}