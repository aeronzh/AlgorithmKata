// One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

//      _9_
//     /   \
//    3     2
//   / \   / \
//  4   1  #  6
// / \ / \   / \
// # # # #   # #
// For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

// Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

// Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

// You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

// Example 1:
// "9,3,4,#,#,1,#,#,2,#,6,#,#"
// Return true

// Example 2:
// "1,#"
// Return false

// Example 3:
// "9,#,#,1"
// Return false

// Credits:
// Special thanks to @dietpepsi for adding this problem and creating all test cases.

// Hide Company Tags Google
// Hide Tags Stack

// beware of test cases such as "#" (true) or "1" (false)
public class Solution {
    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.length() == 0) {
            return true;
        }
        
        String[] arr = preorder.split(",");
        Stack<String> stack = new Stack<String>();
        
        for (String s : arr) {
            if (s.equals("#")) {
                while (!stack.isEmpty() && stack.peek().equals("#")) {
                    stack.pop();
                    // don't need to check stack.peek() == 0 ~ 9
                    if (stack.isEmpty()) {
                        return false;
                    }
                    stack.pop();
                }
            }
            stack.push(s);
        }
        
        return stack.size() == 1 && stack.peek().equals("#");
    }
}