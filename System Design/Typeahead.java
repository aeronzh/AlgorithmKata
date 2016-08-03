// Implement typeahead. Given a string and a dictionary, return all words that contains the string as a substring. The dictionary will give at the initialize method and wont be changed. The method to find all words with given substring would be called multiple times.

// Have you met this question in a real interview? Yes
// Example
// Given dictionary = {"Jason Zhang", "James Yu", "Bob Zhang", "Larry Shi"}

// search "Zhang", return ["Jason Zhang", "Bob Zhang"].

// search "James", return ["James Yu"].

// trie is used for prefix
// inverted index (implemented here) can also be used for substrings
public class Typeahead {
    
    HashMap<String, HashSet<String>> map;
    
    // @param dict A dictionary of words dict
    public Typeahead(Set<String> dict) {
        // do initialize if necessary
        map = new HashMap<>();
        for (String str : dict) {
            for (int i = 0; i < str.length(); i++) {
                for (int j = i + 1; j <= str.length(); j++) {
                    String s = str.substring(i, j);
                    if (!map.containsKey(s)) {
                        map.put(s, new HashSet<String>());
                    }
                    map.get(s).add(str);
                }
            }
        }
    }

    // @param str: a string
    // @return a list of words
    public List<String> search(String str) {
        // Write your code here
        if (!map.containsKey(str)) {
            return new ArrayList<String>();
        }
        return new ArrayList<String>(map.get(str));
    }
}