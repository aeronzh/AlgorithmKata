// Implement a rate limiter, provide one method: is_ratelimited(timestamp, event, rate, increment).

// timestamp: The current timestamp, which is an integer and in second unit.
// event: The string to distinct different event. for example, "login" or "signup".
// rate: The rate of the limit. 1/s (1 time per second), 2/m (2 times per minute), 10/h (10 times per hour), 100/d (100 times per day). The format is [integer]/[s/m/h/d].
// increment: Whether we should increase the counter. (or take this call as a hit of the given event)
// The method should return true or false to indicate the event is limited or not.

// Have you met this question in a real interview? Yes
// Example
// is_ratelimited(1, "login", "3/m", true), return false.
// is_ratelimited(11, "login", "3/m", true), return false.
// is_ratelimited(21, "login", "3/m", true), return false.
// is_ratelimited(30, "login", "3/m", true), return true.
// is_ratelimited(65, "login", "3/m", true), return false.
// is_ratelimited(300, "login", "3/m", true), return false.
// Challenge 
// How many different algorithms you can come up with?

public class RateLimiter {
    
    Map<String, List<Integer>> map;
    
    public RateLimiter() {
        map = new HashMap<>();
    }
    
    /**
     * @param timestamp the current timestamp
     * @param event the string to distinct different event
     * @param rate the format is [integer]/[s/m/h/d]
     * @param increment whether we should increase the counter
     * @return true or false to indicate the event is limited or not
     */
    public boolean isRatelimited(int timestamp, String event, String rate, boolean increment) {
        // Write your code here
        int index = rate.indexOf("/");
        int allowed_times = Integer.parseInt(rate.substring(0, index));
        String unit = rate.substring(index + 1);
        
        int interval = 1;
        if (unit.equals("m")) {
            interval *= 60;
        } else if (unit.equals("h")) {
            interval *= 60 * 60;
        } else if (unit.equals("d")) {
            interval *= 60 * 60 * 24;
        }
        
        if (!map.containsKey(event)) {
            map.put(event, new ArrayList<Integer>());
        }
        
        boolean result = no_of_events(map.get(event), timestamp - interval + 1) >= allowed_times;
        if (!result && increment) {
            map.get(event).add(timestamp);
        }
        
        return result;
    }
    
    private int no_of_events(List<Integer> timestamps, int target) {
        if (timestamps.size() == 0) {
            return 0;
        }
        
        int left = 0;
        int right = timestamps.size() - 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (timestamps.get(mid) == target) {
                right = mid;
            } else if (timestamps.get(mid) < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        if (timestamps.get(left) >= target) {
            return timestamps.size() - left;
        } else if (timestamps.get(right) >= target) {
            return timestamps.size() - right;
        }
        
        return 0;
    }
}