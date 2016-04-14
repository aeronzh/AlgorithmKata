// Find the number connected component in the undirected graph. Each node in the graph contains a label and a list of its neighbors. (a connected component (or just component) of an undirected graph is a subgraph in which any two vertices are connected to each other by paths, and which is connected to no additional vertices in the supergraph.)

// Have you met this question in a real interview? Yes
// Example
// Given graph:

// A------B  C
 // \     |  | 
  // \    |  |
   // \   |  |
    // \  |  |
      // D   E
// Return {A,B,D}, {C,E}. Since there are two connected component which is {A,B,D}, {C,E}

/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        // Write your code here
        
        Map<UndirectedGraphNode, Boolean> visited = new HashMap<UndirectedGraphNode, Boolean>();
        for (UndirectedGraphNode node : nodes) {
            visited.put(node, false);
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        for (UndirectedGraphNode node : nodes) {
            if (visited.get(node) == false) { // probably unnecessary
                bfs(node, visited, result);
            }
        }
        
        return result;
    }
    
    public void bfs(UndirectedGraphNode node, Map<UndirectedGraphNode, Boolean> visited, List<List<Integer>> result) {
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.add(node);
        visited.put(node, true);
        
        ArrayList<Integer> row = new ArrayList<Integer>();
        row.add(node.label);
        
        while (!queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();
            
            for (UndirectedGraphNode nb : cur.neighbors) {
                if (visited.get(nb) == false) {
                    row.add(nb.label);
                    visited.put(nb, true);
                    queue.add(nb);
                }
            }
        }
        Collections.sort(row);
        result.add(row);
    }
}