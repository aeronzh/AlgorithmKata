// Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

// Example 1:
// Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
// Return 16
// The two words can be "abcw", "xtfn".

// Example 2:
// Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
// Return 4
// The two words can be "ab", "cd".

// Example 3:
// Given ["a", "aa", "aaa", "aaaa"]
// Return 0
// No such pair of words.

// Credits:
// Special thanks to @dietpepsi for adding this problem and creating all test cases.

// Hide Company Tags Google
// Hide Tags Bit Manipulation

public class Solution {
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        generateBitmap(map, words);
        
        Arrays.sort(words, new Comparator<String>(){
            public int compare(String a, String b) {
                return b.length() - a.length();
            }
        });
        
        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((map.get(words[i]) & map.get(words[j])) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                    break;
                }
            }
        }
        
        return max;
    }
    
    private void generateBitmap(HashMap<String, Integer> map, String[] words) {
        for (String word : words) {
            int bitmap = 0;
            for (char c : word.toCharArray()) {
                bitmap |= 1 << (c - 'a');
            }
            map.put(word, bitmap);
        }
    }
}