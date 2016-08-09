// Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

// You have the following 3 operations permitted on a word:

// Insert a character
// Delete a character
// Replace a character
// Have you met this question in a real interview? Yes
// Example
// Given word1 = "mart" and word2 = "karma", return 3.

// Tags 
// String Dynamic Programming

public class Solution {
    /**
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        // write your code here
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i < m + 1; i++) {
            dp[i][0] = i;
        }
        
        for (int i = 1; i < n + 1; i++) {
            dp[0][i] = i;
        }
        
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], 
                            Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        
        return dp[m][n];
    }
}

public class Solution {
    /**
     * @param word1 & word2: Two string.
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        // write your code here
        if (word1 == null || word2 == null) {
            return 0;
        }
        
        HashMap<String, Integer> map = new HashMap<>();
        return memoization(word1, 0, word2, 0, map);
    }
    
    private int memoization(String word1, int pos1, String word2, int pos2, HashMap<String, Integer> map) {
        String key = pos1 + "," + pos2;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        
        if (pos1 == word1.length() && pos2 == word2.length()) {
            return 0;
        }
        
        if (pos1 == word1.length()) {
            return word2.length() - pos2;    
        }
        
        if (pos2 == word2.length()) {
            return word1.length() - pos1;
        }
        
        int min = Integer.MAX_VALUE;
        
        if (word1.charAt(pos1) == word2.charAt(pos2)) {
            min = memoization(word1, pos1 + 1, word2, pos2 + 1, map);
        } else{
            min = Math.min(memoization(word1, pos1 + 1, word2, pos2 + 1, map),
                    Math.min(memoization(word1, pos1 + 1, word2, pos2, map), 
                            memoization(word1, pos1, word2, pos2 + 1, map))) + 1;
        } 
        
        map.put(key, min);
        return min;
    }
}