// Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

//  Notice

// Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)

// The solution set must not contain duplicate triplets.

// Have you met this question in a real interview? Yes
// Example
// For example, given array S = {-1 0 1 2 -1 -4}, A solution set is:

// (-1, 0, 1)
// (-1, -1, 2)

public class Solution {
    /**
     * @param numbers : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
    public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        
        if (numbers == null || numbers.length == 0) {
            return result;
        }
        
        Arrays.sort(numbers);
        
        for (int i = 0; i < numbers.length - 1; i++) {
            if (i != 0 && numbers[i - 1] == numbers[i]) {
                continue;
            }
            
            int target = -numbers[i];
            int left = i + 1;
            int right = numbers.length - 1;
            
            while (left < right) {
                int sum = numbers[left] + numbers[right];
                
                if (sum == target) {
                    ArrayList<Integer> triplet = new ArrayList<Integer>();
                    triplet.add(numbers[i]);
                    triplet.add(numbers[left]);
                    triplet.add(numbers[right]);
                    result.add(triplet);
                    left++;
                    right--;
                    
                    while (left < right && numbers[left - 1] == numbers[left]) {
                        left++;
                    }
                    
                    while (left < right && numbers[right + 1] == numbers[right]) {
                        right--;
                    }
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }
}