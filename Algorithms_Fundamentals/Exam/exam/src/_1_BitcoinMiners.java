import java.util.Scanner;

public class _1_BitcoinMiners {
    public static long transactions[][];
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        int x = Integer.parseInt(scan.nextLine());

        transactions = new long[n + 1][x + 1];

        System.out.println(calcBinom(n, x));

    }

    private static long calcBinom(int n, int x) {

        if (x == 0 || x == n) {
            return 1;
        }

        if (transactions[n][x] != 0) {
            return transactions[n][x];
        }

        return transactions[n][x] = calcBinom(n - 1, x) + calcBinom(n - 1, x - 1);
    }
}
