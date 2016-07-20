// Example
// "/home/", => "/home"

// "/a/./b/../../c/", => "/c"

public class Solution {
    /**
     * @param path the original path
     * @return the simplified path
     */
    public String simplifyPath(String path) {
        // Write your code here
        if (path == null || path.length() == 0) {
            return path;
        }
        
        String[] paths = path.split("/");
        Stack<String> stack = new Stack<>();
        
        for (String p : paths) {
            if (p.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!p.equals(".") && !p.equals("")) {
                stack.push(p);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, "/" + stack.pop());
        }
        
        return sb.length() == 0 ? "/" : sb.toString();
    }
}