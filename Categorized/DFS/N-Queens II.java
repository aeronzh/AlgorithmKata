class Solution {
    /**
     * Calculate the total number of distinct N-Queen solutions.
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
    public int totalNQueens(int n) {
        //write your code here
        if (n == 0) {
            return 0;
        }

        return helper(n, new ArrayList<Integer>());
    }

    private int helper(int n, ArrayList<Integer> list) {
        if (list.size() == n) {
            return 1;
        }

        int solutions = 0;

        for (int i = 0; i < n; i++) {
            if (isValid(i, list)) {
                list.add(i);
                solutions += helper(n, list);
                list.remove(list.size() - 1);
            }
        }

        return solutions;
    }

    private boolean isValid(int col, ArrayList<Integer> list) {
        int row = list.size();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == col) {
                return false;
            }

            // diagonal: upper left -> lower right
            if (i - list.get(i) == row - col) {
                return false;
            }

            // diagonal: lower left -> upper right
            if (i + list.get(i) == row + col) {
                return false;
            }
        }

        return true;
    }
}
