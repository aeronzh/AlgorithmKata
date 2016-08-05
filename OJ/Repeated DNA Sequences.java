// All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

// Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

// For example,

// Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

// Return:
// ["AAAAACCCCC", "CCCCCAAAAA"].
// Hide Company Tags LinkedIn
// Hide Tags Hash Table Bit Manipulation

// The key is to represent strings as bits, for space efficiency
// A String object inits with 20 bytes
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 11) {
            return result;
        }
        
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);
        
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash << 2) | map.get(s.charAt(i));
            if (i >= 9) {
                hash = hash & ((1 << 20) - 1); // bit mask to remove the first char
                String temp = s.substring(i - 9, i + 1);
                if (!visited.add(hash) && !result.contains(temp)) {
                    result.add(temp);
                }
            }
        }
        
        return result;
    }
}