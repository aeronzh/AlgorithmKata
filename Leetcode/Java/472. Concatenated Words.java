// Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
//
// A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
//
// Example:
// Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
//
// Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
//
// Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
//  "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
// "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
// Note:
// The number of elements of the given array will not exceed 10,000
// The length sum of elements in the given array will not exceed 600,000.
// All the input string will only include lower case letters.
// The returned elements order does not matter.
// Hide Tags Dynamic Programming Trie Depth-first Search
// Hide Similar Problems (H) Word Break II

// DP, similar to word break
public class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> set = new HashSet<>();

        Arrays.sort(words, new Comparator<String>() {
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        });

        for (String word : words) {
            if (isConcatenated(word, set)) {
                result.add(word);
            }
            set.add(word); // longer shorter strings can form longer strings
        }

        return result;
    }

    private boolean isConcatenated(String word, Set<String> set) {
        if (set.size() == 0) {
            return false;
        }

        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= word.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && set.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[word.length()];
    }
}
