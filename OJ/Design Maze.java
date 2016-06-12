// Perfect maze: http://algs4.cs.princeton.edu/41graph/
// A perfect maze has exactly one path between every pair of nodes. There is no inaccessible locations, cycles, and open spaces.

class Maze {
    boolean[][] north;
    boolean[][] south;
    boolean[][] west;
    boolean[][] east;
    boolean[][] visited;
    int size;
    boolean done;
    
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    public Maze(int size) {
        this.size = size;
        init();
        generate(1, 1);
    }

    private void init() {
        // init border cells as visited
        visited = new boolean[size + 2][size + 2];
        for (int i = 0; i < size + 2; i++) {
            visited[i][0] = true;
            visited[i][size + 1] = true;

            visited[0][i] = true;
            visited[size + 1][i] = true;
        }
        
        // init all walls as present
        north = new boolean[size + 2][size + 2];
        south = new boolean[size + 2][size + 2];
        west = new boolean[size + 2][size + 2];
        east = new boolean[size + 2][size + 2];
        for (int i = 0; i < size + 2; i++) {
            for (int j = 0; j < size + 2; j++) {
                north[i][j] = true;
                south[i][j] = true;
                west[i][j] = true;
                east[i][j] = true;
            }
        }
    }

    private void generate(int x, int y) {
        visited[x][y] = true;

        while (!visited[x - 1][y] || !visited[x + 1][y] || !visited[x][y - 1] || !visited[x][y + 1]) {
            while (true) {
                int r = Math.random() * 4;

                if (r == 0 && !visited[x - 1][y]) {
                    // up
                    north[x][y] = false;
                    south[x - 1][y] = false;
                    generate(x - 1, y);
                    break;
                } else if (r == 1 && !visited[x + 1][y]) {
                    // down
                    south[x][y] = false;
                    north[x + 1][y] = false;
                    generate(x + 1, y);
                    break;
                } else if (r == 2 && !visited[x][y - 1]) {
                    // left
                    west[x][y] = false;
                    east[x][y - 1] = false;
                    generate(x, y - 1);
                    break;
                } else if (r == 3 && !visited[x][y + 1]){
                    // right
                    east[x][y] = false;
                    west[x][y + 1] = false;
                    generate(x, y + 1);
                    break;
                }
            }
        }
    }

    public void solve() {
        // reset
        for (int i = 0; i <= size; i++) {
            for (int j = 1; i <= size; j++) {
                visited[i][j] = false;
            }
        }
        done = false;

        solve(1, 1);
    }

    private void solve(int x, int y) {
        if (x == 0 || x == size + 1 || y == 0 || y == size + 1) {
            return;
        }

        if (done || visited[x][y]) {
            return;
        }

        if (x == size / 2 + 1 && y == size / 2 + 1) {
            done = true;
            return;
        }

        visited[x][y] = true;

        if (!north[x][y]) solve(x - 1, y);
        if (!south[x][y]) solve(x + 1, y);
        if (!west[x][y]) solve(x, y - 1);
        if (!east[x][y]) solve(x, y + 1);
    }
}