import java.util.Scanner;

public class _1_FibonacciSeq {
    public static long[] dp;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());

        dp = new int[n + 1];

        long fib = getFib(n);

    }

    private static long getFib(int n) {
        if (n <= 2) {
            return 1;
        }

        if (dp[n] != 0) {
            return  dp[n];
        }

        return dp[n] = getFib(n - 1) + getFib(n - 2);
    }
}
