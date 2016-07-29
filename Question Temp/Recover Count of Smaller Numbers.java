// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=144709&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26searchoption%5B3091%5D%5Bvalue%5D%3D1%26searchoption%5B3091%5D%5Btype%5D%3Dradio%26sortid%3D311
// Solution: traverse input order array backwards - each element means the nth largest in the rest of [1, n]

public class RecoverCountOfSmallerNumbers {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 1, 0};
        int[] result = recover(5, nums);
        for (int n : result) {
            System.out.println(n); // [4, 2, 1, 3, 5]
        }
    }

    public static int[] recover(int n, int[] nums) {
        List<Integer> list = new ArrayList<>();
        int[] result = new int[n];

        for (int i = n; i > 0; i--) {
            list.add(i);
        }

        for (int i = n - 1; i >= 0; i--) {
            result[i] = list.remove(nums[i]);
        }

        return result;
    }
}