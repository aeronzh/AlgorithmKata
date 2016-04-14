// https://www.careercup.com/question?id=5648527329853440

class Node {
    int id;
    int parent;
    int weight;

    Node(int id, int parent, int weight) {
        this.id = id;
        this.parent = parent;
        this.weight = weight;
    }
}

class Solution {
    public void printSubTreeWeight(List<Node> nodes) {
        if (nodes == null || nodes.length == 0) {
            return;
        }

        HashMap<Node, ArrayList<Node>> children = new HashMap<Node, ArrayList<Node>>();
        Node root = null;

        for (Node n : nodes) {
            if (n.id == 0) {
                root = n;
            }
            int parent = n.parent;
            if (children.contains(parent)) {
                ArrayList<Node> list = children.get(parent);
            } else {
                ArrayList<Node> list = new ArrayList<Node>();
            }
            list.add(n);
            children.put(parent, list);
        }

        calculateSum(root, children);
    }

    private int calculateSum(TreeNode root, HashMap<Node, ArrayList<Node>> children) {
        int sum = root.weight; // have to be outside of the if statement. Leaf nodes need to be printed as well
        if (children.containsKey(root)) { // otherwise it's a leaf node
            for (Node child : children.get(root)) {
                sum += calculateSum(child, children);
            }
        }

        System.out.println("Weight for " + root.id + " is " + sum);
        return sum;
    }
}
