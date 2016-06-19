// Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
//
// For example,
// Given [100, 4, 200, 1, 3, 2],
// The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
//
// Your algorithm should run in O(n) complexity.
//
// Hide Company Tags Google Facebook
// Hide Tags Array Union Find
// Hide Similar Problems (M) Binary Tree Longest Consecutive Sequence

public class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        HashSet<Integer> set = new HashSet<Integer>();
        for (int n : nums) {
            set.add(n);
        }

        int max = 0;
        for (int n : nums) {
            if (!set.contains(n - 1)) {
                int m = n + 1;
                while (set.contains(m)) {
                    m++;
                }
                max = Math.max(max, m - n);
            }
        }

        return max;
    }
}

// TLE Union Find
public class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        UnionFind uf = new UnionFind(nums);
        for (int n : nums) {
            uf.union(n, n + 1);
            uf.union(n, n - 1);
        }

        // remove duplicates
        HashSet<Integer> set = new HashSet<Integer>();
        for (int n : nums) {
            set.add(n);
        }

        // find father that belongs to most numbers
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = 0;
        for (int n : set) {
            int father = uf.find(n);
            if (map.containsKey(father)) {
                map.put(father, map.get(father) + 1);
            } else {
                map.put(father, 1);
            }
            max = Math.max(max, map.get(father));
        }

        return max;
    }
}

class UnionFind {
    HashMap<Integer, Integer> father;

    public UnionFind(int[] nums) {
        father = new HashMap<Integer, Integer>();
        for (int n : nums) {
            father.put(n, n);
        }
    }

    public int find(int x) {
        while (x != father.get(x)) {
            x = father.get(x);
        }
        return x;
    }

    public int compressed_find(int x) {
        int ancestor = x;
        while (ancestor != father.get(ancestor)) {
            ancestor = father.get(ancestor);
        }

        while (x != ancestor) {
            int temp = father.get(x);
            father.put(x, ancestor);
            x = temp;
        }

        return ancestor;
    }

    public void union(int x, int y) {
        if (!father.containsKey(x) || !father.containsKey(y)) {
            return;
        }

        father.put(compressed_find(x), compressed_find(y));
    }
}
