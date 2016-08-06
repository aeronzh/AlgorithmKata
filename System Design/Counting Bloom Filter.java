public class CountingBloomFilter {
    
    int k;
    int[] bits;
    List<HashFunction> hashFunc;
    
    public CountingBloomFilter(int k) {
        // initialize your data structure here
        this.k = k;
        bits = new int[100000 + k];
        hashFunc = new ArrayList<HashFunction>();
        for (int i = 0; i < k; i++) {
            hashFunc.add(new HashFunction(100000 + i, 2 * i + 3));
        }
    }

    public void add(String word) {
        // Write your code here
        for (int i = 0; i < k; i++) {
            bits[hashFunc.get(i).hash(word)]++;
        }
    }

    public void remove(String word) {
        // Write your code here
        for (int i = 0; i < k; i++) {
            bits[hashFunc.get(i).hash(word)]--;
        }
    }

    public boolean contains(String word) {
        // Write your code here
        for (int i = 0; i < k; i++) {
            if (bits[hashFunc.get(i).hash(word)] == 0) {
                return false;
            }
        }
        
        return true;
    }
}

class HashFunction {
    
    int cap, seed;
    
    public HashFunction(int cap, int seed) {
        this.cap = cap;
        this.seed = seed;
    }
    
    public int hash(String val) {
        int hashcode = 0;
        for (char c : val.toCharArray()) {
            hashcode = (hashcode * seed + c) % cap;
        }
        return hashcode;
    }
}