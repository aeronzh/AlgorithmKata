// every third number in the Fibonacci sequence is even
// proof: odd + odd = even; even + odd = odd
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            long n = sc.nextLong();
            System.out.println(solve(n));
        }
    }

    private static long solve(long n) {
        long a = 1;
        long b = 1;
        long c = 2;
        long sum = 0;

        while (c <= n) {
            sum += c;
            a = b + c;
            b = c + a;
            c = a + b;
        }

        return sum;
    }
}

// brute force
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            long n = sc.nextLong();
            System.out.println(solve(n));
        }
    }

    private static long solve(long n) {
        long pre = 1;
        long cur = 1;
        long sum = 0;

        while (cur <= n) {
            sum += cur % 2 == 0 ? cur : 0;
            long temp = cur;
            cur += pre;
            pre = temp;
        }

        return sum;
    }
}
