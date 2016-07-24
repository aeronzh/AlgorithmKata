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
