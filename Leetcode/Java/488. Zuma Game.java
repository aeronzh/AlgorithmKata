// Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several balls in your hand.
//
// Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.
//
// Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.
//
// Examples:
//
// Input: "WRRBBW", "RB"
// Output: -1
// Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW
//
// Input: "WWRRBBWW", "WRBRW"
// Output: 2
// Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty
//
// Input:"G", "GGGGG"
// Output: 2
// Explanation: G -> G[G] -> GG[G] -> empty
//
// Input: "RBYYBBRRB", "YRBGB"
// Output: 3
// Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty
//
// Note:
// You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
// The number of balls on the table won't exceed 20, and the string represents these balls is called "board" in the input.
// The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
// Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.
// Hide Company Tags Baidu
// Hide Tags Depth-first Search

public class Solution {
    public int findMinStep(String board, String hand) {
        int result = helper(board, hand, new HashMap<String, Integer>());
        return result;
    }

    private int helper(String board, String hand, Map<String, Integer> map) {
        if (board.length() == 0) {
            return 0;
        }

        if (hand.length() == 0) {
            return -1;
        }

        if (map.containsKey(board + "#" + hand)) {
            return map.get(board + "#" + hand);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < hand.length(); i++) {
            char ball = hand.charAt(i);

            for (int j = 0; j < board.length(); j++) {
                if (board.charAt(j) == ball && (j == 0 || board.charAt(j - 1) != ball)) {
                    int right = j + 1;
                    while (right < board.length() && board.charAt(right) == ball) {
                        right++;
                    }

                    String newBoard = board;
                    if (right - j >= 2) {
                        newBoard = board.substring(0, j);
                    } else {
                        newBoard = board.substring(0, j + 1) + ball;
                    }
                    newBoard = cleanUp(newBoard + board.substring(right));

                    int result = helper(newBoard, hand.substring(0, i) + hand.substring(i + 1), map);
                    if (result != -1) {
                        min = Math.min(min, result + 1);
                    }
                }
            }
        }

        min = min == Integer.MAX_VALUE ? -1 : min;
        map.put(board + "#" + hand, min);

        return min;
    }

    private String cleanUp(String board) {
        int end = 0;
        for (int i = 0; i < board.length(); i++) {
            while (end < board.length() && board.charAt(i) == board.charAt(end)) {
                end++;
            }
            if (end - i >= 3) {
                return cleanUp(board.substring(0, i) + board.substring(end));
            }
        }

        return board;
    }
}
