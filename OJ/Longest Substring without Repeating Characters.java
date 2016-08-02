// Given a string, find the length of the longest substring without repeating characters.

// Have you met this question in a real interview? Yes
// Example
// For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.

// For "bbbbb" the longest substring is "b", with the length of 1.

// Challenge 
// O(n) time

public class Solution {
    /**
     * @param s: a string
     * @return: an integer 
     */
    public int lengthOfLongestSubstring(String s) {
        // write your code here
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        HashSet<Character> set = new HashSet<Character>();
        int max = 1;
        
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j < s.length()) {
                char c = s.charAt(j);
                if (!set.contains(c)) {
                    set.add(c);
                } else {
                    break;
                }
                j++;
            }
            
            max = Math.max(max, j - i);
            set.remove(s.charAt(i));
        }
        
        return max;
    }
}

public class Solution {
    /**
     * @param s: a string
     * @return: an integer 
     */
    public int lengthOfLongestSubstring(String s) {
        // write your code here
        int longest = 0;
        int j = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i  = 0; i < s.length(); i++) {
            while (j < s.length()) {
                if (!set.add(s.charAt(j))) {
                    break;
                }
                j++;
            }
            
            longest = Math.max(longest, j - i);
            set.remove(s.charAt(i));
        }
        
        return longest;
    }
}