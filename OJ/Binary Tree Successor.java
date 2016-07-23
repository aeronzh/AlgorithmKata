// cc150 http://www.nowcoder.com/practice/60231d6931d543d4aadcb67851b21e4a?tpId=8&tqId=11016&rp=2&ru=/ta/cracking-the-coding-interview&qru=/ta/cracking-the-coding-interview/question-ranking

import java.util.*;

/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }
}*/
public class Successor {
    
    boolean found = false;
    int successor = -1;
    
    public int findSucc(TreeNode root, int p) {
        // write code here
        if (root == null) {
			return successor;
        }
        
        findSucc(root.left, p);
        if (found) {
			successor = root.val;
            found = false;
        }
        if (root.val == p) {
			found = true;
        }
        findSucc(root.right, p);
        
        return successor;
    }
}

// v2
public class Successor {
    boolean found = false;
    public int findSucc(TreeNode root, int p) {
        // write code here
        if (root == null) {
			return -1;
        }
        
        int left = findSucc(root.left, p);
        if (left != -1) {
			return left;
        }
        if (found) {
			return root.val;
        }
        if (root.val == p) {
			found = true;
        }
        int right = findSucc(root.right, p);
        
        return right;
    }
}

// D&V needs ResultType
// otherwise the following case will not work
//   1
//  /
// 2
// p = 2