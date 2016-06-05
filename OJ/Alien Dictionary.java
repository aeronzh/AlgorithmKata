// There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

// For example,
// Given the following words in dictionary,

// [
//   "wrt",
//   "wrf",
//   "er",
//   "ett",
//   "rftt"
// ]
// The correct order is: "wertf".

// Note:
// You may assume all letters are in lowercase.
// If the order is invalid, return an empty string.
// There may be multiple valid order of letters, return any one of them is fine.
// Hide Company Tags Google Airbnb Facebook Twitter Snapchat Pocket Gems
// Hide Tags Graph Topological Sort
// Hide Similar Problems (M) Course Schedule II

public class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        
        // adjacency list
        HashMap<Character, Node> map = new HashMap<Character, Node>();
        
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (!map.containsKey(words[i].charAt(j))) {
                    map.put(words[i].charAt(j), new Node(words[i].charAt(j)));
                }
            }
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            int index = 0;
            int len = Math.min(words[i].length(), words[i + 1].length());
            
            while (index < len) {
                if (words[i].charAt(index) != words[i + 1].charAt(index)) {
                    break;
                }
                index++;
            }
            
            if (index != len) {
                char first = words[i].charAt(index);
                char sec = words[i + 1].charAt(index);
                if (!map.get(first).neighbors.contains(sec)) {
                    map.get(first).neighbors.add(sec);
                    map.get(sec).indegree++;
                }
            }
        }
        
        Queue<Character> queue = new LinkedList<Character>();
        for (Character c : map.keySet()) {
            if (map.get(c).indegree == 0) {
                queue.offer(c);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (!queue.isEmpty()) {
            char cur = queue.poll();
            sb.append(cur);
            for (Character nb : map.get(cur).neighbors) {
                map.get(nb).indegree--;
                if (map.get(nb).indegree == 0) {
                    queue.offer(nb);
                }
            }
        }
        
        return sb.length() == map.size() ? sb.toString() : "";
    }
}

class Node {
    char c;
    HashSet<Character> neighbors;
    int indegree;
    Node(char c) {
        this.c = c;
        neighbors = new HashSet<Character>();
        indegree = 0;
    }
}