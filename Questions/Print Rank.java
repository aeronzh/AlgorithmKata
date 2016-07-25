// [10 3 8 9 4] => [1 5 3 2 4] 10 is the 1st largest etc.
// Duplicates are possible!

class Solution {
    public int[] rank(int[] nums) {
        int[] copy = new int[nums.length];
        Arrays.sort(copy, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        HashMap<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for (int n : copy) {
            if (!map.containsKey(n)) {
                map.put(n, rank++);
            }
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = map.get(nums[i]);
        }

        return result;
    }
}