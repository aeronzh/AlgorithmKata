public class TinyUrl {
    
    public static int GLOBAL_ID = 0;
    HashMap<Integer, String> id2url;
    HashMap<String, Integer> url2id;
    
    public TinyUrl() {
        id2url = new HashMap<Integer, String>();
        url2id = new HashMap<String, Integer>();
    }
    
    /**
     * @param url a long url
     * @return a short url starts with http://tiny.url/
     */
    public String longToShort(String url) {
        // Write your code here
        if (url2id.containsKey(url)) {
            return "http://tiny.url/" + idToShortKey(url2id.get(url));
        }
        GLOBAL_ID++;
        url2id.put(url, GLOBAL_ID);
        id2url.put(GLOBAL_ID, url);
        String shortURL = idToShortKey(GLOBAL_ID);
        return "http://tiny.url/" + shortURL;
    }

    /**
     * @param url a short url starts with http://tiny.url/
     * @return a long url
     */
    public String shortToLong(String url) {
        // Write your code here
        String shortKey = getShortKey(url);
        int id = shortKeytoID(shortKey);
        return id2url.get(id);
    }
    
    private String idToShortKey(int id) {
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shortURL = "";
        while (id != 0) {
            shortURL = chars.charAt(id % 62) + shortURL;
            id = id / 62;
        }
        // short key's length equals to 6
        while (shortURL.length() < 6) {
            shortURL = "0" + shortURL;
        }
        return shortURL;
    }
    
    private int shortKeytoID(String url) {
        int id = 0;
        for (char c : url.toCharArray()) {
            id = id * 62 + toBase62(c);
        }
        return id;
    }
    
    private int toBase62(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        } else {
            return c - 'A' + 36;
        }
    }
    
    private String getShortKey(String url) {
        return url.substring("http://tiny.url/".length());
    }
}