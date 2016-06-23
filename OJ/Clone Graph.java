/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        map.put(node, new UndirectedGraphNode(node.label));

        while (!queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();

            for (UndirectedGraphNode nb : cur.neighbors) {
                if (!map.containsKey(nb)) {
                    map.put(nb, new UndirectedGraphNode(nb.label));
                    queue.offer(nb);
                }
            }
        }

        for (Map.Entry<UndirectedGraphNode, UndirectedGraphNode> entry : map.entrySet()) {
            UndirectedGraphNode original = entry.getKey();
            UndirectedGraphNode copy = entry.getValue();
            for (UndirectedGraphNode nb : original.neighbors) {
                copy.neighbors.add(map.get(nb));
            }
        }

        return map.get(node);
    }
}

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }

        List<UndirectedGraphNode> nodes = getNodes(node);

        HashMap<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();
        for (UndirectedGraphNode n : nodes) {
            mapping.put(n, new UndirectedGraphNode(n.label));
        }

        for (UndirectedGraphNode n : nodes) {
            for (UndirectedGraphNode nb : n.neighbors) {
                mapping.get(n).neighbors.add(mapping.get(nb));
            }
        }

        return mapping.get(node);
    }

    private List<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
        Stack<UndirectedGraphNode> stack = new Stack<UndirectedGraphNode>();
        HashSet<UndirectedGraphNode> set = new HashSet<UndirectedGraphNode>();
        stack.push(node);
        set.add(node);

        while (!stack.isEmpty()) {
            UndirectedGraphNode u = stack.peek();
            UndirectedGraphNode v = null;

            for (UndirectedGraphNode nb : u.neighbors) {
                if (!set.contains(nb)) {
                    v = nb;
                }
            }

            if (v == null) {
                stack.pop();
            } else {
                stack.push(v);
                set.add(v);
            }
        }

        return new ArrayList<UndirectedGraphNode>(set);
    }
}
