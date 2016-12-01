// Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.
//
// Example:
//
// Input:
// [1,2,3]
//
// Output:
// 3
//
// Explanation:
// Only three moves are needed (remember each move increments two elements):
//
// [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

// Math problem
// Observation: he min in the array is always incremented in each moves.
// let x be the final equal value
// sum(array) + (n - 1) * moves = x * n
// x = min(array) + moves
// sum(array) + (n - 1) * moves = (min(array) + moves) * n
// sum(array) - min(array) * n = moves

public class Solution {
    public int minMoves(int[] nums) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int n : nums) {
            sum += n;
            min = Math.min(min, n);
        }

        return sum - min * nums.length;
    }
}
