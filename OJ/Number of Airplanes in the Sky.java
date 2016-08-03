// Given an interval list which are flying and landing time of the flight. How many airplanes are on the sky at most?

//  Notice

// If landing and flying happens at the same time, we consider landing should happen at first.

// Have you met this question in a real interview? Yes
// Example
// For interval list

// [
//   [1,10],
//   [2,3],
//   [5,8],
//   [4,7]
// ]
// Return 3

// Tags 
// LintCode Copyright Array Interval

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */

class Solution {
    /**
     * @param intervals: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) { 
        // write your code here
        List<Pair> pairs = new ArrayList<Pair>();
        for (Interval i : airplanes) {
            pairs.add(new Pair(i.start, 1));
            pairs.add(new Pair(i.end, 0));
        }
        
        Collections.sort(pairs, pairComparator);
        
        int max = 0;
        int count = 0;
        for (Pair p : pairs) {
            count += p.start == 1 ? 1 : -1;
            max = Math.max(max, count);
        }
        
        return max;
    }
    
    Comparator<Pair> pairComparator = new Comparator<Pair>() {
        public int compare(Pair a, Pair b) {
            return a.time == b.time ? a.start - b.start : a.time - b.time;
        }
    };
}

class Pair {
    int time;
    int start;
    public Pair(int time, int start) {
        this.time = time;
        this.start = start;
    }
}