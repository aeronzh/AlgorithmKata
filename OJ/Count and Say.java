public class Solution {
    public String countAndSay(int n) {
        if (n < 1) {
            return "";
        }
        
        String result = "1";
        while (n > 1) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            int j = 0;
            while (j < result.length()) {
                while (j < result.length() && result.charAt(j) == result.charAt(i)) {
                    j++;
                }
                sb.append("" + (j - i) + result.charAt(i));
                i = j;
            }
            result = sb.toString();
            n--;
        }
        
        return result;
    }
}