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
public class TopKFrequentWords {

    public static class Map {
        public void map(String _, Document value,
                        OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            
            // map is similar to word count
            StringTokenizer tokenizer = new StringTokenizer(value.content);
            while (tokenizer.hasMoreTokens()) {
                output.collect(tokenizer.nextToken(), 1);
            }
        }
    }

    public static class Reduce {
        PriorityQueue<Pair> heap;
        int k;
        Comparator<Pair> pairComparator = new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.frequency != b.frequency ?
                        a.frequency - b.frequency : 
                        b.word.compareTo(a.word);
            }
        };
        
        public void setup(int k) {
            // initialize your data structure here
            this.k = k;
            heap = new PriorityQueue<Pair>(k, pairComparator);
        }   

        public void reduce(String key, Iterator<Integer> values) {
            // Write your code here
            int frequency = 0;
            while (values.hasNext()) {
                frequency += values.next();
            }
            
            Pair pair = new Pair(key, frequency);
            if (heap.size() < k) {
                heap.offer(pair);
            } else if (pairComparator.compare(pair, heap.peek()) > 0) {
                heap.poll();
                heap.offer(pair);
            }
        }

        public void cleanup(OutputCollector<String, Integer> output) {
            // Output the top k pairs <word, times> into output buffer.
            // Ps. output.collect(String key, Integer value);
            List<Pair> list = new ArrayList<Pair>();
            
            while (!heap.isEmpty()) {
                list.add(heap.poll());
            }
            
            for (int i = list.size() - 1; i >= 0; i--) {
                Pair pair = list.get(i);
                output.collect(pair.word, pair.frequency);
            }
        }
    }
}

class Pair{
    String word;
    int frequency;
    public Pair(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }
}