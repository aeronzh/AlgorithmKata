// Given an array of n integer, and a moving window(size k), move the window at each iteration from the start of the array, find the median of the element inside the window at each moving. (If there are even numbers in the array, return the N/2-th number after sorting the element in the window. )
//
// Example
// For array [1,2,7,8,5], moving window size k = 3. return [2,7,7]
//
// At first the window is at the start of the array like this
//
// [ | 1,2,7 | ,8,5] , return the median 2;
//
// then the window move one step forward.
//
// [1, | 2,7,8 | ,5], return the median 7;
//
// then the window move one step forward again.
//
// [1,2, | 7,8,5 | ], return the median 7;
//
// Challenge
// O(nlog(n)) time
// -----------------------------------------------------------------------------
// HashHeap: remove - O(1). Java does not have it implemented
// In this case PriorityQueue is good enough.But need to understand how to implement HashHeap
// Overall time complexity: O(nlogk) - k is the window size
// Similar idea to the way how Data Stream Median maintains its median
// Summary for sliding window problems:
// 1. Add next element
// 2. Delete first element
// -----------------------------------------------------------------------------
public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: The median of the element inside the window at each moving.
     */
    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        // write your code
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) return result;

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(1, Collections.reverseOrder());
        // PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
        //     @Override
        //     public int compare(Integer a, Integer b>() {
        //         return b - a;
        //     }
        // });

        int median = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (i != 0) { // for the case when window size is 1
                if (nums[i] < median) {
                    maxHeap.add(nums[i]);
                } else {
                    minHeap.add(nums[i]);
                }
            }

            if (i >= k) {
                if (nums[i - k] == median) {
                    if (maxHeap.size() > minHeap.size()) {
                        median = maxHeap.poll();
                    } else {
                        median = minHeap.poll();
                    }
                } else if (nums[i - k] < median) {
                    maxHeap.remove(nums[i - k]);
                } else {
                    minHeap.remove(nums[i - k]);
                }
            }

            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(median);
                median = maxHeap.poll();
            } else if (maxHeap.size() + 1 < minHeap.size()) {
                maxHeap.add(median);
                median = minHeap.poll();
            }
            if (i + 1 >= k) {
                result.add(median);
            }
        }
        return result;
    }
}
// -----------------------------------------------------------------------------
// HashHeap implementation
// -----------------------------------------------------------------------------
class HashHeap {
    ArrayList<Integer> heap;
    HashMap<Integer, Node> hash;
    String mode;
    int size;

    class Node {
        int id;
        int count;

        public Node(Node node) {
            id = node.id;
            count = node.count;
        }

        public Node(int id, int count) {
            this.id = id;
            this.count = count;
        }
    }

    public HashHeap(String mode) {
        this.mode = mode;
        heap = new ArrayList<Integer>();
        hash = new HashMap<Integer, Node>();
        size = 0;
    }

    int size() {
        return size;
    }

    int peek() {
        return heap.get(0);
    }

    boolean isEmpty() {
        return heap.size() == 0;
    }

    int parent(int id) {
        if (id == 0) {
            return -1;
        }
        return (id - 1) / 2;
    }

    int leftChild(int id) {
        return id * 2 + 1;
    }

    int rightChild(int id) {
        return id * 2 + 2;
    }

    boolean compare(int a, int b) {
        if (a <= b) {
            if (mode == "min") {
                return true;
            } else {
                return false;
            }
        } else {
            if (mode == "min") {
                return false;
            } else {
                return true;
            }
        }
    }

    void swap(int a_id, int b_id) {
        int a_val = heap.get(a_id);
        int b_val = heap.get(b_id);

        int a_count = hash.get(a_val).count;
        int b_count = hash.get(b_val).count;

        hash.put(b_val, new Node(a_id, b_count));
        hash.put(a_val, new Node(b_id, a_count));

        heap.set(a_id, b_val);
        heap.set(b_id, a_val);
    }

    int poll() {
        size--;
        int val = heap.get(0);
        Node node = hash.get(val);
        if (node.count == 1) {
            swap(0, heap.size() - 1);
            hash.remove(val);
            heap.remove(heap.size() - 1);
            if (heap.size() > 0) {
                siftdown(0);
            }
        } else {
            hash.put(val, new Node(0, node.count - 1));
        }
        return val;
    }

    void add(int val) {
        size++;
        if (hash.containsKey(val)) {
            Node node = hash.get(val);
            hash.put(val, new Node(node.id, node.count + 1));
        } else {
            heap.add(val);
            hash.put(val, new Node(heap.size() - 1, 1));
        }
        siftup(heap.size() - 1);
    }

    void delete(int val) {
        size--;
        Node node = hash.get(val);
        int id = node.id;
        int count = node.count;
        if (count == 1) {
            swap(id, heap.size() - 1);
            hash.remove(val);
            heap.remove(heap.size() - 1);
            if (heap.size() > id) {
                siftup(id);
                siftdown(id);
            }
        } else {
            hash.put(val, new Node(id, count - 1));
        }

    }

    void siftup(int id) {
        while (parent(id) > -1) {
            int parent_id = parent(id);
            if (compare(heap.get(parent_id), heap.get(id))) {
                break;
            } else {
                swap(id, parent_id);
            }
            id = parent_id;
        }
    }

    void siftdown(int id) {
        while (leftChild(id) < heap.size()) {
            int left_id = leftChild(id);
            int right_id = rightChild(id);
            int swap_id;
            if (right_id >= heap.size() || compare(heap.get(left_id), heap.get(right_id))) {
                swap_id = left_id;
            } else {
                swap_id =right_id;
            }
            if (compare(heap.get(id), heap.get(swap_id))) {
                break;
            } else {
                swap(id, swap_id);
            }
            id = swap_id;
        }
    }
}
public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: The median of the element inside the window at each moving.
     */
    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        // write your code
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) return result;

        HashHeap minHeap = new HashHeap("min");
        HashHeap maxHeap = new HashHeap("max");

        int median = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                if (nums[i] < median) {
                    maxHeap.add(nums[i]);
                } else {
                    minHeap.add(nums[i]);
                }
            }

            if (i >= k) {
                if (nums[i - k] == median) {
                    if (maxHeap.size() > minHeap.size()) {
                        median = maxHeap.poll();
                    } else {
                        median = minHeap.poll();
                    }
                } else if (nums[i - k] < median) {
                    maxHeap.delete(nums[i - k]);
                } else {
                    minHeap.delete(nums[i - k]);
                }
            }

            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(median);
                median = maxHeap.poll();
            } else if (maxHeap.size() + 1 < minHeap.size()) {
                maxHeap.add(median);
                median = minHeap.poll();
            }
            if (i + 1 >= k) {
                result.add(median);
            }
        }
        return result;
    }
}
