// There are a total of n courses you have to take, labeled from 0 to n - 1.

// Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

// Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

// For example:

// 2, [[1,0]]
// There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

// 2, [[1,0],[0,1]]
// There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

// Note:
// The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

// click to show more hints.

// Hints:
// This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
// Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
// Topological sort could also be done via BFS.
// Hide Company Tags Apple Yelp Zenefits
// Hide Tags Depth-first Search Breadth-first Search Graph Topological Sort
// Hide Similar Problems (M) Course Schedule II (M) Graph Valid Tree (M) Minimum Height Trees

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            return true;
        }
        
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<Integer, HashSet<Integer>>();
        HashMap<Integer, Integer> indegree = new HashMap<Integer, Integer>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new HashSet<Integer>());
            indegree.put(i, 0);
        }
        
        for (int[] prereq : prerequisites) {
            if (!graph.get(prereq[1]).contains(prereq[0])) {
                graph.get(prereq[1]).add(prereq[0]);
                indegree.put(prereq[0], indegree.get(prereq[0]) + 1);
            }
        }
        
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int n : indegree.keySet()) {
            if (indegree.get(n) == 0) {
                queue.offer(n);
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                count++;
                for (int nb : graph.get(cur)) {
                    indegree.put(nb, indegree.get(nb) - 1);
                    if (indegree.get(nb) == 0) {
                        queue.offer(nb);
                    }
                }
            }
        }
        
        if (count != numCourses) {
            return false;
        }
        
        return true;
    }
}

// DFS
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            return true;
        }
        
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<Integer, HashSet<Integer>>();
        HashMap<Integer, Integer> status = new HashMap<Integer, Integer>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new HashSet<Integer>());
            status.put(i, 0);
        }
        
        for (int[] prereq : prerequisites) {
            if (!graph.get(prereq[1]).contains(prereq[0])) {
                graph.get(prereq[1]).add(prereq[0]);
            }
        }
        
        for (int i = 0; i < numCourses; i++) {
            if (status.get(i) == 0) {
                if (!dfs(status, graph, i)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean dfs(HashMap<Integer, Integer> status, HashMap<Integer, HashSet<Integer>> graph, int i) {
        status.put(i, 1);
        
        for (int nb : graph.get(i)) {
            if (status.get(nb) == 1) {
                return false;
            }
            if (!dfs(status, graph, nb)) {
                return false;
            }
        }
        
        status.put(i, 2);
        return true;
    }
}

// iterative DFS
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null) {
            return true;
        }
        
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<Integer, HashSet<Integer>>();
        HashMap<Integer, Integer> status = new HashMap<Integer, Integer>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new HashSet<Integer>());
            status.put(i, 0);
        }
        
        for (int[] prereq : prerequisites) {
            if (!graph.get(prereq[1]).contains(prereq[0])) {
                graph.get(prereq[1]).add(prereq[0]);
            }
        }
        
        for (int i = 0; i < numCourses; i++) {
            if (status.get(i) == 0) {
                if (!dfs(status, graph, i)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean dfs(HashMap<Integer, Integer> status, HashMap<Integer, HashSet<Integer>> graph, int i) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(i);
        status.put(i, 1);
        
        while (!stack.isEmpty()) {
            int u = stack.peek();
            int v = -1;
            
            for (int nb : graph.get(u)) {
                if (status.get(nb) == 0) {
                    v = nb;
                    break;
                } else if (status.get(nb) == 1) {
                    return false;
                }
            }
            
            if (v == -1) {
                stack.pop();
                status.put(u, 2);
            } else {
                stack.push(v);
                status.put(v, 1);
            }
        }
        
        return true;
    }
}