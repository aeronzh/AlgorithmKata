// Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
//
// For example:
//
// Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
//
// Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
//
// Hint:
//
// Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
// According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
// Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
//
// Hide Company Tags Google Facebook Zenefits
// Hide Tags Depth-first Search Breadth-first Search Graph Union Find
// Hide Similar Problems (M) Course Schedule (M) Number of Connected Components in an Undirected Graph

// Union Findpublic class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges == null || edges.length != n - 1) {
            return false;
        }

        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (uf.find(edge[0]) == uf.find(edge[1])) {
                return false;
            }
            uf.union(edge[0], edge[1]);
        }

        return true;
    }
}

class UnionFind {
    HashMap<Integer, Integer> father;

    public UnionFind(int n) {
        father = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            father.put(i, i);
        }
    }

    public int find(int x) {
        while (x != father.get(x)) {
            x = father.get(x);
        }
        return x;
    }

    public void union(int x, int y) {
        father.put(find(x), find(y));
    }
}

public class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges == null || edges.length != n - 1) {
            return false;
        }

        List<HashSet<Integer>> graph = new ArrayList<HashSet<Integer>>();
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<Integer>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];

        isValid(graph, 0, -1, visited); // a tree only needs one node to traverse all of its nodes

        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }

        return true;
    }

    // DFS
    private void isValid(List<HashSet<Integer>> graph, int cur, int parent, boolean[] visited) {
        visited[cur] = true;

        for (int nb : graph.get(cur)) {
            if (nb == parent) {
                continue;
            }
            if (visited[nb]) {
                return;
            }

            isValid(graph, nb, cur, visited);
        }
    }

    // BFS
    private void isValid(List<HashSet<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(0);
        visited[0] = true;
        HashMap<Integer, Integer> pred = new HashMap<>();
        pred.put(0, -1);

        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            for (int nb : graph.get(cur)) {
                if (nb == pred.get(cur)) {
                    continue;
                }
                if (visited[nb]) {
                    return;
                }
                visited[nb] = true;
                pred.put(nb, cur);
                queue.offer(nb);
            }
        }
    }
}
