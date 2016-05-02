public class Solution {
    /**
     * @param s1 A string
     * @param s2 Another string
     * @return whether s2 is a scrambled string of s1
     */
    public boolean isScramble(String s1, String s2) {
        // Write your code here
        if (s1.length() != s2.length()) {
            return false;
        }

        if (s1.length() == 0 || s1.equals(s2)) {
            return true;
        }

        if (!isValid(s1, s2)) {
            return false;
        }

        boolean isScramble = false;

        for (int i = 1; i < s1.length(); i++) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i, s1.length());

            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, s2.length());

            String s23 = s2.substring(s2.length() - i, s2.length());
            String s24 = s2.substring(0, s2.length() - i);

            isScramble = isScramble || (isScramble(s11, s21) && isScramble(s12, s22));
            isScramble = isScramble || (isScramble(s11, s23) && isScramble(s12, s24));
        }

        return isScramble;
    }

    private boolean isValid(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);

        s1 = new String(c1);
        s2 = new String(c2);

        if (s1.equals(s2)) {
            return true;
        }

        return false;
    }
}

// DP
public class Solution {
    /**
     * @param s1 A string
     * @param s2 Another string
     * @return whether s2 is a scrambled string of s1
     */
    public boolean isScramble(String s1, String s2) {
        // Write your code here
        if (s1.length() != s2.length()) {
            return false;
        }

        int len = s1.length();
        boolean[][][] dp = new boolean[len][len][len + 1];
        boolean[][][] visited = new boolean[len][len][len + 1];

        return scrambleHelper(s1, 0, s2, 0, len, dp, visited);
    }

    private boolean scrambleHelper(String s1, int s1Start, String s2, int s2Start, int len, boolean[][][] dp, boolean[][][] visited) {

        if (visited[s1Start][s2Start][len]) {
            return dp[s1Start][s2Start][len];
        }

        if (s1.length() == 0 || s1.equals(s2)) {
            visited[s1Start][s2Start][len] = true;
            dp[s1Start][s2Start][len] = true;
            return true;
        }

        if (!isValid(s1, s2)) {
            visited[s1Start][s2Start][len] = true;
            dp[s1Start][s2Start][len] = false;
            return false;
        }

        boolean isScramble = false;

        for (int i = 1; i < s1.length(); i++) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i, s1.length());

            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, s2.length());

            String s23 = s2.substring(s2.length() - i, s2.length());
            String s24 = s2.substring(0, s2.length() - i);

            isScramble = isScramble
                        || (scrambleHelper(s11, s1Start, s21, s2Start, i, dp, visited)
                            && scrambleHelper(s12, s1Start + i, s22, s2Start + i, len - i, dp, visited));
            isScramble = isScramble
                        || (scrambleHelper(s11, s1Start, s23, s2Start + len - i, len - i, dp, visited)
                            && scrambleHelper(s12, s1Start + i, s24, s2Start, len, dp, visited));
        }

        dp[s1Start][s2Start][len] = isScramble;
        visited[s1Start][s2Start][len] = true;

        return isScramble;
    }

    private boolean isValid(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);

        s1 = new String(c1);
        s2 = new String(c2);

        if (s1.equals(s2)) {
            return true;
        }

        return false;
    }
}
