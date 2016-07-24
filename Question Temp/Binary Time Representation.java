// http://www.1point3acres.com/bbs/thread-197560-1-1.html

// given n lights, return all possible time

class Solution {
    public List<String> watch(int n) {
        List<String> result = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            for (int j = 1; j <= 60; j++) {
                if (countOneBits(i) + (countOneBits(j)) == n) {
                    result.add("" + i + ":" + j);
                }
            }
        }

        return result;
    }

    private int countOneBits(int n) {
        
    }
}

// follow up: if need to represent year, month, seconds?
// DFS permutation