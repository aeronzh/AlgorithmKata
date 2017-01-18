// Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).
//
// Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).
//
// Example:
// Input:
// [[0,0],[1,0],[2,0]]
//
// Output:
// 2
//
// Explanation:
// The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]

public class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] i : points) {
            for (int[] j : points) {
                if (i == j) {
                    continue;
                }

                int distance = getSquaredDistance(i, j);
                int num = map.getOrDefault(distance, 0);
                count += num * 2;
                map.put(distance, num + 1);
            }

            map.clear();
        }

        return count;
    }

    // public int numberOfBoomerangs(int[][] points) {
    //     int count = 0;
    //     Map<Integer, Integer> map = new HashMap<>();
    //
    //     for (int i = 0; i < points.length; i++) {
    //         for (int j = 0; j < points.length; j++) {
    //             if (i == j) {
    //                 continue;
    //             }
    //
    //             int distance = getSquaredDistance(points[i], points[j]);
    //             map.put(distance, map.getOrDefault(distance, 0) + 1);
    //         }
    // 
    //         for (int d : map.values()) {
    //             count += (d * (d - 1));
    //         }
    //
    //         map.clear();
    //     }
    //
    //     return count;
    // }

    private int getSquaredDistance(int[] a, int[] b) {
        int x = a[0] - b[0];
        int y = a[1] - b[1];
        return x * x + y * y;
    }
}
