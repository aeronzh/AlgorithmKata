// Design a data structure that supports all following operations in average O(1) time.

// insert(val): Inserts an item val to the set if not already present.
// remove(val): Removes an item val from the set if present.
// getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
// Example:

// // Init an empty set.
// RandomizedSet randomSet = new RandomizedSet();

// // Inserts 1 to the set. Returns true as 1 was inserted successfully.
// randomSet.insert(1);

// // Returns false as 2 does not exist in the set.
// randomSet.remove(2);

// // Inserts 2 to the set, returns true. Set now contains [1,2].
// randomSet.insert(2);

// // getRandom should return either 1 or 2 randomly.
// randomSet.getRandom();

// // Removes 1 from the set, returns true. Set now contains [2].
// randomSet.remove(1);

// // 2 was already in the set, so return false.
// randomSet.insert(2);

// // Since 1 is the only number in the set, getRandom always return 1.
// randomSet.getRandom();
// Hide Company Tags Google Uber Twitter Amazon Yelp Pocket Gems
// Hide Tags Array Hash Table Design

import java.util.Random;

public class RandomizedSet {
    
    Map<Integer, Integer> map; // <val, position>
    List<Integer> list;
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<Integer, Integer>();
        list = new ArrayList<Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, list.size());
            list.add(val);
            return true;
        }
        return false;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int lastVal = list.get(list.size() - 1);
            int valPos = map.get(val);
            list.set(valPos, lastVal); 
            list.remove(list.size() - 1);
            map.put(lastVal, valPos);
            map.remove(val);
            return true;
        }
        return false;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        if (list.size() > 0) {
            Random random = new Random();
            return list.get(random.nextInt(list.size()));
        }
        return -1;
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


// follow up: allow duplicates
// solution: Map<value, Set<positions>>