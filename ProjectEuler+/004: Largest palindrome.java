// brute force with some optimizations. Not optimized if N is a small number
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            System.out.println(solve(n));
        }
    }

    private static int solve(int n) {
        int largest = 0;
        for (int i = 999; i >= 100; i--) {
            for (int j = 999; j >= i; j--) {
                int cur = i * j;
                if (cur < largest) {
                    break;
                }
                if (cur == reverse(cur) && cur < n) {
                    largest = Math.max(largest, cur);
                }
            }
        }

        return largest;
    }

    private static int reverse(int n) {
        int reverse = 0;
        while (n != 0) {
            reverse = reverse * 10 + n % 10;
            n /= 10;
        }
        return reverse;
    }
}

// brute force
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            System.out.println(solve(n));
        }
    }

    private static int solve(int n) {
        int largest = 0;
        for (int i = 100; i <= 999; i++) {
            for (int j = i + 1; j <= 999; j++) {
                int cur = i * j;
                if (cur >= n) {
                    break;
                }
                if (cur == reverse(cur)) {
                    largest = Math.max(largest, cur);
                }
            }
        }

        return largest;
    }

    private static int reverse(int n) {
        int reverse = 0;
        while (n != 0) {
            reverse = reverse * 10 + n % 10;
            n /= 10;
        }
        return reverse;
    }
}

// Even faster! But limited to 6-digit integers
// P = 100000x + 10000y + 1000z + 100z + 10y + x
// P = 100001x + 10010y + 1100z
// P = 11 * (9091x + 910y + 100z)
// Since 11 is prime, at least one of the integers a or b must have a factor of 11.
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            System.out.println(solve(n));
        }
    }

    private static int solve(int n) {
        int largest = 0;
        for (int i = 100; i <= 999; i++) {
            int inc = 11;
            int j = 100;
            if (i % 11 == 0) {
                inc = 1;
                j = 110;
            }
            for ( ; j <= 999; j += inc) {
                int cur = i * j;
                if (cur >= n) {
                    break;
                }
                if (cur == reverse(cur)) {
                    largest = Math.max(largest, cur);
                }
            }
        }

        return largest;
    }

    private static int reverse(int n) {
        int reverse = 0;
        while (n != 0) {
            reverse = reverse * 10 + n % 10;
            n /= 10;
        }
        return reverse;
    }
}
