// Find the number Weak Connected Component in the directed graph. Each node in the graph contains a label and a list of its neighbors. (a connected set of a directed graph is a subgraph in which any two vertices are connected by direct edge path.)

// Have you met this question in a real interview? Yes
// Example
// Given graph:

// A----->B  C
 // \     |  | 
  // \    |  |
   // \   |  |
    // \  v  v
     // ->D  E <- F
// Return {A,B,D}, {C,E,F}. Since there are two connected component which are {A,B,D} and {C,E,F}

// Note
// Sort the element in the set in increasing order

/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */
public class Solution {
    class UnionFind {
        HashMap<Integer, Integer> father = new HashMap<Integer,Integer>();
        
        UnionFind(HashSet<Integer> set) {
            for (Integer i : set) {
                father.put(i, i);
            }
        }
        
        int find(int x) {
            int parent = father.get(x);
            while (parent != father.get(parent)) {
                parent = father.get(parent);
            }
            return parent;
        }
        
        void union(int x, int y) {
            int x_father = find(x);
            int y_father = find(y);
            if (x_father != y_father) {
                father.put(x_father, y_father);   
            }
        }
    }

    /**
     * @param nodes a array of Directed graph node
     * @return a connected set of a directed graph
     */
    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        // Write your code here
        // Add all the nodes to a set
        HashSet<Integer> set = new HashSet<Integer>();
        for (DirectedGraphNode node : nodes) {
            set.add(node.label);
            for (DirectedGraphNode nb : node.neighbors) {
                set.add(nb.label);
            }
        }
        
        UnionFind uf = new UnionFind(set); // Union all the nodes to itself
        for (DirectedGraphNode node : nodes) {
            for (DirectedGraphNode nb : node.neighbors) {
                if (uf.find(node.label) != uf.find(nb.label)) {
                    uf.union(node.label, nb.label);
                }
            }
        }
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int i : set) {
            int father = uf.find(i);
            if (!map.containsKey(father)) {
                map.put(father, new ArrayList<Integer>());
            }
            List<Integer> list = map.get(father);
            list.add(i);
            map.put(father, list);
        }
        
        for (List<Integer> list : map.values()) {
            Collections.sort(list);
            result.add(list);
        }
        
        return result;
    }
}
