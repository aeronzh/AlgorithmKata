// Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

// Example 1:
// Given points = [[1,1],[-1,1]], return true.

// Example 2:
// Given points = [[1,1],[-1,-1]], return false.

// Follow up:
// Could you do better than O(n2)?

// Hint:

// Find the smallest and largest x-value for all points.
// If there is a line then it should be at y = (minX + maxX) / 2.
// For each point, make sure that it has a reflected point in the opposite side.
// Credits:
// Special thanks to @memoryless for adding this problem and creating all test cases.

// Hide Company Tags Google
// Hide Tags Hash Table Math
// Hide Similar Problems (H) Max Points on a Line

public class Solution {
    public boolean isReflected(int[][] points) {
        if (points == null || points.length == 0 || points[0].length != 2) {
            return true;
        }
        
        double min = Integer.MAX_VALUE;
        double max = Integer.MIN_VALUE;
        HashMap<Double, HashSet<Double>> map = new HashMap<>();
        for (int[] point : points) {
            min = Math.min(min, point[0]);
            max = Math.max(max, point[0]);
            if (!map.containsKey(point[1] * 1.0)) {
                map.put(point[1] * 1.0, new HashSet<Double>());
            }
            map.get(point[1] * 1.0).add(point[0] * 1.0);
        }
        
        double line = min + max;
        for (HashSet<Double> set : map.values()) {
            for (double n : set) {
                if (!set.contains(line - n)) {
                    return false;
                }
            }
        }
        
        return true;
    }
}