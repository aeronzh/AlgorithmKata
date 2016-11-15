// Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
//
// Note:
// The number of people is less than 1,100.
//
// Example
//
// Input:
// [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
//
// Output:
// [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
// Subscribe to see which companies asked this question
//
// Hide Tags Greedy
// Hide Similar Problems (H) Count of Smaller Numbers After Self

public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        int[][] result = new int[people.length][people[0].length];
        if (people == null || people.length == 0 || people[0].length == 0) {
            return result;
        }

        // sort by height
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
            }
        });

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            list.add(i);
        }

        for (int[] p : people) {
            int idx = list.remove(p[1]);
            result[idx][0] = p[0];
            result[idx][1] = p[1];
        }

        return result;
    }
}
