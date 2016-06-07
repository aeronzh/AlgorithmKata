// You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
//
// What is the maximum number of envelopes can you Russian doll? (put one inside other)
//
// Example:
// Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
//
// Hide Company Tags Google
// Hide Tags Binary Search Dynamic Programming
// Hide Similar Problems (M) Longest Increasing Subsequence

public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length < 2) {
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                // if (a[0] == b[0]) {
                //     return a[1] - b[1];
                // }
                return a[0] - b[0];
            }
        });

        // max num of envelopes can first i envelop russian doll
        int[] dp = new int[envelopes.length];
        int max = 1;

        for (int i = 0; i < envelopes.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
