// note: negative inputs are possible
// negative input is converted into an array of negative numbers

public class BigInteger {
    public static void main(String[] args) {
        System.out.println(add("9999", "9999"));
        System.out.println(add("9999", "-9999"));
        System.out.println(add("9999", "-999999"));
    }

    private static String add(String a, String b) {
        int[] aNum = strToArray(a);
        int[] bNum = strToArray(b);
        int[] result = new int[Math.max(a.length(), b.length()) + 1]; // +1 for carry

        int ai = aNum.length - 1;
        int bi = bNum.length - 1;
        int idx = result.length - 1;
        int carry = 0;

        while (ai >= 0 || bi >= 0 || carry != 0) {
            int aDigit = ai >= 0 ? aNum[ai--] : 0;
            int bDigit = bi >= 0 ? bNum[bi--] : 0;
            int sum = aDigit + bDigit + carry;
            result[idx--] = sum % 10;
            carry = sum / 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            // skip initial 0s
            if (sb.length() == 0 && result[i] == 0) {
                continue;
            }
            if (sb.length() == 0 && result[i] < 0) {
                sb.append("-");
            }
            sb.append(Math.abs(result[i]));
        }

        return sb.length() == 0? "0" : sb.toString();
    }

    private static int[] strToArray(String num) {
        int sign = num.charAt(0) == '-' ? -1 : 1;
        num = num.charAt(0) == '-' ? num.substring(1) : num;
        int[] result = new int[num.length()];

        for (int i = 0; i < num.length(); i++) {
            result[i] = sign * (num.charAt(i) - '0');
        }

        return result;
    }

}