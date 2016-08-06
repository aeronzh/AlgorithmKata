// Geohash is a hash function that convert a location coordinate pair into a base32 string.

// Check how to generate geohash on wiki: Geohash or just google it for more details.

// Try http://geohash.co/.

// You task is converting a (latitude, longitude) pair into a geohash string.

// Have you met this question in a real interview? Yes
// Example
// Given lat = 39.92816697, lng = 116.38954991 and precision = 12 which indicate how long the hash string should be. You should return wx4g0s8q3jf9.

// The precision is between 1 ~ 12.

public class GeoHash {
    /**
     * @param latitude, longitude a location coordinate pair 
     * @param precision an integer between 1 to 12
     * @return a base32 string
     */
    public String encode(double latitude, double longitude, int precision) {
        // Write your code here
        String base32 = "0123456789bcdefghjkmnpqrstuvwxyz";
        String lng_bin = getBin(longitude, -180, 180);
        String lat_bin = getBin(latitude, -90, 90);
        
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            binary.append(lng_bin.charAt(i));
            binary.append(lat_bin.charAt(i));
        }
        
        StringBuilder hashcode = new StringBuilder();
        for (int i = 0; i < 60; i += 5) {
            int index = Integer.parseInt(binary.substring(i, i + 5), 2); // 5 bits = 1 base32char
            hashcode.append(base32.charAt(index));
        }
        
        return hashcode.substring(0, precision);
    }
    
    // val ~ [left, mid] = 0, val ~ [mid, right] = 1
    private String getBin(double val, double left , double right) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            double mid = left + (right - left) / 2.0;
            if (val <= mid) {
                sb.append("0");
                right = mid;
            } else {
                sb.append("1");
                left = mid;
            }
        }
        
        return sb.toString();
    }
}