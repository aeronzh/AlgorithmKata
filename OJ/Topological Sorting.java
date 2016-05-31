/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        if (graph == null) return null;

        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        HashMap<DirectedGraphNode, Integer> map = new HashMap<DirectedGraphNode, Integer>();

        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, 1);
                } else {
                    map.put(neighbor, map.get(neighbor) + 1);
                }
            }

        }

        Queue<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) {
                result.add(node);
                queue.offer(node);
            }
        }
        
        // if (queue.isEmpty()) {
        //     cycle!
        // }
        
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                DirectedGraphNode node = queue.poll();

                for (DirectedGraphNode neighbor : node.neighbors) {
                    map.put(neighbor, map.get(neighbor) - 1);
                    if (map.get(neighbor) == 0) {
                        queue.offer(neighbor);
                        result.add(neighbor);
                    }
                }
            }
        }

        return result;
    }
}

// DFS
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */    
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        
        if (graph == null || graph.size() == 0) {
            return result;
        }
        
        HashMap<DirectedGraphNode, Integer> status = new HashMap<DirectedGraphNode, Integer>();
        // status: 0 => unvisited, 1 => visiting, 2 => visited
        
        for (DirectedGraphNode n : graph) {
            status.put(n, 0);
        }
        
        for (DirectedGraphNode n : graph) {
            if (status.get(n) == 0) {
                dfs(status, result, n);
            }
        }
        
        Collections.reverse(result);
        return result;
    }
    
    private void dfs(HashMap<DirectedGraphNode, Integer> status, List<DirectedGraphNode> result, DirectedGraphNode node) {
        
        status.put(node, 1);
        
        for (DirectedGraphNode nb : node.neighbors) {
            // detect cycle
            // if (status.get(nb) == 1) cycle!
            if (status.get(nb) == 0) {
                dfs(status, result, nb);
            }
        }
        
        status.put(node, 2);
        result.add(node);
    }
}

// iterative DFS
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */    
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        
        if (graph == null || graph.size() == 0) {
            return result;
        }
        
        HashMap<DirectedGraphNode, Integer> status = new HashMap<DirectedGraphNode, Integer>();
        
        for (int i = 0; i < graph.size(); i++) {
            status.put(graph.get(i), 0);
        }
        
        for (DirectedGraphNode n : graph) {
            if (status.get(n) == 0) {
                dfs(status, result, n);
            }
        }
        
        Collections.reverse(result);
        
        return result;
    }
    
    private void dfs(HashMap<DirectedGraphNode, Integer> status, ArrayList<DirectedGraphNode> result, DirectedGraphNode node) {
        
        Stack<DirectedGraphNode> stack = new Stack<DirectedGraphNode>();
        stack.push(node);
        status.put(node, 1);
        
        while (!stack.isEmpty()) {
            DirectedGraphNode u = stack.peek();
            DirectedGraphNode v = null;
            
            for (DirectedGraphNode nb : u.neighbors) {
                if (status.get(nb) == 0) {
                    v = nb;
                    break;
                }
            }
            
            if (v == null) {
                result.add(stack.pop());
                status.put(u, 2);
            } else {
                stack.push(v);
                status.put(v, 1);
            }
        }
    }
}
