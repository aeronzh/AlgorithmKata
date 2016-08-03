// Implement a simple twitter. Support the following method:

// postTweet(user_id, tweet_text). Post a tweet.
// getTimeline(user_id). Get the given user's most recently 10 tweets posted by himself, order by timestamp from most recent to least recent.
// getNewsFeed(user_id). Get the given user's most recently 10 tweets in his news feed (posted by his friends and himself). Order by timestamp from most recent to least recent.
// follow(from_user_id, to_user_id). from_user_id followed to_user_id.
// unfollow(from_user_id, to_user_id). from_user_id unfollowed to to_user_id.
// Have you met this question in a real interview? Yes
// Example
// postTweet(1, "LintCode is Good!!!")
// >> 1
// getNewsFeed(1)
// >> [1]
// getTimeline(1)
// >> [1]
// follow(2, 1)
// getNewsFeed(2)
// >> [1]
// unfollow(2, 1)
// getNewsFeed(2)
// >> []

/**
 * Definition of Tweet:
 * public class Tweet {
 *     public int id;
 *     public int user_id;
 *     public String text;
 *     public static Tweet create(int user_id, String tweet_text) {
 *         // This will create a new tweet object,
 *         // and auto fill id
 *     }
 * }
 */

class Node {
    int timestamp;
    Tweet tweet;
    public Node(int timestamp, Tweet tweet) {
        this.timestamp = timestamp;
        this.tweet = tweet;
    }
}

public class MiniTwitter {
    
    HashMap<Integer, HashSet<Integer>> following; // <user_id, List<user_id>>
    HashMap<Integer, List<Node>> tweets; // <user_id, List<timestamp, tweet>>
    int timestamp;
    Comparator<Node> sortByTimestamp = new Comparator<Node>() {
        public int compare(Node a, Node b) {
            return b.timestamp - a.timestamp;
        }
    };
    
    public MiniTwitter() {
        // initialize your data structure here.
        following = new HashMap<>();
        tweets = new HashMap<>();
        timestamp = 0;
    }

    // @param user_id an integer
    // @param tweet a string
    // return a tweet
    public Tweet postTweet(int user_id, String tweet_text) {
        //  Write your code here
        timestamp++;
        if (!tweets.containsKey(user_id)) {
            tweets.put(user_id, new ArrayList<Node>());
        }
        Tweet tweet = Tweet.create(user_id, tweet_text);
        tweets.get(user_id).add(new Node(timestamp, tweet));
        return tweet;
    }

    // @param user_id an integer
    // return a list of 10 new feeds recently
    // and sort by timeline
    public List<Tweet> getNewsFeed(int user_id) {
        // Write your code here
        // TODO: to be replaced by merge k sorted arrays
        List<Tweet> feed = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();
        if (tweets.containsKey(user_id)) {
            nodes.addAll(getLastTen(tweets.get(user_id)));
        }
        if (following.containsKey(user_id)) {
            for (int f : following.get(user_id)) {
                if (tweets.containsKey(f)) {
                    nodes.addAll(getLastTen(tweets.get(f)));
                }
            }
        }
        Collections.sort(nodes, sortByTimestamp);
        nodes = getFirstTen(nodes);
        for (Node n : nodes) {
            feed.add(n.tweet);
        }
        return feed;
    }
        
    // @param user_id an integer
    // return a list of 10 new posts recently
    // and sort by timeline
    public List<Tweet>  getTimeline(int user_id) {
        // Write your code here
        List<Node> nodes = new ArrayList<>();
        if (tweets.containsKey(user_id)) {
            nodes.addAll(getLastTen(tweets.get(user_id)));
        }
        Collections.sort(nodes, sortByTimestamp);
        List<Tweet> feed = new ArrayList<>();
        for (Node n : nodes) {
            feed.add(n.tweet);
        }
        return feed;
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) {
        // Write your code here
        if (!following.containsKey(from_user_id)) {
            following.put(from_user_id, new HashSet<Integer>());
        }
        following.get(from_user_id).add(to_user_id);
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
        // Write your code here
        if (following.containsKey(from_user_id)) {
            if (following.get(from_user_id).contains(to_user_id)) {
                following.get(from_user_id).remove(to_user_id);
            }
        }
    }
    
    private List<Node> getLastTen(List<Node> list) {
        int last = list.size() > 10 ? 10 : list.size();
        return list.subList(list.size() - last, list.size());
    }
    
    private List<Node> getFirstTen(List<Node> list) {
        int first = list.size() > 10 ? 10 : list.size();
        return list.subList(0, first);
    }
}