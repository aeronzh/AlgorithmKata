// 对正整数n，如果用2到sqrt(n)之间的所有整数去除，均无法整除，则n为质数
// Proof:
// 如果n不能被[2, sqrt(n)]之间的任一整数整除，且不是质数，那么n可以表示为：n = a * b，其中a * b是非1正整数。
// 因为n不能被[2, sqrt(n)]之间的任一整数整除，所以a ＞ sqrt(n)，b ＞ sqrt(n)，a * b ＞ sqrt(n) * sqrt(n) = n。
// 这跟a * b = n是矛盾的,所以原来的命题得证.
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            long n = sc.nextLong();
            System.out.println(largestPrimeFactor(n));
        }
    }

    private static long largestPrimeFactor(long n) {
        long largestPrimeFactor = 2;
        long i = 2;

        while (i * i <= n) {
            while (n % i == 0) {
                largestPrimeFactor = i;
                n /= i;
            }
            i++;
        }

        // if n is not prime, n == 1
        return n > largestPrimeFactor ? n : largestPrimeFactor;
    }
}

// brute force. TLE
// e.g N = 12 = 2 * 2 * 3, return 3
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            long n = sc.nextLong();
            System.out.println(largestPrimeFactor(n));
        }
    }

    private static long largestPrimeFactor(long n) {
        long largestPrimeFactor = 2;
        long i = 2;

        while (i <= n) {
            while (n % i == 0) {
                largestPrimeFactor = i;
                n /= i;
            }
            i++;
        }

        return largestPrimeFactor;
    }
}
