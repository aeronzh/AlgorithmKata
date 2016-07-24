// http://www.1point3acres.com/bbs/thread-161616-1-1.html

// 1. Given an number, convert it to an Palindrome number by modifying the latter half: 12333 => 12321, 10000 => 9999 or 10001
// 2. Find the closest palindrome number

public class PalindromeNumbers {

    public static void main(String[] args) {
        System.out.println(convert(12333));
        System.out.println(convert(12999));
        System.out.println(findClosest(12999));
    }

    private static int convert(int num) {
        String n = String.valueOf(num);
        int len = n.length();

        String prefix = n.substring(0, len / 2);
        String result;

        if (len % 2 == 1) {
            result = prefix + n.charAt(len / 2) + new StringBuilder(prefix).reverse();
        }  else {
            result = prefix + new StringBuilder(prefix).reverse();
        }

        return Integer.parseInt(result); // overflow is possible
    }

    // 10000 => 10001 (itself), 10101 (prefix + 1) and 10901 (prefix - 1)
    // special case: 12999 => 13031 (convert(129 + 1))
    private static int findClosest(int num) {
        int len = String.valueOf(num).length();

        int result = convert(num);
        int diff = Math.abs(num - result);

        int temp = convert(num - (int)Math.pow(10, len / 2));
        if (Math.abs(num - temp) < diff) {
            diff = Math.abs(num - temp);
            result = temp;
        }

        temp = convert(num + (int)Math.pow(10, len / 2));
        if (Math.abs(num - temp) < diff) {
            result = temp;
        }

        return result;
    }
}