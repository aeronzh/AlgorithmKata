// Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
//
// Return all such possible sentences.
//
// For example, given
// s = "catsanddog",
// dict = ["cat", "cats", "and", "sand", "dog"].
//
// A solution is ["cats and dog", "cat sand dog"].
//
// Hide Company Tags Dropbox Google Uber Snapchat Twitter
// Hide Tags Dynamic Programming Backtracking

// similar to subsets
public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> result = new ArrayList<String>();
        if (s == null || wordDict == null) {
            return result;
        }

        result = dfs(s, wordDict, new HashMap<Integer, List<String>>(), 0);
        return result;
    }

    private List<String> dfs(String s, Set<String> wordDict, HashMap<Integer, List<String>> map, int pos) {
        if (map.containsKey(pos)) {
            return map.get(pos);
        }

        List<String> result = new ArrayList<String>();

        if (pos == s.length()) {
            result.add("");
            return result;
        }

        for (int i = pos; i < s.length() + 1; i++) {
            if (wordDict.contains(s.substring(pos, i))) {
                List<String> temp = dfs(s, wordDict, map, i);
                for (String rest : temp) {
                    result.add(s.substring(pos, i) + (rest.length() == 0 ? "" : " ") + rest);
                }
            }
        }

        map.put(pos, new ArrayList<String>(result));

        return result;
    }
}

// v2
public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> result = new ArrayList<String>();
        if (s == null || wordDict == null) {
            return result;
        }

        result = dfs(s, wordDict, new HashMap<String, List<String>>());
        return result;
    }

    private List<String> dfs(String s, Set<String> wordDict, HashMap<String, List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }

        List<String> result = new ArrayList<String>();

        if (s.length() == 0) {
            result.add("");
            return result;
        }

        for (int i = 0; i < s.length() + 1; i++) {
            if (wordDict.contains(s.substring(0, i))) {
                List<String> temp = dfs(s.substring(i), wordDict, map);
                for (String rest : temp) {
                    result.add(s.substring(0, i) + (rest.length() == 0 ? "" : " ") + rest);
                }
            }
        }

        map.put(s, new ArrayList<String>(result));

        return result;
    }
}
