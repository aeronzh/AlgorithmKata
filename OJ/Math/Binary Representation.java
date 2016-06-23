// Given a (decimal - e.g. 3.72) number that is passed in as a string, return the binary representation that is passed in as a string. If the fractional part of the number can not be represented accurately in binary with at most 32 characters, return ERROR.

// Example
// For n = "3.72", return "ERROR".

// For n = "3.5", return "11.1".

public class Solution {
    /**
     *@param n: Given a decimal number that is passed in as a string
     *@return: A string
     */
    public String binaryRepresentation(String n) {
        // write your code here
        if (n.indexOf('.') == -1) {
            return parseInteger(n);
        }
        
        String[] params = n.split("\\.");
        String integerStr = parseInteger(params[0]);
        String floatStr = parseFloat(params[1]);
        
        if (floatStr.equals("ERROR")) {
            return floatStr;
        }
        if (floatStr.equals("") || floatStr.equals("0")) {
            return integerStr;
        }
        
        return integerStr + "." + floatStr;
    }
    
    private String parseInteger(String str) {
        int n = Integer.parseInt(str);
        if (str == "" || n == 0) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (n != 0) {
            sb.insert(0, n % 2);
            n = n / 2;
        }
        
        return sb.toString();
    }
    
    private String parseFloat(String str) {
        double d = Double.parseDouble("0." + str);
        StringBuilder sb = new StringBuilder();
        HashSet<Double> set = new HashSet<Double>();
        
        while (d > 0) {
            if (sb.length() > 32 || set.contains(d)) {
                return "ERROR";
            }
            set.add(d);
            d = d * 2;
            if (d >= 1) {
                sb.append("1");
                d -= 1;
            } else {
                sb.append("0");
            }
        }
        
        return sb.toString();
    }
}