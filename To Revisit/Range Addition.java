// Assume you have an array of length n initialized with all 0's and are given k update operations.
//
// Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
//
// Return the modified array after all k operations were executed.
//
// Example:
//
// Given:
//
//     length = 5,
//     updates = [
//         [1,  3,  2],
//         [2,  4,  3],
//         [0,  2, -2]
//     ]
//
// Output:
//
//     [-2, 0, 3, 5, 3]
// Explanation:
//
// Initial state:
// [ 0, 0, 0, 0, 0 ]
//
// After applying operation [1, 3, 2]:
// [ 0, 2, 2, 2, 0 ]
//
// After applying operation [2, 4, 3]:
// [ 0, 2, 5, 5, 3 ]
//
// After applying operation [0, 2, -2]:
// [-2, 0, 3, 5, 3 ]
// Hint:
//
// Thinking of using advanced data structures? You are thinking it too complicated.
// For each update operation, do you really need to update all elements between i and j?
// Update only the first and end element is sufficient.
// The optimal time complexity is O(k + n) and uses O(1) extra space.
// Credits:
// Special thanks to @vinod23 for adding this problem and creating all test cases.
//
// Hide Company Tags Google
// Hide Tags Array

public class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] result = new int[length];
        if (updates == null || updates.length == 0 || updates[0].length == 0) {
            return result;
        }

        for (int[] update : updates) {
            result[update[0]] += update[2];
            if (update[1] != length - 1) {
                result[++update[1]] -= update[2];
            }
        }

        int update = 0;
        for (int i = 0; i < length; i++) {
            update += result[i];
            result[i] = update;
        }

        return result;
    }
}

// scan-line
public class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        if (updates == null || updates.length == 0 || updates[0].length == 0) {
            return new int[length];
        }

        int[] result = new int[length];
        PriorityQueue<Pair> heap = new PriorityQueue<Pair>(new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.pos - b.pos;
            }
        });

        for (int[] update : updates) {
            heap.offer(new Pair(update[0], update[2]));
            heap.offer(new Pair(update[1] + 1, -update[2]));
        }

        int increment = 0;
        for (int i = 0; i < length; i++) {
            while (!heap.isEmpty() && heap.peek().pos == i) {
                increment += heap.poll().val;
            }
            result[i] = increment;
        }

        return result;
    }
}

class Pair {
    int pos;
    int val;
    public Pair(int pos, int val) {
        this.pos = pos;
        this.val = val;
    }
}
