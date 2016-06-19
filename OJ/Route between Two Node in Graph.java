/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) {
 *         label = x;
 *         neighbors = new ArrayList<DirectedGraphNode>();
 *     }
 * };
 */
public class Solution {
   /**
     * @param graph: A list of Directed graph node
     * @param s: the starting Directed graph node
     * @param t: the terminal Directed graph node
     * @return: a boolean value
     */
    public boolean hasRoute(ArrayList<DirectedGraphNode> graph,
                            DirectedGraphNode s, DirectedGraphNode t) {
        // write your code here
        if (graph == null) {
            return false;
        }

        Queue<DirectedGraphNode> queue = new LinkedList<DirectedGraphNode>();
        queue.offer(s);
        HashSet<DirectedGraphNode> visited = new HashSet<DirectedGraphNode>();

        while (!queue.isEmpty()) {
            DirectedGraphNode cur = queue.poll();
            if (cur == t) {
                return true;
            }

            for (DirectedGraphNode nb : cur.neighbors) {
                if (visited.contains(nb)) {
                    continue;
                }

                queue.offer(nb);
                visited.add(nb);
            }
        }

        return false;
    }
}

/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) {
 *         label = x;
 *         neighbors = new ArrayList<DirectedGraphNode>();
 *     }
 * };
 */
public class Solution {
   /**
     * @param graph: A list of Directed graph node
     * @param s: the starting Directed graph node
     * @param t: the terminal Directed graph node
     * @return: a boolean value
     */
    public boolean hasRoute(ArrayList<DirectedGraphNode> graph,
                            DirectedGraphNode s, DirectedGraphNode t) {
        // write your code here
        if (graph == null) {
            return false;
        }

        if (s == t) {
            return true;
        }

        boolean result = false;
        for (DirectedGraphNode nb : s.neighbors) {
            result |= hasRoute(graph, nb, t);
        }

        return result;
    }
}
