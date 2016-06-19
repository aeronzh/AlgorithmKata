// A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//
// Write a function to determine if a number is strobogrammatic. The number is represented as a string.
//
// For example, the numbers "69", "88", and "818" are all strobogrammatic.
//
// Hide Company Tags Google
// Hide Tags Hash Table Math
// Hide Similar Problems (M) Strobogrammatic Number II (H) Strobogrammatic Number III

public class Solution {
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) {
            return true;
        }

        HashSet<String> set = new HashSet<String>();
        set.add("0");
        set.add("1");
        set.add("8");
        set.add("00");
        set.add("11");
        set.add("88");
        set.add("69");
        set.add("96");

        int left = 0;
        int right = num.length() - 1;

        while (left < right) {
            String cur = "" + num.charAt(left) + "" + num.charAt(right);
            if (!set.contains(cur)) {
                return false;
            }
            left++;
            right--;
        }

        if (num.length() % 2 != 0 && !set.contains("" + num.charAt(num.length() / 2))) {
            return false;
        }

        return true;
    }
}
