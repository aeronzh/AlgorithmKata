// Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

// Only one letter can be changed at a time
// Each intermediate word must exist in the dictionary
//  Notice

// All words have the same length.
// All words contain only lowercase alphabetic characters.
// Have you met this question in a real interview? Yes
// Example
// Given:
// start = "hit"
// end = "cog"
// dict = ["hot","dot","dog","lot","log"]
// Return
//   [
//     ["hit","hot","dot","dog","cog"],
//     ["hit","hot","lot","log","cog"]
//   ]
// Tags 
// Backtracking Depth First Search Breadth First Search

public class Solution {
    /**
      * @param start, a string
      * @param end, a string
      * @param dict, a set of string
      * @return a list of lists of string
      */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
        List<List<String>> result = new ArrayList<List<String>>();
        
        if (dict == null || dict.size() == 0) {
            return result;
        }
        
        dict.add(start);
        dict.add(end);
        HashMap<String, Integer> distance = new HashMap<String, Integer>();
        HashMap<String, List<String>> nextLevel = new HashMap<String, List<String>>();
        
        bfs(distance, nextLevel, start, end, dict);
        
        List<String> path = new ArrayList<String>();
        path.add(end);
        dfs(result, path, start, end, distance, nextLevel);
        
        return result;
    }
    
    private void bfs(HashMap<String, Integer> distance, HashMap<String, List<String>> nextLevel, String start, String end, Set<String> dict) {
        int path = 0;
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        distance.put(start, 0);
        
        for (String word : dict) {
             nextLevel.put(word, new ArrayList<String>());
        }
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            path++;
            
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                for (String word : getNextWords(cur, dict)) {
                    nextLevel.get(word).add(cur);
                    if (!distance.containsKey(word)) {
                        distance.put(word, path);
                        queue.offer(word);
                    }
                }
            }
        }
    }
    
    private void dfs(List<List<String>> result, List<String> path, String start, String end, HashMap<String, Integer> distance, HashMap<String, List<String>> nextLevel) {
        if (end.equals(start)) {
            Collections.reverse(path);
            result.add(new ArrayList<String>(path)); 
            Collections.reverse(path);
        }
        
        
        for (String word : nextLevel.get(end)) {
            if (distance.get(word) + 1 != distance.get(end)) {
                continue;
            }
            path.add(word);
            dfs(result, path, start, word, distance, nextLevel);
            path.remove(path.size() - 1);
        }
    }
    
    private List<String> getNextWords(String word, Set<String> dict) {
        List<String> list = new ArrayList<String>();
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            for (char j = 'a'; j <= 'z'; j++) {
                if (j == c) {
                    continue;
                }
                String nextWord = word.substring(0, i) + j + word.substring(i + 1);
                if (dict.contains(nextWord)) {
                    list.add(nextWord);
                }
            }
        }
        
        return list;
    }
}