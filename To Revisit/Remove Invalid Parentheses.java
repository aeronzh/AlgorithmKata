// Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

// Note: The input string may contain letters other than the parentheses ( and ).

// Examples:
// "()())()" -> ["()()()", "(())()"]
// "(a)())()" -> ["(a)()()", "(a())()"]
// ")(" -> [""]

// Hide Company Tags Facebook
// Hide Tags Depth-first Search Breadth-first Search
// Hide Similar Problems (E) Valid Parentheses

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<String>();
        
        if (s == null) {
            return result;
        }
        
        Queue<String> queue = new LinkedList<String>();
        queue.offer(s);
        boolean flag = false;
        HashSet<String> visited = new HashSet<String>();
        visited.add(s);
        
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (isValid(cur)) {
                result.add(cur);
                flag = true;
            }
            
            if (flag) {
                continue;
            }
            
            for (int i = 0; i < cur.length(); i++) {
                char c = cur.charAt(i);
                if (c != '(' && c != ')') continue;
                
                String newString = cur.substring(0, i) + cur.substring(i + 1);
                if (!visited.contains(newString)) {
                    queue.offer(newString);
                    visited.add(newString);
                }
            }
        }
        
        return result;
    }
    
    private boolean isValid(String s) {
        int n = 0;
        for (char c : s.toCharArray()) {
            if (c =='(') {
                n++;
            } else if (c == ')') {
                n--;
            }
            if (n < 0) {
                return false;
            }
        }
        
        return n == 0;
    }
}