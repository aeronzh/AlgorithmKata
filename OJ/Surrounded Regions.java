// Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

// A region is captured by flipping all 'O''s into 'X''s in that surrounded region.

// Have you met this question in a real interview? Yes
// Example
// X X X X
// X O O X
// X X O X
// X O X X
// After capture all regions surrounded by 'X', the board should be:

// X X X X
// X X X X
// X X X X
// X O X X
// Tags 
// Breadth First Search Union Find

// Union Find
public class Solution {
    /**
     * @param board a 2D board containing 'X' and 'O'
     * @return void
     */
    public void surroundedRegions(char[][] board) {
        // Write your code here
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        int m = board.length;
        int n = board[0].length;
        int dp = m * n; // dummy parent for '0's on the edge
        
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        UnionFind uf = new UnionFind(m * n);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    continue;
                }
                int cur = convert(i, j, n);
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    uf.union(cur, dp);
                    continue;
                }
                
                for (int k = 0; k < 4; k++) {
                    int newX = i + dx[k];
                    int newY = j + dy[k];
                    
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && board[newX][newY] == 'O') {
                        uf.union(cur, convert(newX, newY, n));
                    }
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && uf.find(convert(i, j, n)) != dp) {
                     board[i][j] = 'X';
                }
            }
        }
    }
    
    private int convert(int row, int col, int n) {
        return row * n + col;
    }
}

class UnionFind {
    HashMap<Integer, Integer> father;
    int root;
    
    public UnionFind(int n) {
        father = new HashMap<Integer, Integer>();
        for (int i = 0; i <= n; i++) {
            father.put(i, i);
        }
        root = n;
    }
    
    public int find(int x) {
        while (x != father.get(x)) {
            x = father.get(x);
        }
        
        return x;
    }
    
    public int compressed_find(int x){
        int ancestor = x;
        while (ancestor != father.get(ancestor)) {
            ancestor = father.get(ancestor);
        }
        
        int parent = father.get(x);
        while (parent != father.get(parent)) {
            father.put(parent, ancestor);
            parent = father.get(parent);
        }
        
        return ancestor;
    }
    
    public void union(int x, int y) {
        int xFather = compressed_find(x);
        int yFather = compressed_find(y);
        // prevent dummy parent getting ranked down
        if (xFather == root) {
            father.put(yFather, xFather);
        } else {
            father.put(xFather, yFather);
        }
    }
}

// BFS
public class Solution {
    /**
     * @param board a 2D board containing 'X' and 'O'
     * @return void
     */
    public void surroundedRegions(char[][] board) {
        // Write your code here
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        int m = board.length;
        int n = board[0].length;
        
        for (int i = 0; i < m; i++) {
            bfs(board, i, 0);
            bfs(board, i, n - 1);
        }
        
        for (int i = 0; i < n; i++) {
            bfs(board, 0, i);
            bfs(board, m - 1, i);
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'E') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void bfs(char[][] board, int x, int y) {
        if (board[x][y] != 'O') {
            return;
        }
        
        int m = board.length;
        int n = board[0].length;
        
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        Queue<Integer> queue = new LinkedList<Integer>();
        int root = convert(x, y, n);
        queue.offer(root);
        board[x][y] = 'E';
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int curX = cur / n;
            int curY = cur % n;
            
            for (int i = 0; i < 4; i++) {
                int newX = curX + dx[i];
                int newY = curY + dy[i];
                
                
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && board[newX][newY] == 'O') {
                    board[newX][newY] = 'E';
                    queue.offer(convert(newX, newY, n));
                }
            }
        }
    }
    
    private int convert(int row, int col, int n) {
        return row * n + col;
    }
}
