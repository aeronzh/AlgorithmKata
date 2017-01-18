// Given a string, sort it in decreasing order based on the frequency of characters.
//
// Example 1:
//
// Input:
// "tree"
//
// Output:
// "eert"
//
// Explanation:
// 'e' appears twice while 'r' and 't' both appear once.
// So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
// Example 2:
//
// Input:
// "cccaaa"
//
// Output:
// "cccaaa"
//
// Explanation:
// Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
// Note that "cacaca" is incorrect, as the same characters must be together.
// Example 3:
//
// Input:
// "Aabb"
//
// Output:
// "bbAa"
//
// Explanation:
// "bbaA" is also a valid answer, but "Aabb" is incorrect.
// Note that 'A' and 'a' are treated as two different characters.
// Hide Company Tags Amazon Google
// Hide Tags Hash Table Heap
// Hide Similar Problems (M) Top K Frequent Elements

public class Solution {
    public String frequencySort(String s) {
        int[] freq = new int[256];
        for (char c : s.toCharArray()) {
            freq[c]++;
        }

        List<Character>[] bucket = new List[s.length() + 1];
        for (int i = 0; i < 256; i++) {
            if (bucket[freq[i]] == null) {
                bucket[freq[i]] = new ArrayList<Character>();
            }
            bucket[freq[i]].add((char)i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i > 0; i--) {
            if (bucket[i] == null) {
                continue;
            }
            for (char c : bucket[i]) {
                for (int j = 0; j < i; j++) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}
