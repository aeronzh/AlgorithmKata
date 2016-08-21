// Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

// Examples:

// s = "leetcode"
// return 0.

// s = "loveleetcode",
// return 2.
// Note: You may assume the string contain only lowercase letters.

public class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        
        int uniqueIndex = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, 0);
                queue.offer(i);
            }
            map.put(c, map.get(c) + 1);
        }
        
        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (map.get(s.charAt(index)) == 1) {
                return index;
            }
        }
        
        return -1;
    }
}