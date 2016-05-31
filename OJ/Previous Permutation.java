public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers that's previous permuation
     */
    public ArrayList<Integer> previousPermuation(ArrayList<Integer> nums) {
		// write your code
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (nums == null || nums.size() == 0) {
		    return result;
		}

		int index = -1;
		for (int i = nums.size() - 2; i >= 0; i--) {
		    if (nums.get(i) > nums.get(i + 1)) {
		        index = i;
		        break;
		    }
		}

		if (index == -1) {
		    reverse(nums, 0, nums.size() - 1);
		    return nums;
		}

		int firstLarger = index;
		for (int i = index + 1; i < nums.size(); i++) {
		    if (nums.get(i) < nums.get(index) && nums.get(i) >= nums.get(index + 1)) {
		        firstLarger = i;
		    }
		}

        int temp = nums.get(index);
		nums.set(index, nums.get(nums.size() - 1));
		nums.set(nums.size() - 1, temp);

		reverse(nums, index + 1, nums.size() - 1);

		return nums;
    }

    private void reverse(ArrayList<Integer> nums, int start, int end) {
        while (start < end) {
            int temp = nums.get(start);
            nums.set(start, nums.get(end));
            nums.set(end, temp);
            start++;
            end--;
        }
    }
}
