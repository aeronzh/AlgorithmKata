// https://www.hackerrank.com/contests/projecteuler/challenges/euler001

// Arithmetic progression
// Take the number of terms being incremented, multiplying by the sum of the first and last number in the progression, and dividing by 2 (一个等差数列的和等于其首项与末项的和乘以项数除以2)
// If N = 99, there are (99 - 1) / 3 = 32 multiples of 3. Then the largest multiple of 3 below 99 (excluding 99) is 32 * 3 = 96
// Therefore the sum of all multiples of 3 has a pattern 3 * (1 + 2 + 3 + ... + 32) = (1 * 3 + 32 * 3) * 32 / 2
public class Solution {

    private static long n;

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            n = sc.nextInt();
            System.out.println(sum(3) + sum(5) - sum(15));
        }
    }

    private static long sum(long base) {
        long numOfTerms = (n - 1) / base;
        return (1 + numOfTerms) * base * numOfTerms / 2;
    }
}

// time out
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            System.out.println(sumOfMultiples(n));
        }
    }

    private static int sumOfMultiples(int n) {
        int sum = 0;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                if (set.add(i)) {
                    sum += i;
                }
            }
        }

        return sum;
    }
}
