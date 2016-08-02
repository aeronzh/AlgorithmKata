// Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

// For example,
// Given:
// s1 = "aabcc",
// s2 = "dbbca",

// When s3 = "aadbbcbcac", return true.
// When s3 = "aadbbbaccc", return false.

// Hide Tags Dynamic Programming String

public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        // state: dp[i][j] can fisrt i chars in s1 and first j chars in s2 form the first i + j chars in s3?
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        
        int m = s1.length();
        int n = s2.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        
        for (int i = 1; i < m + 1; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        
        for (int i = 1; i < n + 1; i++) {
            dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }
        
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) || 
                    (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1))) {
                    dp[i][j] = true;
                }
            }
        }
        
        return dp[m][n];
    }
}

// DFS + memoization
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        // state: dp[i][j] can fisrt i chars in s1 and first j chars in s2 form the first i + j chars in s3?
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        
        return helper(s1, 0, s2, 0, s3, 0, new HashMap<String, Boolean>());
    }
    
    private boolean helper(String s1, int pos1, String s2, int pos2, String s3, int pos3, HashMap<String, Boolean> dp) {
        String key = pos1 + "," + pos2 + "," + pos3;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        
        if (pos3 == s3.length()) {
            return true;
        }
        
        boolean isInterleave = false;
        if (pos1 < s1.length() && s1.charAt(pos1) == s3.charAt(pos1 + pos2)) {
            isInterleave |= helper(s1, pos1 + 1, s2, pos2, s3, pos3 + 1, dp);
        }
        
        if (pos2 < s2.length() && s2.charAt(pos2) == s3.charAt(pos1 + pos2)) {
            isInterleave |= helper(s1, pos1, s2, pos2 + 1, s3, pos3 + 1, dp);
        }
        
        dp.put(key, isInterleave);
        return isInterleave;
    }
}