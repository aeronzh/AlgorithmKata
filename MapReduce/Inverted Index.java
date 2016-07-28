/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 * Definition of Document:
 * class Document {
 *     public int id;
 *     public String content;
 * }
 */
public class InvertedIndex {

    public static class Map {
        public void map(String _, Document value,
                        OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            
            // key/offset: the address of the article in the original large document
            // value: the article
            // 1. Extract all the words
            // 2. Output: <word, key/offset>
            // note: duplicates i.e. same words in the same value
            
            StringTokenizer tokenizer = new StringTokenizer(value.content);
            while (tokenizer.hasMoreTokens()) {
                output.collect(tokenizer.nextToken(), value.id);
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<Integer> values,
                           OutputCollector<String, List<Integer>> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, List<Integer> value);
            // *the input of Reduce is the output of Map
            
            // key: word
            // values: articels that contains word
            
            List<Integer> result = new ArrayList<Integer>();
            HashSet<Integer> set = new HashSet<Integer>(); // handle duplicates
            while (values.hasNext()) {
                int index = values.next();
                if (set.add(index)) {
                    result.add(index);
                }
            }
            output.collect(key, result);
        }
    }
}