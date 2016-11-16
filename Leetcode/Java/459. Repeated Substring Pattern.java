// Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
//
// Example 1:
// Input: "abab"
//
// Output: True
//
// Explanation: It's the substring "ab" twice.
// Example 2:
// Input: "aba"
//
// Output: False
// Example 3:
// Input: "abcabcabcabc"
//
// Output: True
//
// Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)

// brute force
public class Solution {
    public boolean repeatedSubstringPattern(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        for (int i = 1; i <= str.length() / 2; i++) {
            if (str.length() % i == 0 && isRepeated(str.substring(0, i), str.substring(i))) {
                return true;
            }
        }

        return false;
    }

    private boolean isRepeated(String substr, String str) {
        for (int i = 0; i < str.length(); i += substr.length()) {
            if (!substr.equals(str.substring(i, i + substr.length()))) {
                return false;
            }
        }

        return true;
    }

    // mod operation is more costly than substring?
    // private boolean isRepeated(String substr, String str) {
    //     for (int i = 0; i < str.length(); i++) {
    //         if (str.charAt(i) != substr.charAt(i % substr.length())) {
    //             return false;
    //         }
    //     }
    //
    //     return true;
    // }
}
