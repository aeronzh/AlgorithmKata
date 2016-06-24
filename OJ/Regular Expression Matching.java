// Implement regular expression matching with support for '.' and '*'.

// '.' Matches any single character.
// '*' Matches zero or more of the preceding element.

// The matching should cover the entire input string (not partial).

// The function prototype should be:
// bool isMatch(const char *s, const char *p)

// Some examples:
// isMatch("aa","a") → false
// isMatch("aa","aa") → true
// isMatch("aaa","aa") → false
// isMatch("aa", "a*") → true
// isMatch("aa", ".*") → true
// isMatch("ab", ".*") → true
// isMatch("aab", "c*a*b") → true
// Hide Company Tags Google Uber Airbnb Facebook Twitter
// Hide Tags Dynamic Programming Backtracking String
// Hide Similar Problems (H) Wildcard Matching

public class Solution {
    public boolean isMatch(String s, String p) {
        return helper(s, 0, p, 0);
    }
    
    private boolean helper(String s, int si, String p, int pi) {
        if (pi >= p.length()) {
            return si >= s.length();
        }
        
        if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
            if (helper(s, si, p, pi + 2)) {
                return true;
            }
            
            for (int i = si; i < s.length(); i++) {
                if (p.charAt(pi) != '.' && s.charAt(i) != p.charAt(pi)) {
                    return false;
                } else if (helper(s, i + 1, p, pi + 2)) {
                    return true;
                }
            }
        } else {
            if (si >= s.length()) {
                return false;
            }
            
            if (p.charAt(pi) == '.' || p.charAt(pi) == s.charAt(si)) {
                return helper(s, si + 1, p, pi + 1);
            }
        }
        
        return false;
    }
}