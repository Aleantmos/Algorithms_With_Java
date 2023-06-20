import java.util.Scanner;

public class _1_BinomialCoefficients {
    public static long memory[][];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        int k = Integer.parseInt(scan.nextLine());

        memory = new long[n + 1][k + 1];

        long binom = calcBinom(n, k);

        System.out.println(binom);
    }

    private static long calcBinom(int n, int k) {

        if (k == 0 || k == n) {
            return 1;
        }

        if (memory[n][k] != 0) {
            return memory[n][k];
        }

        return memory[n][k] = calcBinom(n - 1, k) + calcBinom(n - 1, k - 1);
    }
}
