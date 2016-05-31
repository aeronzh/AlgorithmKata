public class Solution {
    /**
     * @param s: A string 
     * @param p: A string includes "." and "*"
     * @return: A boolean
     */
    public boolean isMatch(String s, String p) {
        // write your code here
        if (s == null || s.length() == 0) {
            return true;
        }
        
        if (p == null || p.length() == 0) {
            return false;
        }
    
        return match(s, 0, p, 0);
    }
    
    private boolean match(String s, int si, String p, int pi) {
        if (si >= s.length() && pi >= p.length()) {
            return true;
        }
        
        if (si >= s.length() || pi >= p.length()) {
            return false;
        }
        
        // if next char in p is *
        if (pi < p.length() - 1 && p.charAt(pi + 1) == '*') {
            // p can match 0 chars in s
            if (match(s, si, p, pi + 2)) {
                return true;
            }
            
            // p can match more than one p[j] in s
            for (int i = si; i < s.length(); i++) {
                if (s.charAt(i) != p.charAt(pi) && p.charAt(pi) != '.') {
                    return false;
                } else if (match(s, i + 1, p, pi + 2)) {
                    return true;
                }
            }
            
            return false;
        } else {
            if (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.') {
                return match(s, si + 1, p, pi + 1);
            } else {
                return false;
            }
        }
    }
}