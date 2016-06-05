// Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

// Example 1:
//      0          3
//      |          |
//      1 --- 2    4
// Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

// Example 2:
//      0           4
//      |           |
//      1 --- 2 --- 3
// Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

// Note:
// You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

// Hide Company Tags Google
// Hide Tags Depth-first Search Breadth-first Search Graph Union Find
// Hide Similar Problems (M) Number of Islands (M) Graph Valid Tree

public class Solution {
    public int countComponents(int n, int[][] edges) {
        if (edges == null || n == 0) {
            return 0;
        }
        
        ArrayList<HashSet<Integer>> graph = new ArrayList<HashSet<Integer>>();
        
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<Integer>());
        }
        
        for (int[] pair : edges) {
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }
        
        int count = 0;
        HashSet<Integer> visited = new HashSet<Integer>();
        
        for (int i = 0; i < graph.size(); i++) {
            if (!visited.contains(i)) {
                bfs(i, visited, graph);
                count++;
            }
        }
        
        return count;
    }
    
    private void bfs(int i, HashSet<Integer> visited, ArrayList<HashSet<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(i);
        visited.add(i);
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (int nb : graph.get(cur)) {
                if (!visited.contains(nb)) {
                    queue.offer(nb);
                    visited.add(nb);
                }
            }
        }
    }
}