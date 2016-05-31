* @param buildings: A list of lists of integers
* @return: Find the outline of those buildings
*/
public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
   // write your code here
   ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
   if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
       return result;
   }

   ArrayList<Edge> edges = new ArrayList<Edge>();
   for (int[] building : buildings) {
       Edge start = new Edge(building[0], building[2], true);
       edges.add(start);
       Edge end = new Edge(building[1], building[2], false);
       edges.add(end);
   }
   Collections.sort(edges, Edge.EdgeComparator);

   PriorityQueue<Integer> heap = new PriorityQueue<Integer>();

   for (Edge edge : edges) {
       ArrayList<Integer> cur = new ArrayList<Integer>();
       if (edge.isStart) {
           if (heap.isEmpty() || edge.height > heap.peek().height) {
               cur.add(edge.pos, edge.height);
               result.add(cur);
           }
           heap.add(cur.height);
       } else {
           heap.remove(edge.height);
           if (heap.isEmpty()) {
               cur.add(edge.pos);
               cur.add(0);
           } else if (edge.height > heap.peek().height) {
               cur.add(edge.pos);
               cur.add(heap.peek().height);
           }
           result.add(cur);
       }
   }

   return output(result);
}

private ArrayList<ArrayList<Integer>> output(ArrayList<ArrayList<Integer>> result) {
   ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();

   int pre = result.get(0).get(0);
   int height = result.get(0).get(1);
   for (int i = 1; i < result.length; i++) {
       ArrayList<Integer> cur = new ArrayList<Integer>();
       int end = result.get(i).get(0);
       if (height > 0) {
           cur.add(pre);
           cur.add(end);
           cur.add(height);
           output.add(cur);
       }
       pre = end;
       height = result.get(i).get(1);
   }

   return output;
}
}

class Edge {
int pos;
int height;
boolean isStart;

public Edge(int pos, int height, boolean isStart) {
   this.pos = pos;
   this.height = height;
   this.isStart = isStart;
}

public static Comparator<Edge> EdgeComparator = new Comparator<Edge>() {
   // public int compare(Edge e1, Edge e2) {

   // }
   public int compare(Edge arg1, Edge arg2) {
     Edge l1 = (Edge) arg1;
     Edge l2 = (Edge) arg2;
     if (l1.pos != l2.pos)
       return compareInteger(l1.pos, l2.pos);
     if (l1.isStart && l2.isStart) {
       return compareInteger(l2.height, l1.height);
     }
     if (!l1.isStart && !l2.isStart) {
       return compareInteger(l1.height, l2.height);
     }
     return l1.isStart ? -1 : 1;
   }

   int compareInteger(int a, int b) {
     return a <= b ? -1 : 1;
   }
}
}
