// lcm = (a * b) / gcd(a, b)
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
        long lcm = 1;
        for (long i = 2; i <= n; i++) {
            lcm = lcm * i / gcd(lcm, i);
        }

        return lcm;
    }

    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }
}
