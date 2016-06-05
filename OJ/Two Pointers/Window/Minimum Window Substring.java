// Given a string source and a string target, find the minimum window in source which will contain all the characters in target.

//  Notice

// If there is no such window in source that covers all characters in target, return the emtpy string "".

// If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in source.

// Have you met this question in a real interview? Yes
// Clarification
// Should the characters in minimum window has the same order in target?

// Not necessary.
// Example
// For source = "ADOBECODEBANC", target = "ABC", the minimum window is "BANC"

// Challenge 
// Can you do it in time complexity O(n) ?

public class Solution {
    /**
     * @param source: A string
     * @param target: A string
     * @return: A string denote the minimum window
     *          Return "" if there is no such a string
     */
    public String minWindow(String source, String target) {
        // write your code
        if (source == null || target == null || source.length() < target.length()) {
            return "";
        }
        
        HashMap<Character, Integer> targetMap = new HashMap<Character, Integer>();
        HashMap<Character, Integer> sourceMap = new HashMap<Character, Integer>();
        
        for (char c : target.toCharArray()) {
            if (targetMap.containsKey(c)) {
                targetMap.put(c, targetMap.get(c) + 1);
            } else {
                targetMap.put(c, 1);
            }
        }
        
        int min = Integer.MAX_VALUE;
        String minSubstring = "";
        
        int j = 0;
        for (int i = 0; i < source.length(); i++) {
            while (j < source.length()) {
                char c = source.charAt(j);
                if (!valid(sourceMap, targetMap)) {
                    if (sourceMap.containsKey(c)) {
                        sourceMap.put(c, sourceMap.get(c) + 1);
                    } else {
                        sourceMap.put(c, 1);
                    }
                } else {
                    break;
                }
                j++;
            }
            
            if (j - i < min && valid(sourceMap, targetMap)) {
                min = j - i;
                minSubstring = source.substring(i, j);
            }
            
            sourceMap.put(source.charAt(i), sourceMap.get(source.charAt(i)) - 1);
        }
        
        return minSubstring;
    }
    
    private boolean valid(HashMap<Character, Integer> sourceMap, HashMap<Character, Integer> targetMap) {
        for (char c : targetMap.keySet()) {
            if (!sourceMap.containsKey(c) || targetMap.get(c) > sourceMap.get(c)) {
                return false;
            }
        }
        
        return true;
    }
}