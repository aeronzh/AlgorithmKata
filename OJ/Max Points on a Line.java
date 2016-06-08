// Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
//
// Have you met this question in a real interview? Yes
// Example
// Given 4 points: (1,2), (3,6), (0,0), (1,3).
//
// The maximum number is 3.

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    /**
     * @param points an array of point
     * @return an integer
     */
    public int maxPoints(Point[] points) {
        // Write your code here
        if (points == null || points.length == 0) {
            return 0;
        }

        HashMap<Double, Integer> map = new HashMap<Double, Integer>();
        int max = 1;

        for (int i = 0; i < points.length; i++) {
            map.clear();
            int duplicates = 1;

            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    duplicates++;
                    continue;
                }

                double tangent = points[i].x == points[j].x ? Integer.MAX_VALUE :
                                       (double)(points[i].y - points[j].y) / (points[i].x - points[j].x);

                if (map.containsKey(tangent)) {
                    map.put(tangent, map.get(tangent) + 1);
                } else {
                    map.put(tangent, 1);
                }
            }

            for (int count : map.values()) {
                max = Math.max(max, count + duplicates);
            }

            max = Math.max(max, duplicates);
        }

        return max;
    }
}
