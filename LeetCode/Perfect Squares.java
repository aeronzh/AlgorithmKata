// Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

// For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

// Credits:
// Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

// Hide Company Tags Google
// Hide Tags Dynamic Programming Breadth-first Search Math
// Hide Similar Problems (E) Count Primes (M) Ugly Number II

public class Solution {
    public int numSquares(int n) {
        if (n == 0) {
            return 0;
        }
        
        int[] dp = new int[n + 1];
        
        for (int i = 1; i < n + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        
        return dp[n];
    }
}

// DFS TLE
public class Solution {
    public int numSquares(int n) {
        if (n == 0) {
            return 0;
        }
        
        List<Integer> nums = new ArrayList<Integer>();
        
        for (int i = 1; i < n; i++) {
            if (i * i < n) {
                nums.add(i * i);
            }
        }
        
        List<Integer> result = new ArrayList<Integer>();
        dfs(nums, result, new ArrayList<Integer>(), n, 0);
        
        return result.size();
    }
    
    private void dfs(List<Integer> nums, List<Integer> result, List<Integer> list, int n, int pos) {
        if (n < 0) {
            return;
        }
        
        if (n == 0) {
            if (list.size() < result.size()) {
                result = new ArrayList<Integer>(list);
            }
            return;
        }
        
        for (int i = pos; i < nums.size(); i++) {
            list.add(nums.get(i));
            dfs(nums, result, list, n - nums.get(i), i);
            list.remove(list.size() - 1);
        }
    }
}