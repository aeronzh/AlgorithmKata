public class Solution {
    /**
     * @param digits A digital string
     * @return all posible letter combinations
     */
    public ArrayList<String> letterCombinations(String digits) {
        // Write your code here
        ArrayList<String> result = new ArrayList<String>();

        if (digits == null || digits.length() == 0) {
            return result;
        }

        HashMap<Character, char[]> map = new HashMap<Character, char[]>();
        map.put('0', new char[] {});
        map.put('1', new char[] {});
        map.put('2', new char[] {'a', 'b', 'c'});
        map.put('3', new char[] {'d', 'e', 'f'});
        map.put('4', new char[] {'g', 'h', 'i'});
        map.put('5', new char[] {'j', 'k', 'l'});
        map.put('6', new char[] {'m', 'n', 'o'});
        map.put('7', new char[] {'p', 'q', 'r', 's'});
        map.put('8', new char[] {'t', 'u', 'v'});
        map.put('9', new char[] {'w', 'x' ,'y', 'z'});

        dfs(result, new StringBuilder(), digits, map, 0);

        return result;
    }

    private void dfs(List<String> result, StringBuilder sb, String digits, HashMap<Character, char[]> map, int pos) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }

        for (char c : map.get(digits.charAt(pos))) {
            sb.append(c);
            dfs(result, sb, digits, map, pos + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
