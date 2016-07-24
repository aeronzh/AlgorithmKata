// Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

// For example, Given s = “eceba”,

// T is "ece" which its length is 3.

// Hide Company Tags Google
// Hide Tags Hash Table Two Pointers String
// Hide Similar Problems (M) Longest Substring Without Repeating Characters (H) Sliding Window Maximum (H) Longest Substring with At Most K Distinct Characters

public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int j = 0;
        
        for (int i = 0; i < s.length(); i++) {
            while (j < s.length()) {
                char c = s.charAt(j);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    if (map.size() == 2) {
                        break;
                    }
                    map.put(c, 1);
                }
                j++;
            }
            
            max = Math.max(max, j - i);
            
            char c = s.charAt(i);
            if (map.get(c) > 1) {
                map.put(c, map.get(c) - 1);
            } else {
                map.remove(c);
            }
        }
        
        return max;
    }
}