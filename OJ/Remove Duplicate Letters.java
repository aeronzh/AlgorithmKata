// Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

// Example:
// Given "bcabc"
// Return "abc"

// Given "cbacdcbc"
// Return "acdb"

// Credits:
// Special thanks to @dietpepsi for adding this problem and creating all test cases.

// Hide Company Tags Google
// Hide Tags Stack Greedy

// -----
// Scan twice.
// First scan: build a map, count how many times each character have appeared
// Second scan: decrease the count of each character. 
//              If current char is already in the stack, continue. 
//              Else, compare current char with the top of the stack. 
//              If the char at the top of the stack is lexicographically greater, check if count is still more than 0. If so, pop
// -----

public class Solution {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        char[] map = new char[26];
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        
        boolean[] visited = new boolean[26];
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            map[c - 'a']--;
            
            // already in stack
            if (visited[c - 'a']) {
                continue;
            }

            while (!stack.isEmpty() && stack.peek() > c && map[stack.peek() - 'a'] > 0) {
                visited[stack.pop() - 'a'] = false;
            }

            stack.push(c);
            visited[c - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        return sb.toString(); 
    }
}