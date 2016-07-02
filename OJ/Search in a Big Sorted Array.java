// Given a big sorted array, find the first index of a target number. Your algorithm should be in O(log k), where k is the first index of the target number.
//
// Return -1, if the number doesn't exist in the array.
//
// Example
// Given [1, 3, 6, 9, 21], and target = 3, return 1.
//
// Given [1, 3, 6, 9, 21], and target = 4, return -1.
//
// Challenge
// O(log k), k is the first index of the given target number.
// -----------------------------------------------------------------------------
// Note the pre-processing of end pointer
// A similar idea taken from ArrayList
// -----------------------------------------------------------------------------
/**
 * Definition of ArrayReader:
 *
 * class ArrayReader {
 *      // get the number at index, return -1 if not exists.
 *      public int get(int index);
 * }
 */
public class Solution {
    /**
     * @param reader: An instance of ArrayReader.
     * @param target: An integer
     * @return : An integer which is the index of the target number
     */
    public int searchBigSortedArray(ArrayReader reader, int target) {
        // write your code here
        int end = 1;
        while (reader.get(end - 1) < target && reader.get(end - 1) != -1) {
            end = end * 2;
        }

        int start = 0;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (reader.get(mid) == target) {
                end = mid;
            } else if (reader.get(mid) < target && reader.get(mid) != -1) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (reader.get(start) == target) {
            return start;
        } else if (reader.get(end) == target) {
            return end;
        }

        return -1;
    }
}
