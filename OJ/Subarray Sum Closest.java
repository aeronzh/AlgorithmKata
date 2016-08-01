// Given an integer array, find a subarray with sum closest to zero. Return the indexes of the first number and last number.

// Have you met this question in a real interview? Yes
// Example
// Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4].

// Challenge 
// O(nlogn) time

public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        // write your code here
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        Pair[] pairs = new Pair[nums.length + 1];
        pairs[0] = new Pair(-1, 0);
        for (int i = 1; i < pairs.length; i++) {
            pairs[i] = new Pair(i - 1, pairs[i - 1].val + nums[i - 1]);
        }
        
        Arrays.sort(pairs, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.val - b.val;
            }
        });
        
        int closest = Integer.MAX_VALUE;
        for (int i = 1; i < pairs.length; i++) {
            int diff = Math.abs(pairs[i].val - pairs[i - 1].val);
            if (diff < closest) {
                closest = diff;
                result[0] = Math.min(pairs[i].pos, pairs[i - 1].pos) + 1;
                result[1] = Math.max(pairs[i].pos, pairs[i - 1].pos);
            }
        }
        
        return result;
    }
}

class Pair {
    int pos;
    int val;
    Pair(int pos, int val) {
        this.pos = pos;
        this.val = val;
    }
}

// TreeMap version
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        // write your code here
        // treemap
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        map.put(0, -1);
        int prefixSum = 0;
        int minDiff = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (map.containsKey(prefixSum)) {
                result[0] = map.get(prefixSum) + 1;
                result[1] = i;
                break;
            }
            if (map.ceilingEntry(prefixSum) != null) {
                Map.Entry<Integer, Integer> entry = map.ceilingEntry(prefixSum);
                int diff = Math.abs(entry.getKey() - prefixSum);
                if (diff < minDiff) {
                    minDiff = diff;
                    result[0] = entry.getValue() + 1;
                    result[1] = i;
                }
            }
            if (map.floorEntry(prefixSum) != null) {
                Map.Entry<Integer, Integer> entry = map.floorEntry(prefixSum);
                int diff = Math.abs(entry.getKey() - prefixSum);
                if (diff < minDiff) {
                    minDiff = diff;
                    result[0] = entry.getValue() + 1;
                    result[1] = i;
                }
            }
            map.put(prefixSum, i);
        }
        
        return result;
    }
}
