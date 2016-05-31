class Sort {

    public void sort(int[] nums) {
        quicksort(nums, 0, nums.length - 1);
    }

    private void quicksort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int pos = partition(nums, left, right);

        quicksort(nums, left, pos - 1);
        quicksort(nums, pos + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];

        while (left < right) {
            while (right > left && nums[right] >= pivot) {
                right--;
            }
            nums[left] = nums[right];

            while (left < right && nums[left] <= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }

        nums[left] = pivot;
        return left;
    }
}
