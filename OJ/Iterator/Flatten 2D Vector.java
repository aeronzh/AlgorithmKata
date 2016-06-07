// Implement an iterator to flatten a 2d vector.
//
// For example,
// Given 2d vector =
//
// [
//   [1,2],
//   [3],
//   [4,5,6]
// ]
// By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
//
// Hint:
//
// How many variables do you need to keep track?
// Two variables is all you need. Try with x and y.
// Beware of empty rows. It could be the first few rows.
// To write correct code, think about the invariant to maintain. What is it?
// The invariant is x and y must always point to a valid point in the 2d vector. Should you maintain your invariant ahead of time or right when you need it?
// Not sure? Think about how you would implement hasNext(). Which is more complex?Show More Hint
// Follow up:
// As an added challenge, try to code it using only iterators in C++ or iterators in Java.
//
// Hide Company Tags Google Airbnb Twitter Zenefits
// Hide Tags Design
// Hide Similar Problems (M) Binary Search Tree Iterator (M) Zigzag Iterator (M) Peeking Iterator (M) Flatten Nested List Iterator

public class Vector2D implements Iterator<Integer> {
    List<List<Integer>> vec;
    int x;
    int y;

    public Vector2D(List<List<Integer>> vec2d) {
        vec = vec2d;
        x = 0;
        y = 0;
    }

    @Override
    public Integer next() {
        return vec.get(x).get(y++);
    }

    @Override
    public boolean hasNext() {
        while (x < vec.size()) {
            if (y < vec.get(x).size()) {
                return true;
            } else {
                x++;
                y = 0;
            }
        }

        return false;
    }
}

 public class Vector2D implements Iterator<Integer> {
    Iterator<List<Integer>> listIter;
    Iterator<Integer> elemIter;

    public Vector2D(List<List<Integer>> vec2d) {
        listIter = vec2d.iterator();
    }

    @Override
    public Integer next() {
        return elemIter.next();
    }

    @Override
    public boolean hasNext() {
        while (listIter.hasNext() || elemIter != null && elemIter.hasNext()) {
            if (elemIter == null) {
                 elemIter = listIter.next().iterator();
            }
            if (elemIter.hasNext()) {
                return true;
            } else if (listIter.hasNext()){
                elemIter = listIter.next().iterator();
            }
        }

        return false;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
