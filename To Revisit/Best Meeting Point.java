// A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
//
// For example, given three people living at (0,0), (0,4), and (2,2):
//
// 1 - 0 - 0 - 0 - 1
// |   |   |   |   |
// 0 - 0 - 0 - 0 - 0
// |   |   |   |   |
// 0 - 0 - 1 - 0 - 0
// The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
//
// Hint:
//
// Try to solve it in one dimension first. How can this solution apply to the two dimension case?
// Hide Company Tags Twitter
// Hide Tags Math Sort
// Hide Similar Problems (H) Shortest Distance from All Buildings

// the best meeting point is the median of all the points people live
// consider 1D example: 1->2->3<-4
// people live in 1, 2 and 4, if meet at 3, it is furthur than meeting at 2
// the median point makes sure the right points go left, and left points go right

public class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> ipos = new ArrayList<Integer>();
        List<Integer> jpos = new ArrayList<Integer>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    ipos.add(i);
                    jpos.add(j);
                }
            }
        }

        int sum = 0;
        // don't need to sort ipos because i is added by sorted order
        for (int i : ipos) {
            sum += Math.abs(i - ipos.get(ipos.size() / 2));
        }

        Collections.sort(jpos);
        for (int j : jpos) {
            sum += Math.abs(j - jpos.get(jpos.size() / 2));
        }

        return sum;
    }
}
