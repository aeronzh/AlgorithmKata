// bit shifting
public class Solution {
    /**
     * @param num: an integer
     * @return: an integer, the number of ones in num
     */
    public int countOnes(int num) {
        // write your code here
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += ((num >> i) & 1) == 1 ? 1 : 0;
        }
        return count;
    }
}

// bit masking
public class Solution {
    /**
     * @param num: an integer
     * @return: an integer, the number of ones in num
     */
    public int countOnes(int num) {
        // write your code here
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += (num & (1 << i)) != 0 ? 1 : 0;
        }
        return count;
    }
}