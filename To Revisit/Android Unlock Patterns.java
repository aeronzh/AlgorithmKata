// Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

// Rules for a valid pattern:
// Each pattern must connect at least m keys and at most n keys.
// All the keys must be distinct.
// If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
// The order of keys used matters.

public class Solution {
    public int numberOfPatterns(int m, int n) {
        // order matters -> permutation
        return helper(m, n, new ArrayList<Integer>());
    }
    
    private int helper(int m, int n, List<Integer> list) {
        int count = 0;
        
        if (list.size() > n) {
            return count;
        }
        
        if (list.size() >= m) {
            count++;
        }
        
        int result = 0;
        for (int i = 1; i <= 9; i++) {
            if (isValid(i, list)) {
                list.add(i);
                count += helper(m, n, list);
                list.remove(list.size() - 1);
            }
        }
        
        return count;
    }
    
    private boolean isValid(int cur, List<Integer> list) {
        if (list.size() == 0) {
            return true;
        }
        
        if (list.contains(cur)) {
            return false;
        }
        
        int last = list.get(list.size() - 1);
        if (cur == 1) {
            if (last == 3 && !list.contains(2) || (last == 7 && !list.contains(4)) || (last == 9 && !list.contains(5))) {
                return false;
            }
        } else if (cur == 2) {
            if (last == 8 && !list.contains(5)) {
                return false;
            }
        } else if (cur == 3) {
            if (last == 1 && !list.contains(2) || (last == 9 && !list.contains(6)) || (last == 7 && !list.contains(5))) {
                return false;
            }
        } else if (cur == 4) {
            if (last == 6 && !list.contains(5)) {
                return false;
            }
        } else if (cur == 6) {
            if (last == 4 && !list.contains(5)) {
                return false;
            }
        } else if (cur == 7) {
            if (last == 1 && !list.contains(4) || (last == 9 && !list.contains(8)) || (last == 3 && !list.contains(5))) {
                return false;
            }
        } else if (cur == 8) {
            if (last == 2 && !list.contains(5)) {
                return false;
            }
        } else if (cur == 9) {
            if (last == 3 && !list.contains(6) || (last == 7 && !list.contains(8)) || (last == 1 && !list.contains(5))) {
                return false;
            }
        }
        
        return true;
    }
}