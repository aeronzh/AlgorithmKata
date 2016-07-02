// Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

// If there are multiple solutions, return any subset is fine.

// Example 1:

// nums: [1,2,3]

// Result: [1,2] (of course, [1,3] will also be ok)
// Example 2:

// nums: [1,2,4,8]

// Result: [1,2,4,8]
// Credits:
// Special thanks to @Stomach_ache for adding this problem and creating all test cases.

// Hide Company Tags Google
// Hide Tags Dynamic Programming Math

// If a smaller element is divisible by a larger element, it is divisible by all the elements that are divisible by the larger element
public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        Arrays.sort(nums);
        List<List<Integer>> dp = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            dp.add(i, new ArrayList<Integer>());
        }
        
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] % nums[i] == 0 && dp.get(j).size() > dp.get(i).size()) {
                    dp.set(i, new ArrayList<Integer>(dp.get(j)));
                }
            }
            dp.get(i).add(nums[i]);
            if (dp.get(i).size() > result.size()) {
                result = dp.get(i);
            }
        }
        
        Collections.sort(result);
        return result;
    }
}

// Brute Force
public class Solution {
    public int[] largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        List<Integer> result = new ArrayList<Integer>();
        backtrack(result, new ArrayList<Integer>(), nums, 0);
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }
    
    private void backtrack(List<Integer> result, List<Integer> list, int[] nums, int pos) {
        if (list.size() > result.size()) {
            result.addAll(new ArrayList<Integer>(list));
        }
        
        for (int i = pos; i < nums.length; i++) {
            if (isValid(list, nums[i])) {
                list.add(nums[i]);
                backtrack(result, list, nums, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
    
    private boolean isValid(List<Integer> list, int num) {
        if (list.size() == 0) {
            return true;
        }
        
        for (int n : list) {
            if (n % num != 0 && num % n != 0) {
                return false;
            }
        }
        
        return true;
    }