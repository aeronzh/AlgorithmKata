// Given an integer n, return 1 - n in lexicographical order.

// For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

// Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.

public class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (i <= n) {
                helper(result, n, i);
            }
        }
        return result;
    }
    
    private void helper(List<Integer> result, int n, int last) {
        if (last > n) {
            return;
        }

        result.add(last);

        for (int i = 0; i <= 9; i++) {
            helper(result, n, last * 10 + i);
        }
    }
}