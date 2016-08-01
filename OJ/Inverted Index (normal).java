// Example
// Given a list of documents with id and content. (class Document)

// [
//   {
//     "id": 1,
//     "content": "This is the content of document 1 it is very short"
//   },
//   {
//     "id": 2,
//     "content": "This is the content of document 2 it is very long bilabial bilabial heheh hahaha ..."
//   },
// ]
// Return an inverted index (HashMap with key is the word and value is a list of document ids).

// {
//    "This": [1, 2],
//    "is": [1, 2],
//    ...
// }

/**
 * Definition of Document:
 * class Document {
 *     public int id;
 *     public String content;
 * }
 */
public class Solution {
    /**
     * @param docs a list of documents
     * @return an inverted index
     */
    public Map<String, List<Integer>> invertedIndex(List<Document> docs) {
        // Write your code here
        Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
        for (Document doc : docs) {
            StringTokenizer tokenizer = new StringTokenizer(doc.content);
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if (!map.containsKey(token)) {
                    map.put(token, new ArrayList<Integer>());
                }
                if (!map.get(token).contains(doc.id)) {
                    map.get(token).add(doc.id);
                }
            }
        }
        
        return map;
    }
}