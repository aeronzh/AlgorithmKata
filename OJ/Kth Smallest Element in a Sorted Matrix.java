// Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

// Note that it is the kth smallest element in the sorted order, not the kth distinct element.

// Example:

// matrix = [
//    [ 1,  5,  9],
//    [10, 11, 13],
//    [12, 13, 15]
// ],
// k = 8,

// return 13.
// Note: 
// You may assume k is always valid, 1 ≤ k ≤ n2.

// Hide Company Tags Google
// Hide Tags Binary Search Heap

// O(klogk): put the right side element into the heap only if it's on the first row
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(k, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.val - b.val;
            }
        });
        
        minHeap.offer(new Pair(0, 0, matrix[0][0]));
        while (k != 1) {
            k--;
            Pair cur = minHeap.poll();
            if (cur.x + 1 < matrix.length) {
                minHeap.offer(new Pair(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]));
            }
            if (cur.x == 0 && cur.y + 1 < matrix[0].length) {
                minHeap.offer(new Pair(cur.x, cur.y + 1, matrix[cur.x][cur.y + 1]));
            }
        }
        
        return minHeap.peek().val;
    }
}

// less efficient: always put down and right element into the heap each time
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(k, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.val - b.val;
            }
        });
        
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        minHeap.offer(new Pair(0, 0, matrix[0][0]));
        while (k != 1) {
            k--;
            Pair cur = minHeap.poll();
            if (cur.x + 1 < matrix.length && !visited[cur.x + 1][cur.y]) {
                minHeap.offer(new Pair(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]));
                visited[cur.x + 1][cur.y] = true;
            }
            if (cur.y + 1 < matrix[0].length && !visited[cur.x][cur.y + 1]) {
                minHeap.offer(new Pair(cur.x, cur.y + 1, matrix[cur.x][cur.y + 1]));
                visited[cur.x][cur.y + 1] = true;
            }
        }
        
        return minHeap.peek().val;
    }
}

class Pair {
    int x;
    int y;
    int val;
    public Pair(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
}