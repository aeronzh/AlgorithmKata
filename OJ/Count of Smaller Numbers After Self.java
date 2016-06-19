// You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

// Example:

// Given nums = [5, 2, 6, 1]

// To the right of 5 there are 2 smaller elements (2 and 1).
// To the right of 2 there is only 1 smaller element (1).
// To the right of 6 there is 1 smaller element (1).
// To the right of 1 there is 0 smaller element.
// Return the array [2, 1, 1, 0].

// Hide Company Tags Google
// Hide Tags Divide and Conquer Binary Indexed Tree Segment Tree Binary Search Tree
// Hide Similar Problems (H) Count of Range Sum

public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        BinarySearchTree bst = new BinarySearchTree();
        for (int i = nums.length - 1; i >= 0; i--) {
            result.add(0, bst.insert(nums[i]));
        }
        
        return result;
    }
}

class TreeNode {
    int val;
    int count;
    int leftCount;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
        count = 1;
        leftCount = 0;
        left = right = null;
    }
}

class BinarySearchTree {
    TreeNode root;
    
    public BinarySearchTree() {
        root = null;
    }
    
    public int insert(int val) {
        if (root == null) {
            root = new TreeNode(val);
            return 0;
        }
        
        TreeNode cur = root;
        int smaller = 0;
        while (true) {
            if (val < cur.val) {
                cur.leftCount++;
                if (cur.left != null) {
                    cur = cur.left;
                } else {
                    cur.left = new TreeNode(val);
                    break;
                }
            } else if (val > cur.val) {
                smaller += cur.count + cur.leftCount;
                if (cur.right != null) {
                    cur = cur.right;
                } else {
                    cur.right = new TreeNode(val);
                    break;
                }
            } else {
                smaller += cur.leftCount;
                cur.count++;
                break;
            }
        }
        
        return smaller;
    }
}