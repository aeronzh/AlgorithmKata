// Given a string s and a dictionary of words dict, determine if s can be break into a space-separated sequence of one or more dictionary words.
//
// Have you met this question in a real interview? Yes
// Example
// Given s = "lintcode", dict = ["lint", "code"].
//
// Return true because "lintcode" can be break as "lint code".
//
// Tags
// String Dynamic Programming

public class Solution {
    /**
     * @param s: A string s
     * @param dict: A dictionary of words dict
     */
    public boolean wordBreak(String s, Set<String> dict) {
        // write your code here
        if (s == null || s.length() == 0) {
            return true;
        }

        if (dict == null || dict.size() == 0) {
            return false;
        }

        int maxLen = getMaxLen(dict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = i - 1; j >= i - maxLen && j >= 0; j--) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }

        return dp[s.length()];
    }

    private int getMaxLen(Set<String> dict) {
        int max = 0;
        for (String s : dict) {
            max = Math.max(max, s.length());
        }

        return max;
    }
}

// TLE
public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        
        for (int i = 1; i <= s.length(); i++) {
            if (wordDict.contains(s.substring(0, i)) && wordBreak(s.substring(i), wordDict)) {
                return true;
            }
        }
        return false;
    }
}