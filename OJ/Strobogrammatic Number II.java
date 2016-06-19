// A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//
// Find all strobogrammatic numbers that are of length = n.
//
// For example,
// Given n = 2, return ["11","69","88","96"].
//
// Hint:
//
// Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
// Hide Company Tags Google
// Hide Tags Math Recursion
// Hide Similar Problems (E) Strobogrammatic Number (H) Strobogrammatic Number III

public class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> temp = find(n);

        if (n == 1) {
            return temp;
        }

        List<String> result = new ArrayList<String>();
        for (String s : temp) {
            if (s.charAt(0) != '0') {
                result.add(s);
            }
        }

        return result;
    }

    private List<String> find(int n) {
        List<String> result = new ArrayList<String>();

        if (n == 1) {
            result.add("0");
            result.add("1");
            result.add("8");
        } else if (n == 2) {
            result.add("00");
            result.add("11");
            result.add("88");
            result.add("69");
            result.add("96");
        } else {
            List<String> list = find(n - 2);
            for (String s : list) {
                result.add("0" + s + "0");
                result.add("1" + s + "1");
                result.add("8" + s + "8");
                result.add("6" + s + "9");
                result.add("9" + s + "6");
            }
        }

        return result;
    }
}
