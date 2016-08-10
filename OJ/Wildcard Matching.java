// Implement wildcard pattern matching with support for '?' and '*'.
//
// '?' Matches any single character.
// '*' Matches any sequence of characters (including the empty sequence).
//
// The matching should cover the entire input string (not partial).
//
// The function prototype should be:
// bool isMatch(const char *s, const char *p)
//
// Some examples:
// isMatch("aa","a") → false
// isMatch("aa","aa") → true
// isMatch("aaa","aa") → false
// isMatch("aa", "*") → true
// isMatch("aa", "a*") → true
// isMatch("ab", "?*") → true
// isMatch("aab", "c*a*b") → false
// Hide Company Tags Google Snapchat Facebook
// Hide Tags Dynamic Programming Backtracking Greedy String
// Hide Similar Problems (H) Regular Expression Matching

public class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 0; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (p.charAt(j - 1) == '*') {
                    // dp[i - 1][j]: '*' match s.charAt(i - 1)
                    // dp[i][j - 1]: '*' doesn't match s.charAt(i - 1)
                    if (i > 0 && dp[i - 1][j] || dp[i][j - 1]) {
                        dp[i][j] = true;
                    }
                } else {
                    if (i > 0 && dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?')) {
                        dp[i][j] = true;
                    }
                }
            }
        }

        return dp[m][n];
    }
}

// TLE
public class Solution {
    public boolean isMatch(String s, String p) {
        return helper(s, 0, p, 0);
    }

    private boolean helper(String s, int si, String p, int pi) {
        if (pi == p.length()) {
            return si == s.length();
        }

        if (p.charAt(pi) == '*') {
            for (int i = si; i <= s.length(); i++) {
                if (helper(s, i, p, pi + 1)) {
                    return true;
                }
            }
        } else {
            if (si >= s.length()) {
                return false;
            }
            if (p.charAt(pi) == '?' || p.charAt(pi) == s.charAt(si)) {
                return helper(s, si + 1, p, pi + 1);
            }
        }

        return false;
    }
}

// added memoization
public class Solution {
    /**
     * @param s: A string 
     * @param p: A string includes "?" and "*"
     * @return: A boolean
     */
    public boolean isMatch(String s, String p) {
        // write your code here
        return match(s, 0, p, 0, new HashMap<String, Boolean>());
    }
    
    private boolean match(String s, int pos1, String p, int pos2, HashMap<String, Boolean> map) {
        String key = pos1 + "," + pos2;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        
        if (pos2 == p.length()) {
            return pos1 == s.length();
        }
        
        boolean result = false;
        if (p.charAt(pos2) == '?') {
            result = match(s, pos1 + 1, p, pos2 + 1, map);
        } else if (p.charAt(pos2) == '*') {
            for (int i = pos1; i <= s.length(); i++) {
                if (match(s, i, p, pos2 + 1, map)) {
                    result = true;
                    break;
                }
            }
        } else {
            if (pos1 == s.length()) {
                result = false;
            } else if (s.charAt(pos1) == p.charAt(pos2)) {
                result = match(s, pos1 + 1, p, pos2 + 1, map);
            }
        }
        
        map.put(key, result);
        return result;
    }
}