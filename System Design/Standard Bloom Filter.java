// Implement a standard bloom filter. Support the following method:
// 1. StandardBloomFilter(k),The constructor and you need to create k hash functions.
// 2. add(string). add a string into bloom filter.
// 3. contains(string). Check a string whether exists in bloom filter.

// Have you met this question in a real interview? Yes
// Example
// StandardBloomFilter(3)
// add("lint")
// add("code")
// contains("lint") // return true
// contains("world") // return false
// Tags 
// Hash Table

public class StandardBloomFilter {
    
    int k;
    BitSet bits;
    List<HashFunction> hashFunc;
    
    public StandardBloomFilter(int k) {
        // initialize your data structure here
        this.k = k;
        bits = new BitSet(100000 + k);
        hashFunc = new ArrayList<HashFunction>();
        for (int i = 0; i < k; i++) {
            hashFunc.add(new HashFunction(100000 + i, 2 * i + 3));
        }
    }

    public void add(String word) {
        // Write your code here
        for (int i = 0; i < k; i++) {
            bits.set(hashFunc.get(i).hash(word));
        }
    }

    public boolean contains(String word) {
        // Write your code here
        for (int i = 0; i < k; i++) {
            if (!bits.get(hashFunc.get(i).hash(word))) {
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