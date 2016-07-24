import java.util.*;

public class Solution {
    public int FirstNotRepeatingChar(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
			if (!map.containsKey(c)) {
				queue.offer(i);
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        
        while (!queue.isEmpty()) {
			int pos = queue.poll();
            if (map.get(str.charAt(pos)) == 1) {
				return pos;
            }
        }
        
        return -1;
    }
}