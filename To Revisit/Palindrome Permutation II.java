public class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> result = new ArrayList<String>();

        if (s == null || s.length() == 0) {
            return result;
        }

        // 1. check if a palindrome permutation exists
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        if (!hasPalindromePermutations(chars, map)) {
            return result;
        }

        // 2. generate the first half of the string
        List<Character> list = new ArrayList<Character>();
        String mid = "";
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();

            if (val % 2 == 1) {
                mid += key;
            }

            for (int i = 0; i < val / 2; i++) {
                list.add(key);
            }
        }


        boolean[] visited = new boolean[list.size()];
        dfs(result, new StringBuilder(), list, mid, visited);
        return result;
    }

    private boolean hasPalindromePermutations(char[] chars, HashMap<Character, Integer> map) {
        int odd = 0;

        for (char c : chars) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }

            odd += map.get(c) % 2 == 0 ? -1 : 1;
        }

        return odd == 0 || odd == 1;
    }

    private void dfs(List<String> result, StringBuilder sb, List<Character> list, String mid, boolean[] visited) {
        if (sb.length() == list.size()) {
            result.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse(); // do not forget to reverse back
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (visited[i] || (i != 0 && !visited[i - 1] && list.get(i - 1) == list.get(i))) {
                continue;
            }

            visited[i] = true;
            sb.append(list.get(i));
            dfs(result, sb, list, mid, visited);
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
