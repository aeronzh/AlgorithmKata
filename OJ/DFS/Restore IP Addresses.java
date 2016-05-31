public class Solution {
    /**
     * @param s the IP string
     * @return All possible valid IP addresses
     */
    public ArrayList<String> restoreIpAddresses(String s) {
        // Write your code here
        ArrayList<String> result = new ArrayList<String>();

        if (s == null || s.length() == 0) {
            return result;
        }

        restore(result, new ArrayList<String>(), s, 0);

        return result;
    }

    private void restore(ArrayList<String> result, ArrayList<String> list, String s, int pos) {
        if (list.size() == 4 && pos == s.length()) {
            StringBuilder sb = new StringBuilder();
            sb.append(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                sb.append(".").append(list.get(i));
            }
            result.add(sb.toString());
            return;
        }

        for (int i = pos; i < s.length() && i < pos + 3; i++) {
            String section = s.substring(pos, i + 1);
            if (isValid(section)) {
                list.add(section);
                restore(result, list, s, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean isValid(String s) {
        if (s.length() != 1 && s.charAt(0) == '0' || Integer.parseInt(s) > 255) {
            return false;
        }

        return true;
    }
}
