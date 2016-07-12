// Given a collection of intervals, merge all overlapping intervals.

// For example,
// Given [1,3],[2,6],[8,10],[15,18],
// return [1,6],[8,10],[15,18].

// Hide Company Tags LinkedIn Google Facebook Twitter Microsoft Bloomberg Yelp
// Hide Tags Array Sort
// Hide Similar Problems (H) Insert Interval (E) Meeting Rooms (M) Meeting Rooms II

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) {
            return result;
        }
        
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        
        int i = 0;
        int j = 0;
        while (j < intervals.size()) {
            int end = intervals.get(i).end;
            while (j < intervals.size() && end >= intervals.get(j).start) {
                end = Math.max(end, intervals.get(j).end);
                j++;
            }
            
            result.add(new Interval(intervals.get(i).start, end));
            i = j;
        }
        
        return result;
    }
}

// v2
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) {
            return result;
        }
        
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        
        Interval cur = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            if (cur.end < intervals.get(i).start) {
                result.add(cur);
                cur = intervals.get(i);
            } else {
                cur.end = Math.max(cur.end, intervals.get(i).end);
            }
        }
        result.add(cur);
        
        return result;
    }
}