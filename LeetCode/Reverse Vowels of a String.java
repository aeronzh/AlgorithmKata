public class Solution {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        HashSet<Character> vowels = new HashSet<Character>();

        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');

        int left = 0;
        int right = s.length() - 1;
        char[] string = s.toCharArray();

        while (left < right) {
            while (left < right && !vowels.contains(s.charAt(left))) {
                left++;
            }

            while (left < right && !vowels.contains(s.charAt(right))) {
                right--;
            }

            if (left < right) {
                char temp = string[left];
                string[left] = string[right];
                string[right] = temp;
            }

            left++;
            right--;
        }

        return new String(string);
    }
}
