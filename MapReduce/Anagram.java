/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 */
public class Anagram {

    public static class Map {
        public void map(String key, String value,
                        OutputCollector<String, String> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, String value);
            StringTokenizer tokenizer = new StringTokenizer(value);
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                output.collect(sortString(token), token);
            }
        }
        
        private String sortString(String string) {
            char[] chars = string.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<String> values,
                           OutputCollector<String, List<String>> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, List<String> value);
            List<String> result = new ArrayList<String>();
            while (values.hasNext()) {
                result.add(values.next());
            }
            output.collect(key, result);
        }
    }
}