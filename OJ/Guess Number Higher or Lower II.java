// We are playing the Guess Game. The game is as follows:

// I pick a number from 1 to n. You have to guess which number I picked.

// Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

// However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

// Example:

// n = 10, I pick 8.

// First round:  You guess 5, I tell you that it's higher. You pay $5.
// Second round: You guess 7, I tell you that it's higher. You pay $7.
// Third round:  You guess 9, I tell you that it's lower. You pay $9.

// Game over. 8 is the number I picked.

// You end up paying $5 + $7 + $9 = $21.
// Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.

// Hint:

// The best strategy to play the game is to minimize the maximum loss you could possibly face. Another strategy is to minimize the expected loss. Here, we are interested in the first scenario.
// Take a small example (n = 3). What do you end up paying in the worst case?
// Check out this article if you're still stuck.
// The purely recursive implementation of minimax would be worthless for even a small n. You MUST use dynamic programming.
// As a follow-up, how would you modify your code to solve the problem of minimizing the expected loss, instead of the worst-case loss?
// Credits:
// Special thanks to @agave and @StefanPochmann for adding this problem and creating all test cases.

// Hide Company Tags Google
// Hide Tags Dynamic Programming
// Hide Similar Problems (M) Flip Game II (E) Guess Number Higher or Lower

//                    [1, 3]
// 		   /+1       | +2            \+3
//        [2,3]      [2] [3] win!      [1, 2]
// 		/+2   \+3                 /+1     \+2
// [3]win!  [2]win!               [2]win!    [1]win!

public class Solution {
    public int getMoneyAmount(int n) {
        int[][] map = new int[n + 1][n + 1];
        return helper(map, 1, n);
    }
    
    private int helper(int[][] map, int start, int end) {
        if (start >= end) {
            return 0;
        }
        
        if (map[start][end] != 0) {
            return map[start][end];
        }
        
        int result = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            result = Math.min(result, Math.max(helper(map, i + 1, end), helper(map, start, i - 1)) + i);
        } 
        
        map[start][end] = result;
        return result;
    }
}