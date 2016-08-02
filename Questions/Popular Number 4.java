// Given an array, find an element that appears more than 1/4(nums.length) times

public class PopularNumber4 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 2, 3, 4, 3, 3, 4, 5, 3, 2, 2, 2, 2, 2, 2, 3, 4, 3, 2, 2};
        popularNumber(nums);
    }

    private static void popularNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if (binarySearchLast(nums[n / 4], nums) - binarySearchFirst(nums[n / 4], nums) >= n / 4) {
            System.out.println("Popular number is: " + nums[n / 4]);
        } else if (binarySearchLast(nums[n / 2], nums) - binarySearchFirst(nums[n / 2], nums) >= n / 4) {
            System.out.println("Popular number is: " + nums[n / 2]);
        } else if (binarySearchLast(nums[3 * n / 4], nums) - binarySearchFirst(nums[3 * n / 4], nums) >= n) {
            System.out.println("Popular number is: " + nums[3 * n / 4]);
        } else {
            System.out.println("Doesn't exist");
        }
    }

    private static int binarySearchLast(int target, int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[end] == target) {
            return end;
        } else {
            return start;
        }
    }

    private static int binarySearchFirst(int target, int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[end] == target) {
            return end;
        } else {
            return start;
        }
    }
}