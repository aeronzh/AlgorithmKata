public class Solution {
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        for (int i = s.length(); i >= 0; i--) {
            String s1 = s.substring(0, i);
            if (isPalindrome(s1)) {
                String s2 = s.substring(i);
                String s2Rev = new StringBuilder(s2).reverse().toString();
                return s2Rev + s;
            }
        }
        
        return "";
    }
    
    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
}