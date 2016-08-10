// Given a string S and a string T, count the number of distinct subsequences of T in S.

// A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

// Have you met this question in a real interview? Yes
// Example
// Given S = "rabbbit", T = "rabbit", return 3.

// Challenge 
// Do it in O(n2) time and O(n) memory.

// O(n2) memory is also acceptable if you do not know how to optimize memory.

// Tags 
// String Dynamic Programming

public class Solution {
    /**
     * @param S, T: Two string.
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String S, String T) {
        // write your code here
        // state: dp[i][j] stands for nums of distinct sequences by matching the first i chars in S with the first j chars in T
        int m = S.length();
        int n = T.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = 1;
        }
        
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    // dp[i - 1][j - 1] match the cur char
                    // dp[i - 1][j] does not match the cur char
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[m][n];
    }
}

public class Solution {
    /**
     * @param S, T: Two string.
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String S, String T) {
        // write your code here
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        return helper(S, 0, T, 0, map);
    }
    
    private int helper(String S, int pos1, String T, int pos2, HashMap<String, Integer> map) {
        String key = pos1 + "," + pos2;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        
        if (pos2 == T.length()) {
            return 1;
        }
        
        int count = 0;
        for (int i = pos1; i < S.length(); i++) {
            if (S.charAt(i) == T.charAt(pos2)) {
                count += helper(S, i + 1, T, pos2 + 1, map);
            }
        }
        
        map.put(key, count);
        return count;
    }
}