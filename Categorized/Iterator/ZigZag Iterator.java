// Given two 1d vectors, implement an iterator to return their elements alternately.
//
// For example, given two 1d vectors:
//
// v1 = [1, 2]
// v2 = [3, 4, 5, 6]
// By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
//
// Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
//
// Clarification for the follow up question - Update (2015-09-18):
// The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:
//
// [1,2,3]
// [4,5,6,7]
// [8,9]
// It should return [1,4,8,2,5,9,3,6,7].
// Hide Company Tags Google
// Hide Tags Design
// Hide Similar Problems (M) Binary Search Tree Iterator (M) Flatten 2D Vector (M) Peeking Iterator (M) Flatten Nested List Iterator

public class ZigzagIterator {
    Queue<Iterator<Integer>> queue;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        queue = new LinkedList<Iterator<Integer>>();

        if (!v1.isEmpty()) {
            queue.add(v1.iterator());
        }

        if (!v2.isEmpty()) {
            queue.add(v2.iterator());
        }
    }

    public int next() {
        Iterator<Integer> nextIter = queue.poll();
        int nextElem = nextIter.next();
        if (nextIter.hasNext()) {
            queue.offer(nextIter);
        }
        return nextElem;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

// two iterator version
public class ZigzagIterator {
    /**
     * @param v1 v2 two 1d vectors
     */

    boolean flag;
    Iterator<Integer> iter1;
    Iterator<Integer> iter2;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        // initialize your data structure here.
        flag = true;
        iter1 = v1.iterator();
        iter2 = v2.iterator();
    }

    public int next() {
        // Write your code here
        if (flag) {
            flag = false;
            return iter1.hasNext() ? iter1.next() : iter2.next();
        } else {
            flag = true;
            return iter2.hasNext() ? iter2.next() : iter1.next();
        }
    }

    public boolean hasNext() {
        // Write your code here
        return iter1.hasNext() || iter2.hasNext();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator solution = new ZigzagIterator(v1, v2);
 * while (solution.hasNext()) result.add(solution.next());
 * Output result
 */
