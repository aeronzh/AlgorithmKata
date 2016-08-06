// Implement a web logger, which provide two methods:

// hit(timestamp), record a hit at given timestamp.
// get_hit_count_in_last_5_minutes(timestamp), get hit count in last 5 minutes.
// the two methods will be called with non-descending timestamp (in sec).

// Have you met this question in a real interview? Yes
// Example
// hit(1);
// hit(2);
// get_hit_count_in_last_5_minutes(3);
// >> 2
// hit(300);
// get_hit_count_in_last_5_minutes(300);
// >> 3
// get_hit_count_in_last_5_minutes(301);
// >> 2

public class WebLogger {
    
    // circular array
    int[] lastHitTime;
    int[] hit;
    
    public WebLogger() {
        // initialize your data structure here.
        lastHitTime = new int[300];
        hit = new int[300];
    }

    /**
     * @param timestamp an integer
     * @return void
     */
    public void hit(int timestamp) {
        // Write your code here
        int index = timestamp % 300;
        if (timestamp == lastHitTime[index]) {
            hit[index]++; // multiple hits at the same time
        } else {
            hit[index] = 1;
        }
        lastHitTime[index] = timestamp;
    }

    /**
     * @param timestamp an integer
     * @return an integer
     */
    public int get_hit_count_in_last_5_minutes(int timestamp) {
        // Write your code here
        int count = 0;
        for (int i = 0; i < 300; i++) {
            if (timestamp - lastHitTime[i] < 300) {
                count += hit[i];
            }
        }
        
        return count;
    }
}

public class WebLogger {
    
    Queue<Integer> queue;
    
    public WebLogger() {
        // initialize your data structure here.
        queue = new LinkedList<>();
    }

    /**
     * @param timestamp an integer
     * @return void
     */
    public void hit(int timestamp) {
        // Write your code here
        while (!queue.isEmpty() && queue.peek() <= timestamp - 300) {
            queue.poll();
        }
        queue.offer(timestamp);
    }

    /**
     * @param timestamp an integer
     * @return an integer
     */
    public int get_hit_count_in_last_5_minutes(int timestamp) {
        // Write your code here
        while (!queue.isEmpty() && queue.peek() <= timestamp - 300) {
            queue.poll();
        }
        return queue.size();
    }
}